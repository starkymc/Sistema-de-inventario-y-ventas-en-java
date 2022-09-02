/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.io.IOException;
import CapaDatos.Exportar;
import CapaEntidad.ConstructorVentas;
import CapaEntidad.ConstructorDetalle_Venta;
import CapaDatos.ClienteDAO;
import CapaDatos.GastosDAO;
import CapaDatos.ProductosDAO;
import CapaDatos.VentasDAO;
import CapaEntidad.ConstructorCliente;
import CapaEntidad.ConstructorGastos;
import CapaEntidad.ConstructorProductos;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

//NUEVA
/**
 *
 * @author Gian
 */
public class FrmInventarios extends javax.swing.JFrame {
  
   ClienteDAO oClienteDAO = new ClienteDAO();
   ProductosDAO oProductosDAO = new ProductosDAO();

   GastosDAO oGastoDAO = new GastosDAO();
   VentasDAO oVentaDAO= new VentasDAO();
    
    Exportar obj;  //Objeto exportar
    
   //CLIENTES//
    void ListarCliente(){
      
        DefaultTableModel modelo=new DefaultTableModel();
        String [] titulos= {"COD","CLIENTE","DNI","DIRECCION","TELEFONO"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<ConstructorCliente> lista= oClienteDAO.listarCliente();
        for(ConstructorCliente ocomDatos:lista){
            Object data[]={ocomDatos.getCodigo(),ocomDatos.getNombre(),ocomDatos.getDocumento(),ocomDatos.getDireccion(),ocomDatos.getTelefono()};
            modelo.addRow(data);
        }
        tblInventario.setModel(modelo);
    
    }
    private void personalizarTablaCliente(){

        tblInventario.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblInventario.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblInventario.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblInventario.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblInventario.getColumnModel().getColumn(4).setPreferredWidth(100);

    }
    
    //PRODUCTOS//
    void ListarProductos(){
        
          DefaultTableModel modelo=new DefaultTableModel();
        String [] titulos= {"COD","NOMBRE","CATEGORIA","STOCK","PRECIO COMPRA","PRECIO VENTA"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<ConstructorProductos> lista= oProductosDAO.listarProductos();
        for(ConstructorProductos ocomDatos:lista){
            Object data[]={ocomDatos.getCodigo(),ocomDatos.getProducto(),ocomDatos.getCategoria(),ocomDatos.getStock(),ocomDatos.getpCompra(),ocomDatos.getpVenta()};
            modelo.addRow(data);
        }
        tblInventario.setModel(modelo);
        personalizarTablaProducto();   
    }
    private void personalizarTablaProducto(){
        tblInventario.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblInventario.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblInventario.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblInventario.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblInventario.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblInventario.getColumnModel().getColumn(5).setPreferredWidth(80);    
     
    }
    
    
    
    //BOLETAS//
    void ListarBoletas(){     
          DefaultTableModel modelo=new DefaultTableModel();
        String [] titulos= {"COD","PRODUCTOS","COD VENTA","PESO KG","IMPORTE"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<ConstructorDetalle_Venta> lista= oVentaDAO.listarBoletaVenta();
        for(ConstructorDetalle_Venta ocomDatos:lista){
            Object data[]={ocomDatos.getCodigo(),ocomDatos.getProducto(),ocomDatos.getVenta(),ocomDatos.getCantidad(),ocomDatos.getPrecio()};
            modelo.addRow(data);
        }
        tblInventario.setModel(modelo);
        personalizarTablaVentas(); 
    }
     private void personalizarTablaBoletas(){
        tblInventario.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblInventario.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblInventario.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblInventario.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblInventario.getColumnModel().getColumn(4).setPreferredWidth(80);
          
     
    }
    
      
    //VENTAS//hacer inner join
    void ListarVentas(){
             DefaultTableModel modelo=new DefaultTableModel();
        String [] titulos= {"COD","CLIENTE","USUARIO","TOTAL","FECHA"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<ConstructorVentas> lista= oVentaDAO.listarVenta();
        for(ConstructorVentas ocomDatos:lista){
            Object data[]={ocomDatos.getCodigo(),ocomDatos.getCliente(),ocomDatos.getUsuario(),ocomDatos.getTotal(),ocomDatos.getFecha()};
            modelo.addRow(data);
        }
        tblInventario.setModel(modelo);
        personalizarTablaVentas(); 
    }
         private void personalizarTablaVentas(){
        tblInventario.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblInventario.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblInventario.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblInventario.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblInventario.getColumnModel().getColumn(4).setPreferredWidth(80);
   
     
    }
    
    
    
    //GASTOS//
    void ListarGastos(){
        
          DefaultTableModel modelo=new DefaultTableModel();
        String [] titulos= {"COD","NOMBRE","DESCRIPCION","MONTO"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<ConstructorGastos> lista= oGastoDAO.listarGastos();
        for(ConstructorGastos ocomDatos:lista){
            Object data[]={ocomDatos.getCodigo(),ocomDatos.getNombre(),ocomDatos.getDescripcion(),ocomDatos.getMonto()};
            modelo.addRow(data);
        }
        tblInventario.setModel(modelo);
        personalizarTablaGasto();   
    }
      private void personalizarTablaGasto(){
        tblInventario.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblInventario.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblInventario.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblInventario.getColumnModel().getColumn(3).setPreferredWidth(80);
        
      }
    
      //VACIO//
      void ListarVacio(){
           DefaultTableModel modelo=new DefaultTableModel();
        tblInventario.setModel(modelo);
      }
    
    public FrmInventarios() {
        initComponents();
        this.setLocationRelativeTo(null);
         
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
        btnExportar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        CboInventarios = new javax.swing.JComboBox<String>();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inventarios");
        setMinimumSize(new java.awt.Dimension(880, 580));
        setPreferredSize(new java.awt.Dimension(900, 580));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excel_2013_23480 (1).png"))); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 140, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("INVENTARIOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 270, 70));

        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblInventario);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 520, 360));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("INVENTARIO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 30));

        CboInventarios.setBackground(new java.awt.Color(204, 204, 255));
        CboInventarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CboInventarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Inventario", "Productos", "Boletas", "Ventas", "Clientes", "Gastos" }));
        CboInventarios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CboInventarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CboInventarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CboInventariosItemStateChanged(evt);
            }
        });
        jPanel1.add(CboInventarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 220, 30));

        lblFondo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/istockphoto-1154145246-170667a.jpg"))); // NOI18N
        lblFondo.setToolTipText("");
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 920, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CboInventariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CboInventariosItemStateChanged
        try{
        String inv = (String)CboInventarios.getSelectedItem();
        if(inv.equals("Seleccionar Inventario")){
           ListarVacio();
        }
        else if(inv.equals("Productos")){
            ListarProductos();
        }
        else if(inv.equals("Clientes")){
            ListarCliente();
        }
        else if(inv.equals("Ventas")){
            ListarVentas();
        }
        else if(inv.equals("Boletas")){
            ListarBoletas();
        }else {
            ListarGastos();
        }
        }catch(Exception ex){
            System.out.println("Listar : "+ex.getMessage());
        }

    }//GEN-LAST:event_CboInventariosItemStateChanged

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
         try {
            obj = new Exportar();               //Referencia al metodo exportart
            obj.exportarExcel(tblInventario);     //Llamando a la tabla a exportar
        }catch(Exception ex){
            System.out.println("Exportar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnExportarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInventarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboInventarios;
    private javax.swing.JButton btnExportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JTable tblInventario;
    // End of variables declaration//GEN-END:variables
}
