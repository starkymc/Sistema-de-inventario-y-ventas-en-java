/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.io.IOException;
import CapaDatos.Exportar;
import CapaDatos.ProductosDAO;
import CapaEntidad.ConstructorProductos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//NUEVA
/**
 *
 * @author Gian
 */
public class FrmProductos extends javax.swing.JFrame {
ProductosDAO oProductosDAO = new ProductosDAO();

 Exportar obj;  //Objeto exportart

      void limpiarProductos(){
        
        txtNombreProducto.setText("");
        txtCategoriaProducto.setText("");
        txtStockProducto.setText("");
        txtPrecioVentaProducto.setText("");
        txtPrecioCompraProducto.setText("");   
    }
    void activarCajasProductos(boolean v){
       
        txtNombreProducto.setEnabled(v);
        txtCategoriaProducto.setEnabled(v);
        txtStockProducto.setEnabled(v);
        txtPrecioVentaProducto.setEnabled(v);
        txtPrecioCompraProducto.setEnabled(v);
        
        
    }
    void activarBotonesProductos(boolean botonAgregar,boolean botonBuscar,boolean botonActualizar,boolean botonEliminar){
        btnAgregarProducto1.setEnabled(botonAgregar);
        btnBuscarProducto.setEnabled(botonBuscar);
        btnModificarProducto.setEnabled(botonActualizar);
        btnEliminarProducto.setEnabled(botonEliminar);   
    }

    void listarProductos(){
          DefaultTableModel modelo=new DefaultTableModel();
        String [] titulos= {"IDPRODUCTO","NOMBRE","CATEGORIA","STOCK","PRECIO COMPRA","PRECIO VENTA"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<ConstructorProductos> lista= oProductosDAO.listarProductos();
        for(ConstructorProductos ocomDatos:lista){
            Object data[]={ocomDatos.getCodigo(),ocomDatos.getProducto(),ocomDatos.getCategoria(),ocomDatos.getStock(),ocomDatos.getpCompra(),ocomDatos.getpVenta()};
            modelo.addRow(data);
        }
        TblProducto.setModel(modelo);
        personalizarTablaProducto();   
    }
    private void personalizarTablaProducto(){
        TblProducto.getColumnModel().getColumn(0).setPreferredWidth(100);
        TblProducto.getColumnModel().getColumn(1).setPreferredWidth(100);
        TblProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
        TblProducto.getColumnModel().getColumn(3).setPreferredWidth(100);
        TblProducto.getColumnModel().getColumn(4).setPreferredWidth(100);
        TblProducto.getColumnModel().getColumn(5).setPreferredWidth(100);    
    }
     void mensaje(String men){
        JOptionPane.showMessageDialog(this, men,"Aviso",1);
    }
     
     
       
    void Obt_productos(){
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("Todos los productos");
        ListaModelo.addElement("BATERIAS");
        ListaModelo.addElement("METALES");
        ListaModelo.addElement("MOTORES");
        ListaModelo.addElement("PLASTICO");
        ListaModelo.addElement("CAJA BEBIDAS");
        ListaModelo.addElement("BEBIDAS");
        ListaModelo.addElement("OTROS");
        cboProductos.setModel(ListaModelo);
       
} 
    
      void filtroProductos(String pro){
    DefaultTableModel model = new DefaultTableModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             
             Connection cn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BD_VENTAS;user=sa;password=123");
            
             Statement st=cn.createStatement();
             
             ResultSet rs;
             
             rs=st.executeQuery("select * from Productos where categoria='"+pro+"'" );
             
             model.addColumn("COD");model.addColumn("NOMBRE");
             model.addColumn("CATEGORIA");model.addColumn("STOCK");model.addColumn("PRECIO COMPRA");
             model.addColumn("PRECIO VENTA");
             
             while (rs.next()) {
                Object data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                model.addRow(data);
            }
            
             TblProducto.setModel(model);
             rs.close();
             cn.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this,e.toString(),"Aviso",1);
        }
        
    }
      public int id_auto(){
         int id_max2 = 1;
       
        
        try{
            id_max2 = oProductosDAO.auto_increm("SELECT MAX(IDPRODUCTO) FROM PRODUCTOS;");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id_max2;
        
    }
      
    public FrmProductos() {
        initComponents();
        this.setLocationRelativeTo(null);
        listarProductos();
        Obt_productos();
        activarCajasProductos(false); 
        activarBotonesProductos(false,true,false,false);
          this.txtidproducto.setText(String.valueOf(id_auto()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlBckgroundproductos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Pnlcajasdetextbx = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtidproducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtCategoriaProducto = new javax.swing.JTextField();
        txtStockProducto = new javax.swing.JTextField();
        txtPrecioVentaProducto = new javax.swing.JTextField();
        txtPrecioCompraProducto = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnExportar = new javax.swing.JButton();
        btnNuevoProductos = new javax.swing.JButton();
        btnAgregarProducto1 = new javax.swing.JButton();
        btnModificarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TblProducto = new javax.swing.JTable();
        cboProductos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Productos");
        setMinimumSize(new java.awt.Dimension(880, 580));
        setPreferredSize(new java.awt.Dimension(880, 580));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnlBckgroundproductos.setBackground(new java.awt.Color(51, 0, 51));
        PnlBckgroundproductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Javanese Text", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Productos");
        PnlBckgroundproductos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 200, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-caza-del-producto-120.png"))); // NOI18N
        PnlBckgroundproductos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 40));

        getContentPane().add(PnlBckgroundproductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 60));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Pnlcajasdetextbx.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Id Producto");

        jLabel3.setText("Producto");

        jLabel4.setText("Categoria");

        jLabel5.setText("Stock");

        jLabel6.setText("Precio Compra");

        jLabel7.setText("Precio Venta");

        txtidproducto.setEnabled(false);

        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });

        txtCategoriaProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCategoriaProductoKeyTyped(evt);
            }
        });

        txtStockProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockProductoKeyTyped(evt);
            }
        });

        txtPrecioVentaProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaProductoKeyTyped(evt);
            }
        });

        txtPrecioCompraProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraProductoKeyTyped(evt);
            }
        });

        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar (1).png"))); // NOI18N
        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlcajasdetextbxLayout = new javax.swing.GroupLayout(Pnlcajasdetextbx);
        Pnlcajasdetextbx.setLayout(PnlcajasdetextbxLayout);
        PnlcajasdetextbxLayout.setHorizontalGroup(
            PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlcajasdetextbxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlcajasdetextbxLayout.createSequentialGroup()
                        .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btnBuscarProducto))
                    .addGroup(PnlcajasdetextbxLayout.createSequentialGroup()
                        .addGroup(PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PnlcajasdetextbxLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtStockProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlcajasdetextbxLayout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPrecioVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlcajasdetextbxLayout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPrecioCompraProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PnlcajasdetextbxLayout.setVerticalGroup(
            PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlcajasdetextbxLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarProducto)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStockProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(29, 29, 29)
                .addGroup(PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecioCompraProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(PnlcajasdetextbxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecioVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel1.add(Pnlcajasdetextbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 410));

        jLabel8.setFont(new java.awt.Font("Javanese Text", 1, 24)); // NOI18N
        jLabel8.setText("Lista de productos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 270, 40));

        btnExportar.setBackground(new java.awt.Color(255, 255, 255));
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excel_2013_23480 (1).png"))); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.setBorder(null);
        btnExportar.setBorderPainted(false);
        btnExportar.setFocusPainted(false);
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 120, 40));

        btnNuevoProductos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNuevoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-nuevo (1).png"))); // NOI18N
        btnNuevoProductos.setText("Nuevo");
        btnNuevoProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductosActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 110, 35));

        btnAgregarProducto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregarProducto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/caja.png"))); // NOI18N
        btnAgregarProducto1.setText("Agregar");
        btnAgregarProducto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProducto1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarProducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 110, 35));

        btnModificarProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnModificarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diseno-de-producto.png"))); // NOI18N
        btnModificarProducto.setText("Modifcar");
        btnModificarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 130, 35));

        btnEliminarProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agotado.png"))); // NOI18N
        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 110, 35));

        TblProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(TblProducto);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 640, 350));

        cboProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboProductos.setBorder(null);
        cboProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboProductos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProductosItemStateChanged(evt);
            }
        });
        jPanel1.add(cboProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 180, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 880, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductosActionPerformed
       try{ if(btnNuevoProductos.getText().equalsIgnoreCase("Nuevo")){
            limpiarProductos();
            activarBotonesProductos(true,false,false,false);
            activarCajasProductos(true);
            btnNuevoProductos.setText("Cancelar");
           this.txtidproducto.setText(String.valueOf(id_auto()));
        }else {
            limpiarProductos();
            activarBotonesProductos(false,true,false,false);
            activarCajasProductos(false);
            btnNuevoProductos.setText("Nuevo");
            
        }}catch(Exception ex){
           System.out.println("Nuevo : "+ex.getMessage());
      }
    }//GEN-LAST:event_btnNuevoProductosActionPerformed

    private void btnAgregarProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProducto1ActionPerformed

        try {
            String m;
            m = oProductosDAO.agregarProductos(new ConstructorProductos(Integer.parseInt(txtidproducto.getText()),txtNombreProducto.getText(),txtCategoriaProducto.getText(),Float.parseFloat(txtStockProducto.getText()),Float.parseFloat(txtPrecioVentaProducto.getText()),Float.parseFloat(txtPrecioCompraProducto.getText())));
            mensaje(m);
            listarProductos();
            limpiarProductos();
              this.txtidproducto.setText(String.valueOf(id_auto()));
            activarBotonesProductos(false,true,false,false);
            activarCajasProductos(false);
            btnNuevoProductos.setText("Nuevo");
        } catch (Exception ex) {
            System.out.println("Agregar : "+ex.getMessage());
        }

    }//GEN-LAST:event_btnAgregarProducto1ActionPerformed

    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed
     try{   String m=oProductosDAO.actualizarProductos(new ConstructorProductos(Integer.parseInt(txtidproducto.getText()),txtNombreProducto.getText(),txtCategoriaProducto.getText(),Float.parseFloat(txtStockProducto.getText()),Float.parseFloat(txtPrecioCompraProducto.getText()),Float.parseFloat(txtPrecioVentaProducto.getText())));
        mensaje(m);
        listarProductos();
        limpiarProductos();
          this.txtidproducto.setText(String.valueOf(id_auto()));
        activarBotonesProductos(false,true,false,false);
        activarCajasProductos(false);
         } catch (Exception ex) {
            System.out.println("Modificar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnModificarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
     try{   String m=oProductosDAO.eliminarProductos(Integer.parseInt(txtidproducto.getText()));
        mensaje(m);
        listarProductos();
        limpiarProductos();
          this.txtidproducto.setText(String.valueOf(id_auto()));
        activarBotonesProductos(false,true,false,false);
        activarCajasProductos(false);
     }  catch (Exception ex) {
            System.out.println("Agregar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
      try{  ConstructorProductos oProductos;
        int dato=Integer.parseInt(JOptionPane.showInputDialog("Codigo a buscar "));
        oProductos=oProductosDAO.buscarProductos(dato);
        if(oProductos==null){
            mensaje("CODIGO NO EXISTE");
            limpiarProductos();
            activarBotonesProductos(false,false,false,false);
            activarCajasProductos(false);
        }else{
            txtidproducto.setText(""+oProductos.getCodigo());
            txtNombreProducto.setText(oProductos.getProducto());
            txtCategoriaProducto.setText(oProductos.getCategoria());
            txtStockProducto.setText(""+oProductos.getStock());
            txtPrecioCompraProducto.setText(""+oProductos.getpCompra());
            txtPrecioVentaProducto.setText(""+oProductos.getpVenta());
            activarBotonesProductos(false,false,true,true);
            activarCajasProductos(true);
        } } catch (Exception ex) {
            System.out.println("Buscar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void cboProductosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProductosItemStateChanged
       try{ String pro;
        pro = cboProductos.getItemAt(cboProductos.getSelectedIndex());

        if(cboProductos.getSelectedIndex()==0){
            listarProductos();
        }else {
            filtroProductos(pro);
        } } catch (Exception ex) {
            System.out.println("Productos : "+ex.getMessage());
        }
    }//GEN-LAST:event_cboProductosItemStateChanged

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        try {
            obj = new Exportar();               //Referencia al metodo exportart
            obj.exportarExcel(TblProducto);     //Llamando a la tabla a exportar
         } catch (Exception ex) {
            System.out.println("Exportar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
         char validar= evt.getKeyChar();
      if(Character.isDigit(validar)){
          getToolkit().beep();
          evt.consume();
          JOptionPane.showMessageDialog((rootPane), "Ingresar solo letras");
      }
       if(txtNombreProducto.getText().length()>=120)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 120 caracteres");
        }
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtCategoriaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaProductoKeyTyped
         char validar= evt.getKeyChar();
      if(Character.isDigit(validar)){
          getToolkit().beep();
          evt.consume();
          JOptionPane.showMessageDialog((rootPane), "Ingresar solo letras");
      }
      if(txtCategoriaProducto.getText().length()>=120)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 120 caracteres");
        }
    }//GEN-LAST:event_txtCategoriaProductoKeyTyped

    private void txtStockProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockProductoKeyTyped
        char validar= evt.getKeyChar();
      if(Character.isLetter(validar)){
          getToolkit().beep();
          evt.consume();
          JOptionPane.showMessageDialog((rootPane), "Ingresar solo Números");
      }
      if(txtStockProducto.getText().length()>=4)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 4 digitos");
        }
    }//GEN-LAST:event_txtStockProductoKeyTyped

    private void txtPrecioCompraProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraProductoKeyTyped
        char validar= evt.getKeyChar();
      if(Character.isLetter(validar)){
          getToolkit().beep();
          evt.consume();
          JOptionPane.showMessageDialog((rootPane), "Ingresar solo Números");
      }
      
      if(txtPrecioCompraProducto.getText().length()>=7)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 7 digitos");
        }
    }//GEN-LAST:event_txtPrecioCompraProductoKeyTyped

    private void txtPrecioVentaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaProductoKeyTyped
         char validar= evt.getKeyChar();
      if(Character.isLetter(validar)){
          getToolkit().beep();
          evt.consume();
          JOptionPane.showMessageDialog((rootPane), "Ingresar solo Números");
      }
       if(txtPrecioVentaProducto.getText().length()>=7)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 7 digitos");
        }
    }//GEN-LAST:event_txtPrecioVentaProductoKeyTyped

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
            java.util.logging.Logger.getLogger(FrmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlBckgroundproductos;
    private javax.swing.JPanel Pnlcajasdetextbx;
    private javax.swing.JTable TblProducto;
    private javax.swing.JButton btnAgregarProducto1;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnModificarProducto;
    private javax.swing.JButton btnNuevoProductos;
    private javax.swing.JComboBox<String> cboProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField txtCategoriaProducto;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioCompraProducto;
    private javax.swing.JTextField txtPrecioVentaProducto;
    private javax.swing.JTextField txtStockProducto;
    private javax.swing.JTextField txtidproducto;
    // End of variables declaration//GEN-END:variables
}
