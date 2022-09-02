    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import CapaEntidad.ConstructorDetalle_Venta;
import CapaDatos.VentasDAO;
import CapaEntidad.ConstructorVentas;
import javax.swing.JOptionPane;
import CapaEntidad.ConstructorCliente;
import CapaDatos.ClienteDAO;
import CapaEntidad.ConstructorProductos;
import CapaDatos.ProductosDAO;
import CapaEntidad.ConstructorCaja;
import static CapaNegocios.FrmImpresion.tblVenta;
import Conexion.ConexionSQL;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author Gian
 */
public class FrmDetalle_Venta extends javax.swing.JFrame {
  
 
   ///IMPRESION///
   public static String cajero="";
   public static String cliente="";
   public static String fecha="";
   public static String Total="";  
   public static String prods="";
   public static String cantis ="";
   public static String pres="";
   public static String tots="";
   
 
   public void Imprimir(){
    cajero=lblVendedor.getText();
    cliente=lblCliente.getText();
    fecha=lblFecha.getText();     
    Total=lblTotalpagar.getText();  
    
   //for (int i = 0; i < tblVentas.getRowCount(); i++) {
    FrmImpresion ID = new FrmImpresion();  
    ID.setVisible(true);
    ID.setLocationRelativeTo(null);
      
   }
   public void AgregarProductos(){
 
       int i= tblVentas.getSelectedRow();
      // for (int i = 0; i < tblVentas.getSelectedRow(); i++) {
    prods=tblVentas.getValueAt(i,1).toString();
    cantis=tblVentas.getValueAt(i,2).toString();
    pres=tblVentas.getValueAt(i,3).toString();
    tots=tblVentas.getValueAt(i,4).toString();
      // }
    m=(DefaultTableModel)tblVenta.getModel(); 
    String filaelement[]={cantis,prods,pres,tots};
    m.addRow(filaelement);
    
  
    

   }
  //////////////
   
    ConexionSQL oconexion = new ConexionSQL();
    Connection cn=oconexion.abrirConexion();
    int item;
    float  Totalpagar;
 
    
   
  
    DefaultTableModel tmp = new DefaultTableModel();
    DefaultTableModel m;
    VentasDAO oVentaDAO = new VentasDAO();
    ProductosDAO oProductoDAO= new ProductosDAO();
    ClienteDAO oCliente= new ClienteDAO();
    FrmListarCliente Cli= new FrmListarCliente();
    FrmListarProducto Pro= new FrmListarProducto();
    FrmLogin ventana= new FrmLogin();
    FrmCaja cajas= new FrmCaja();
    ConstructorProductos pros= new ConstructorProductos();
    ConstructorDetalle_Venta Dv = new ConstructorDetalle_Venta();
    ConstructorCaja caja = new ConstructorCaja();
   
    void limpiarVentas(){    
        lblProductos.setText("---");
      txtCantidad.setText("");
        lblPrecio.setText("---");       
      
         lblStock.setText("---");
      
       
    }
    void limpiarTodo() {
         lblProductos.setText("---");
      txtCantidad.setText("");
        lblPrecio.setText("---");       
         lblStock.setText("---");
         lblCliente.setText("---");
       
     
         lblTotalpagar.setText("---");
         lblTotalProductos.setText("---");
         lblTotalCantidad.setText("---");
    }
    void TablaPrincipal() {
        
        // Agrega fila
        tmp.addColumn("COD");
        tmp.addColumn("PRODUCTO");
        tmp.addColumn("PESO KG");
        tmp.addColumn("PRECIO");
        tmp.addColumn("IMPORTE");
        
        this.tblVentas.setModel(tmp);
        setLocationRelativeTo(null);

        // CONFIGURAR el taño de ancho de la tabla
        int[] anchos = {35, 100, 40, 80, 80};
        for (int i = 0; i < tblVentas.getColumnCount(); i++) {
            tblVentas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

    }      
    void mensaje(String men){
        JOptionPane.showMessageDialog(this, men,"Aviso",1);
    }  
    public void fecha_actual(){
         Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formateador.format(fechaActual);
      
        lblFecha.setText(fecha);
        
    }     
   
    private void TotalPagar() {
       Totalpagar = 0;
          
        int numFila = tblVentas.getRowCount();
        for (int i = 0; i < numFila; i++) {
            float cal = Float.parseFloat(String.valueOf(tblVentas.getModel().getValueAt(i, 4)));
         
        //  float desc = Float.parseFloat(txtDescuento.getText());
          //  float delivery= Float.parseFloat(txtDelivery.getText());
         //  Totalpagar = ((Totalpagar + cal+delivery) - desc);      
            Totalpagar= (Totalpagar+cal);
        }
      //  lblTotalpagar.setText(String.format("%.2f", Totalpagar));
       lblTotalpagar.setText(String.valueOf(Totalpagar));
    }
  
   
    
    private void ActualizarStock() {
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            String cod = tblVentas.getValueAt(i, 1).toString();
            int cant = Integer.parseInt(tblVentas.getValueAt(i, 2).toString());
            pros = oVentaDAO.BuscarPro(cod);
            float StockActual = pros.getStock() - cant;
            oVentaDAO.ActualizarStocks(StockActual, cod);

        }
    }
    private void ActualizarCaja() {  
        float ValorNeto=0;
            String fecha = lblFecha.getText();
            
            float valor = Float.parseFloat(lblTotalpagar.getText());
           // caja = oVentaDAO.MostrarCaja(fecha);
            float monto = Float.parseFloat(txtCaja.getText());
           // float ValorNeto = ((caja.getMontoFinal())+ valor);
             ValorNeto= (monto+valor);
            oVentaDAO.ActualizarCaja(ValorNeto, fecha);
       
    }
     public float caja(){
       // txtCaja.setText(""+cajas.monto);
       // String fecha= lblFecha.getText();
      //  float cajs=Float.parseFloat(txtCaja.getText());
     
       float monto = 0;
       String fech=lblFecha.getText();
        
        try{
            monto = oVentaDAO.caja("SELECT MONTO_FINAL FROM CAJA WHERE FECHA='"+fech+"';");
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return monto;
        
        
    }
    
    
    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) tblVentas.getModel();
        int fila = tblVentas.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }       
    public int id_auto(){
         int id_max2 = 1;
       
        
        try{
            id_max2 = oVentaDAO.auto_increm("SELECT MAX(IDVENTA) FROM VENTAS;");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id_max2;
        
    }
    public int id_auto1(){
         int id_max = 1;
       
        
        try{
            id_max = oVentaDAO.auto_increm("SELECT MAX(ID_DETALLE) FROM DETALLE_VENTA;");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id_max;
        
    }
    private void ContarProducto(){
        for(int x=0;x<=tblVentas.getRowCount();x++){
          lblTotalProductos.setText(""+x);
        }
    }
    private void ContarCantidad(){
       int contar=tblVentas.getRowCount();
       int suma=0;
       
       for(int i=0; i<contar;i++){
           suma=suma+Integer.parseInt(tblVentas.getValueAt(i,2).toString());
       }
        lblTotalCantidad.setText(""+suma);
        
        }
    
    
    public FrmDetalle_Venta() {
        initComponents();
        
        TablaPrincipal();
        this.setLocationRelativeTo(null);
        fecha_actual(); 
        ContarProducto();
        lblVendedor.setText(""+ventana.usuarios);
        ContarCantidad();
      
        this.txtCaja.setText(String.valueOf(caja()));
        this.lblCodVenta.setText(String.valueOf(id_auto()));  
        this.lblCodDetalle.setText(String.valueOf(id_auto1()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        btnGenerarBoleta = new javax.swing.JButton();
        btnGenerarventa = new javax.swing.JButton();
        txtCaja = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        lblStock = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnCliente = new javax.swing.JButton();
        btnProducto = new javax.swing.JButton();
        btnGuiaC = new javax.swing.JButton();
        btnGuiaP = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        lblTotalpagar = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        lblTotalProductos = new javax.swing.JLabel();
        lblTotalCantidad = new javax.swing.JLabel();
        lblProductos = new javax.swing.JLabel();
        lblCodVenta = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblCodDetalle = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        lblTelf = new javax.swing.JLabel();
        ruta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ventas Cliente");
        setBackground(new java.awt.Color(255, 255, 153));
        setMinimumSize(new java.awt.Dimension(880, 580));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 140, 30));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Precio:");
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 50, 30));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("PESO (kg):");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Cliente:");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 46, 20));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Caja:");

        btnGenerarBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diseno-de-producto.png"))); // NOI18N
        btnGenerarBoleta.setText("Guarda venta");
        btnGenerarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarBoletaActionPerformed(evt);
            }
        });

        btnGenerarventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gasto.png"))); // NOI18N
        btnGenerarventa.setText("Generar Venta");
        btnGenerarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarventaActionPerformed(evt);
            }
        });

        txtCaja.setEnabled(false);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Vendedor:");

        lblVendedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVendedor.setForeground(new java.awt.Color(255, 255, 255));
        lblVendedor.setText("---");

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuenta.png"))); // NOI18N
        btnImprimir.setText("Imprimir boleta");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVendedor)
                .addGap(214, 214, 214)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarventa)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarBoleta)
                .addGap(18, 18, 18)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVendedor)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarventa)
                    .addComponent(btnGenerarBoleta))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 970, 80));

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ventas.png"))); // NOI18N
        jPanel1.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gastos.png"))); // NOI18N
        btnCancelar.setText("Eliminar Producto");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 150, -1));

        lblStock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblStock.setText("---");
        jPanel1.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Producto:");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 70, 30));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Cod Detalle:");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 80, 20));

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVentas.setFocusable(false);
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVentas);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 910, 270));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel13.setText("VENTAS");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 42, 740, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 92, 740, 10));

        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar (1).png"))); // NOI18N
        btnCliente.setText("Buscar Cliente");
        btnCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 160, -1));

        btnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar (1).png"))); // NOI18N
        btnProducto.setText("Buscar Producto");
        btnProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 160, -1));

        btnGuiaC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find_search_card_user_16713.png"))); // NOI18N
        btnGuiaC.setText("Guia");
        btnGuiaC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuiaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiaCActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuiaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 50, 110, 30));

        btnGuiaP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find_search_card_user_16713.png"))); // NOI18N
        btnGuiaP.setText("Guia");
        btnGuiaP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuiaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiaPActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuiaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 110, 30));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Stock:");
        jPanel1.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 105, 60, 20));

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 140, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Cod Venta:");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 70, 20));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Total a pagar:");
        jPanel1.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, -1, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Total Productos:");
        jPanel1.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        lblTotalpagar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalpagar.setText("---");
        jPanel1.add(lblTotalpagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 520, -1, -1));

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha.setText("---");
        jPanel1.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 15, -1, 20));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Fecha:");
        jPanel1.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, 30));

        lblTotalProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalProductos.setText("---");
        jPanel1.add(lblTotalProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, -1, -1));

        lblTotalCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalCantidad.setText("---");
        jPanel1.add(lblTotalCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, -1, -1));

        lblProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblProductos.setText("---");
        jPanel1.add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, 30));

        lblCodVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCodVenta.setText("---");
        jPanel1.add(lblCodVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, 20));

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCliente.setText("---");
        jPanel1.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, 40));

        lblPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrecio.setText("---");
        jPanel1.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, 30));

        lblCodDetalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCodDetalle.setText("---");
        jPanel1.add(lblCodDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, 20));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Total Peso:");
        jPanel1.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, -1, -1));

        lblDireccion.setForeground(new java.awt.Color(255, 255, 204));
        jPanel1.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        lblDNI.setForeground(new java.awt.Color(255, 255, 204));
        jPanel1.add(lblDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, -1, -1));

        lblTelf.setForeground(new java.awt.Color(255, 255, 204));
        jPanel1.add(lblTelf, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, -1));
        jPanel1.add(ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 0, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarventaActionPerformed
     try {   
            String m=oVentaDAO.agregarVentas(new ConstructorVentas(Integer.parseInt(lblCodVenta.getText()),lblCliente.getText(),lblVendedor.getText(),Float.parseFloat(lblTotalpagar.getText()),lblFecha.getText()));                      
            ActualizarStock();
           ActualizarCaja();
            mensaje(m);                   
           
        } catch (Exception ex) {
            System.out.println("Agregar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnGenerarventaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       tmp = (DefaultTableModel) tblVentas.getModel();
        tmp.removeRow(tblVentas.getSelectedRow());
        TotalPagar();
       
         
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
       try{
         ConstructorProductos oDv;
        int dato=Integer.parseInt(JOptionPane.showInputDialog("Codigo a buscar "));
        oDv=oProductoDAO.buscarProductos(dato);
        if(oDv==null){
            mensaje("CODIGO NO EXISTE");
              
        }else{
            lblProductos.setText(oDv.getProducto());
            lblStock.setText(""+oDv.getStock());
            lblPrecio.setText(""+oDv.getpVenta());
            txtCantidad.requestFocus();
        }
      }catch(Exception ex){
              System.out.println("Buscar : "+ex.getMessage());
      }
    }//GEN-LAST:event_btnProductoActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
       try{
             
         ConstructorCliente oDv;
        int dato=Integer.parseInt(JOptionPane.showInputDialog("Codigo a buscar "));
        oDv=oCliente.buscarCliente(dato);
        if(oDv==null){
            mensaje("CODIGO NO EXISTE");
              
        }else{
            lblCliente.setText(""+oDv.getNombre());
            lblDNI.setText(""+oDv.getDocumento());
            lblTelf.setText(""+oDv.getTelefono());
            lblDireccion.setText(""+oDv.getDireccion());
        }
      }catch(Exception ex){
              System.out.println("Buscar : "+ex.getMessage());
      }
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnGuiaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiaCActionPerformed
         Cli.setVisible(true);
       Cli.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGuiaCActionPerformed

    private void btnGuiaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiaPActionPerformed
       Pro.setVisible(true);
       Pro.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGuiaPActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidad.getText())) {
                int cod = Integer.parseInt(lblCodVenta.getText());
                String descripcion = lblProductos.getText();
                int cant = Integer.parseInt(txtCantidad.getText());
                float precio = Float.parseFloat(lblPrecio.getText());
                float total = cant * precio;
                float stock = Float.parseFloat(lblStock.getText());
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) tblVentas.getModel();
                    for (int i = 0; i < tblVentas.getRowCount(); i++) {
                        if (tblVentas.getValueAt(i, 1).equals(lblProductos.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    tmp.addRow(O);
                    tblVentas.setModel(tmp);
                   
                    TotalPagar();    
                    ContarCantidad();
                    ContarProducto();                    
                    limpiarVentas();
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
            }  
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarTodo();
       
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGenerarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarBoletaActionPerformed
        String m= oVentaDAO.agregarDetalleVenta(new ConstructorDetalle_Venta(Integer.parseInt(lblCodDetalle.getText()),Integer.parseInt(lblTotalProductos.getText()),Integer.parseInt(lblCodVenta.getText()),Integer.parseInt(lblTotalCantidad.getText()),Float.parseFloat(lblTotalpagar.getText())));
        mensaje(m);
        limpiarTodo();
        LimpiarTableVenta();
    }//GEN-LAST:event_btnGenerarBoletaActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char validar= evt.getKeyChar();
      if(Character.isLetter(validar)){
          getToolkit().beep();
          evt.consume();
          JOptionPane.showMessageDialog((rootPane), "Ingresar solo Números");
      }
      
       if(txtCantidad.getText().length()>=5)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 5 digitos");
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
     Imprimir();
    /*    String nombrePdf  =lblCodDetalle.getText();
       
       try{ 
          //pdf(a);
          pdf1();
           File fil = new File("src/pdf/"+nombrePdf+".pdf");
           
          Desktop.getDesktop().open(fil);
      
       
       } catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error boleta:"+e.getMessage());
       }*/
      
           
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
           AgregarProductos();
    }//GEN-LAST:event_tblVentasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmDetalle_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDetalle_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDetalle_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDetalle_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDetalle_Venta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnGenerarBoleta;
    private javax.swing.JButton btnGenerarventa;
    private javax.swing.JButton btnGuiaC;
    private javax.swing.JButton btnGuiaP;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProducto;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodDetalle;
    private javax.swing.JLabel lblCodVenta;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTelf;
    private javax.swing.JLabel lblTotalCantidad;
    private javax.swing.JLabel lblTotalProductos;
    private javax.swing.JLabel lblTotalpagar;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JTextField ruta;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables

   
    private void pdf(String nombrePdf) throws FileNotFoundException,DocumentException   {
       try { 
             
           
          FileOutputStream archivo = new FileOutputStream("src/pdf/Boleta"+nombrePdf+".pdf");
          
            //File file = new File("src/pdf/"+nombrePdf+".pdf");
            //archivo = new FileOutputStream(file);
       
           
            
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);   
            doc.open();
            
            Image img = Image.getInstance("src/img/logoimg.png");    
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.DARK_GRAY);
            Font negrita2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
              Font negrita3 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("\nFecha: "+ new SimpleDateFormat("dd-MM-yyyy").format(date)+"\n\n");
            fecha.setAlignment(Element.ALIGN_LEFT);

            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
            Encabezado.addCell(img); 
            
              PdfPTable tablaDatoss = new PdfPTable(1);
            tablaDatoss.setWidthPercentage(100);
            tablaDatoss.getDefaultCell().setBorder(0);
            float[] ColumnaDS = new float[]{20f};
            tablaDatoss.setWidths(ColumnaDS);
            tablaDatoss.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell D1A = new PdfPCell(new Phrase("              CENTRO DE ACOPIO DE RECICLAJE B&A", negrita3));
            D1A.setBorder(0);
            D1A.setBackgroundColor(BaseColor.WHITE);
            tablaDatoss.addCell(D1A);
            doc.add(tablaDatoss);
            String ruc= "             RUC: 10742297681";
            String tel ="          Cel:963020726-902822015";
            String dir = "    Asoc.Virgen del CarmenII Mz.C Lt.5"+"\n"+"    Carabayllo,Prd.Carmen Torre Blanca";
        
            
            
            Encabezado.addCell("");
            Encabezado.addCell("\n"+ruc+ "\n"+tel+"\n"+dir );
            Encabezado.addCell(""); 
            doc.add(Encabezado);
            
            PdfPTable tablafech = new PdfPTable(1);
            tablafech.setWidthPercentage(100);
            tablafech.getDefaultCell().setBorder(0);
            float[] Columnafech = new float[]{20f};
            tablafech.setWidths(Columnafech);
            tablafech.setHorizontalAlignment(Element.ALIGN_LEFT);
             
             tablafech.addCell("\n");
             tablafech.addCell("Fecha: "+lblFecha.getText());
             tablafech.addCell("\n");
              tablafech.addCell("\n");
             doc.add(tablafech);

            
            PdfPTable tablaDatos = new PdfPTable(1);
          
            tablaDatos.setWidthPercentage(100);
            tablaDatos.getDefaultCell().setBorder(0);
            float[] ColumnaD = new float[]{20f};
            tablaDatos.setWidths(ColumnaD);
            tablaDatos.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell D1 = new PdfPCell(new Phrase("                               DATOS DE LA VENTA", negrita3));
            PdfPCell D2 = new PdfPCell(new Phrase("\n ", negrita3));
            D1.setBorder(0);
            D2.setBorder(0);
            D1.setBackgroundColor(BaseColor.WHITE);
            D2.setBackgroundColor(BaseColor.WHITE);
            tablaDatos.addCell(D1);
            tablaDatos.addCell(D2);
            doc.add(tablaDatos);
            
            PdfPTable tablacli = new PdfPTable(4);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] Columnacli = new float[]{20f, 50f, 30f, 40f};
            tablacli.setWidths(Columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("DNI", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Cliente", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion", negrita));
            cl1.setBorder(1);
            cl2.setBorder(1);
            cl3.setBorder(1);
            cl4.setBorder(1);
            cl1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cl2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cl3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cl4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);
            tablacli.addCell(lblDNI.getText());
            tablacli.addCell(lblCliente.getText());
            tablacli.addCell(lblTelf.getText());
            tablacli.addCell(lblDireccion.getText());
            doc.add(tablacli);
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("\n");
            espacio.setAlignment(Element.ALIGN_MIDDLE);
            doc.add(espacio);

             
            
            //Productos
             PdfPTable tablepro = new PdfPTable(4);
            tablepro.setWidthPercentage(100);
            tablepro.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{20f, 50f, 30f, 40f};
            tablepro.setWidths(Columnapro);
            tablepro.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Peso KG", negrita2));
            PdfPCell pro2 = new PdfPCell(new Phrase("Producto", negrita2));
            PdfPCell pro3 = new PdfPCell(new Phrase("PrecioUND", negrita2));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio Total.", negrita2));
         
            pro1.setBorder(1);
            pro2.setBorder(1);
            pro3.setBorder(1);
            pro4.setBorder(1);
            
            pro1.setBackgroundColor(BaseColor.ORANGE);
            pro2.setBackgroundColor(BaseColor.ORANGE);
            pro3.setBackgroundColor(BaseColor.ORANGE);
            pro4.setBackgroundColor(BaseColor.ORANGE);
            
            tablepro.addCell(pro1);
            tablepro.addCell(pro2);
            tablepro.addCell(pro3);
            tablepro.addCell(pro4);
            for (int i = 0; i < tblVentas.getRowCount(); i++) {
               String producto = tblVentas.getValueAt(i, 1).toString();
               String cantidad = tblVentas.getValueAt(i, 2).toString();
               String precio = tblVentas.getValueAt(i, 3).toString();
               String total = tblVentas.getValueAt(i, 4).toString();
         
                tablepro.addCell(cantidad);
                tablepro.addCell(producto);
                tablepro.addCell(precio);
                tablepro.addCell(total);  
    
           }
             doc.add(tablepro);
            
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + lblTotalpagar.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
           
            
            Paragraph mensaje = new Paragraph();        
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("____________________________________________________________________"+"\n");
            mensaje.add("PESO EXACTO"+"\n");
            mensaje.add("¡GRACIAS POR SU VENTA!"+"\n");
            mensaje.add("REDUCE        REUTILIZA       RECICLA");
            mensaje.setAlignment(Element.ALIGN_CENTER);           
            doc.add(mensaje);
            doc.close();
           // archivo.close();
            
       } catch (DocumentException | IOException e) {
            System.out.println(e.toString());

       }
   }
    
     private void pdf1()  {
       try { 
          
           String nombrePdf  =lblCodDetalle.getText();
           
            FileOutputStream archivo;
             File file = new File("C:\\Users\\USER\\Desktop\\TRABAJO\\PDFS\\venta"+nombrePdf+".pdf");
         //   File file = new File("src/pdf/venta"+nombrePdf+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
    
            Image img = Image.getInstance("src/img/logoimg.png");    
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.DARK_GRAY);
            Font negrita2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
              Font negrita3 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("\nFecha: "+ new SimpleDateFormat("dd-MM-yyyy").format(date)+"\n\n");
            fecha.setAlignment(Element.ALIGN_LEFT);

            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
            Encabezado.addCell(img); 
            
              PdfPTable tablaDatoss = new PdfPTable(1);
            tablaDatoss.setWidthPercentage(100);
            tablaDatoss.getDefaultCell().setBorder(0);
            float[] ColumnaDS = new float[]{20f};
            tablaDatoss.setWidths(ColumnaDS);
            tablaDatoss.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell D1A = new PdfPCell(new Phrase("              CENTRO DE ACOPIO DE RECICLAJE B&A", negrita3));
            D1A.setBorder(0);
            D1A.setBackgroundColor(BaseColor.WHITE);
            tablaDatoss.addCell(D1A);
            doc.add(tablaDatoss);
            String ruc= "             RUC: 10742297681";
            String tel ="          Cel:963020726-902822015";
            String dir = "    Asoc.Virgen del CarmenII Mz.C Lt.5"+"\n"+"    Carabayllo,Prd.Carmen Torre Blanca";
        
            
            
            Encabezado.addCell("");
            Encabezado.addCell("\n"+ruc+ "\n"+tel+"\n"+dir );
            Encabezado.addCell(""); 
            doc.add(Encabezado);
            
            PdfPTable tablafech = new PdfPTable(1);
            tablafech.setWidthPercentage(100);
            tablafech.getDefaultCell().setBorder(0);
            float[] Columnafech = new float[]{20f};
            tablafech.setWidths(Columnafech);
            tablafech.setHorizontalAlignment(Element.ALIGN_LEFT);
             
             tablafech.addCell("\n");
             tablafech.addCell("Fecha: "+lblFecha.getText());
             tablafech.addCell("\n");
              tablafech.addCell("\n");
             doc.add(tablafech);

            
            PdfPTable tablaDatos = new PdfPTable(1);
          
            tablaDatos.setWidthPercentage(100);
            tablaDatos.getDefaultCell().setBorder(0);
            float[] ColumnaD = new float[]{20f};
            tablaDatos.setWidths(ColumnaD);
            tablaDatos.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell D1 = new PdfPCell(new Phrase("                               DATOS DE LA VENTA", negrita3));
            PdfPCell D2 = new PdfPCell(new Phrase("\n ", negrita3));
            D1.setBorder(0);
            D2.setBorder(0);
            D1.setBackgroundColor(BaseColor.WHITE);
            D2.setBackgroundColor(BaseColor.WHITE);
            tablaDatos.addCell(D1);
            tablaDatos.addCell(D2);
            doc.add(tablaDatos);
            
            PdfPTable tablacli = new PdfPTable(4);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] Columnacli = new float[]{20f, 50f, 30f, 40f};
            tablacli.setWidths(Columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("DNI", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Cliente", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion", negrita));
            cl1.setBorder(1);
            cl2.setBorder(1);
            cl3.setBorder(1);
            cl4.setBorder(1);
            cl1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cl2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cl3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cl4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);
            tablacli.addCell(lblDNI.getText());
            tablacli.addCell(lblCliente.getText());
            tablacli.addCell(lblTelf.getText());
            tablacli.addCell(lblDireccion.getText());
            doc.add(tablacli);
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("\n");
            espacio.setAlignment(Element.ALIGN_MIDDLE);
            doc.add(espacio);

             
            
            //Productos
             PdfPTable tablepro = new PdfPTable(4);
            tablepro.setWidthPercentage(100);
            tablepro.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{20f, 50f, 30f, 40f};
            tablepro.setWidths(Columnapro);
            tablepro.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Peso KG", negrita2));
            PdfPCell pro2 = new PdfPCell(new Phrase("Producto", negrita2));
            PdfPCell pro3 = new PdfPCell(new Phrase("PrecioUND", negrita2));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio Total.", negrita2));
         
            pro1.setBorder(1);
            pro2.setBorder(1);
            pro3.setBorder(1);
            pro4.setBorder(1);
            
            pro1.setBackgroundColor(BaseColor.ORANGE);
            pro2.setBackgroundColor(BaseColor.ORANGE);
            pro3.setBackgroundColor(BaseColor.ORANGE);
            pro4.setBackgroundColor(BaseColor.ORANGE);
            
            tablepro.addCell(pro1);
            tablepro.addCell(pro2);
            tablepro.addCell(pro3);
            tablepro.addCell(pro4);
            for (int i = 0; i < tblVentas.getRowCount(); i++) {
               String producto = tblVentas.getValueAt(i, 1).toString();
               String cantidad = tblVentas.getValueAt(i, 2).toString();
               String precio = tblVentas.getValueAt(i, 3).toString();
               String total = tblVentas.getValueAt(i, 4).toString();
         
                tablepro.addCell(cantidad);
                tablepro.addCell(producto);
                tablepro.addCell(precio);
                tablepro.addCell(total);  
    
           }
             doc.add(tablepro);
            
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + lblTotalpagar.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
           
            
            Paragraph mensaje = new Paragraph();        
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("____________________________________________________________________"+"\n");
            mensaje.add("PESO EXACTO"+"\n");
            mensaje.add("¡GRACIAS POR SU VENTA!"+"\n");
            mensaje.add("REDUCE        REUTILIZA       RECICLA");
            mensaje.setAlignment(Element.ALIGN_CENTER);           
            doc.add(mensaje);
            doc.close();
          
           // archivo.close();
            
       } catch (DocumentException | IOException e) {
            System.out.println(e.toString());

       }
   }
}
