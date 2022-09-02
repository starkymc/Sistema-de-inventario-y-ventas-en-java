/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.io.IOException;
import CapaDatos.Exportar;
import CapaDatos.UsuarioDAO;
import CapaEntidad.ConstructorUsuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Gian
 */
public class FrmUsuario extends javax.swing.JFrame {
UsuarioDAO oUsuarioDAO = new UsuarioDAO();
ConstructorUsuario usu = new ConstructorUsuario();

    Exportar obj;   //Objeto exportar


    void limpiarUsuarios(){
     
        txtUsuario.setText("");
        txtContraUsuario.setText("");
        cboPersonal.setSelectedItem("Seleccionar personal");
        CboHabilitado.setSelectedItem("Seleccionar");
         
         
       
    }
    void activarCajasUsuarios(boolean v){
     
        txtUsuario.setEnabled(v);
        txtContraUsuario.setEnabled(v);
        cboPersonal.setEnabled(v);
        CboHabilitado.setEnabled(v);
        
        
    }
    void activarBotonesUsuarios(boolean botonAgregar,boolean botonActualizar,boolean botonEliminar){
        btnAgregar.setEnabled(botonAgregar);
        btnActualizarUsuario.setEnabled(botonActualizar);
        btnEliminarUsuario.setEnabled(botonEliminar);   
    }
    
    
     void listarUsuario(){
          DefaultTableModel modelo=new DefaultTableModel();
        String [] titulos= {"IDUSUARIO","NOMBREUSUARIO","CONTRASEÑA","PERSONAL","HABILITADO"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<ConstructorUsuario> lista= oUsuarioDAO.listarUsuario();
        for(ConstructorUsuario ocomDatos:lista){
            Object data[]={ocomDatos.getCodigo(),ocomDatos.getUsuario(),ocomDatos.getContraseña(),ocomDatos.getPersonal(),ocomDatos.getHabilitado()};
            modelo.addRow(data);
        }
        TblUsuarios.setModel(modelo);
        personalizarTablaUsuario();
    
    }
        private void personalizarTablaUsuario(){
        TblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
        TblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
        TblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
        TblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(100);
        TblUsuarios.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
   
       public int id_auto(){
         int id_max2 = 1;
       
        
        try{
            id_max2 = oUsuarioDAO.auto_increm("SELECT MAX(IDUSUARIO) FROM USUARIO;");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id_max2;
        
    } 
        
    public FrmUsuario() {
        initComponents();
       this.setLocationRelativeTo(null);
            listarUsuario();
            AutoCompleteDecorator.decorate(cboPersonal);
            oUsuarioDAO.ConsultarPersonal(cboPersonal);
        activarCajasUsuarios(false); 
        activarBotonesUsuarios(false,false,false);
          this.txtidUsuario.setText(String.valueOf(id_auto()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        pnlarriba = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlbackground = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtidUsuario = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtContraUsuario = new javax.swing.JTextField();
        btnExportar = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        cboPersonal = new javax.swing.JComboBox();
        btnActualizarUsuario = new javax.swing.JButton();
        btnNuevoUsuario = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        CboHabilitado = new javax.swing.JComboBox<String>();
        lblimgusuario = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TblUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuario");
        setMinimumSize(new java.awt.Dimension(880, 580));
        setPreferredSize(new java.awt.Dimension(880, 580));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlarriba.setBackground(new java.awt.Color(51, 0, 51));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/u.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Javanese Text", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Usuario");

        javax.swing.GroupLayout pnlarribaLayout = new javax.swing.GroupLayout(pnlarriba);
        pnlarriba.setLayout(pnlarribaLayout);
        pnlarribaLayout.setHorizontalGroup(
            pnlarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlarribaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 665, Short.MAX_VALUE))
        );
        pnlarribaLayout.setVerticalGroup(
            pnlarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlarribaLayout.createSequentialGroup()
                .addGroup(pnlarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlarribaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(pnlarriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 70));

        pnlbackground.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Javanese Text", 1, 24)); // NOI18N
        jLabel1.setText("Lista de usuarios");

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Id Usuario:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 40, 66, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Usuario:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 80, 50, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Contraseña:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 120, 75, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Personal:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 170, 56, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Habilitado");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 220, 58, 17);

        txtidUsuario.setEnabled(false);
        jPanel1.add(txtidUsuario);
        txtidUsuario.setBounds(130, 40, 50, 30);

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtUsuario);
        txtUsuario.setBounds(130, 80, 160, 30);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cboPersonal, org.jdesktop.beansbinding.ObjectProperty.create(), txtContraUsuario, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        txtContraUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraUsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtContraUsuario);
        txtContraUsuario.setBounds(130, 120, 160, 30);

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
        jPanel1.add(btnExportar);
        btnExportar.setBounds(330, 240, 120, 40);

        btnEliminarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/UserRemove_40959.png"))); // NOI18N
        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.setBorder(null);
        btnEliminarUsuario.setBorderPainted(false);
        btnEliminarUsuario.setFocusPainted(false);
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarUsuario);
        btnEliminarUsuario.setBounds(330, 140, 120, 40);

        cboPersonal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar personal" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, CboHabilitado, org.jdesktop.beansbinding.ObjectProperty.create(), cboPersonal, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        jPanel1.add(cboPersonal);
        cboPersonal.setBounds(130, 160, 160, 30);

        btnActualizarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/UserEdit_40958.png"))); // NOI18N
        btnActualizarUsuario.setText("Actualizar");
        btnActualizarUsuario.setBorder(null);
        btnActualizarUsuario.setBorderPainted(false);
        btnActualizarUsuario.setFocusPainted(false);
        btnActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizarUsuario);
        btnActualizarUsuario.setBounds(330, 90, 120, 40);

        btnNuevoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-nuevo (1).png"))); // NOI18N
        btnNuevoUsuario.setText("Nuevo");
        btnNuevoUsuario.setBorder(null);
        btnNuevoUsuario.setBorderPainted(false);
        btnNuevoUsuario.setFocusPainted(false);
        btnNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoUsuario);
        btnNuevoUsuario.setBounds(330, 40, 120, 40);

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/UserAdd_40960.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(null);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar);
        btnAgregar.setBounds(330, 190, 120, 40);

        CboHabilitado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SI", "NO" }));
        CboHabilitado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboHabilitadoActionPerformed(evt);
            }
        });
        jPanel1.add(CboHabilitado);
        CboHabilitado.setBounds(130, 220, 120, 30);

        lblimgusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find_search_card_user_16713 (1).png"))); // NOI18N

        TblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        TblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TblUsuarios);

        javax.swing.GroupLayout pnlbackgroundLayout = new javax.swing.GroupLayout(pnlbackground);
        pnlbackground.setLayout(pnlbackgroundLayout);
        pnlbackgroundLayout.setHorizontalGroup(
            pnlbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbackgroundLayout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabel1)
                .addGap(0, 407, Short.MAX_VALUE))
            .addGroup(pnlbackgroundLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(pnlbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlbackgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlbackgroundLayout.createSequentialGroup()
                        .addComponent(lblimgusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlbackgroundLayout.setVerticalGroup(
            pnlbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbackgroundLayout.createSequentialGroup()
                .addGroup(pnlbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlbackgroundLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(lblimgusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE))
                    .addGroup(pnlbackgroundLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );

        getContentPane().add(pnlbackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 920, 740));

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
           try {
            obj = new Exportar();       //Referencia al metodo
            obj.exportarExcel(TblUsuarios); //Llamando a la tabla a exportar
         } catch (Exception ex) {
            System.out.println("Exportar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
    try{    if (!"".equals(txtidUsuario.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidUsuario.getText());
                oUsuarioDAO.EliminarUsuario(id);
                limpiarUsuarios();
                  this.txtidUsuario.setText(String.valueOf(id_auto()));
                listarUsuario();
                activarBotonesUsuarios(false,false,false);
                activarCajasUsuarios(false);

            }
        }
     } catch (Exception ex) {
            System.out.println("Eliminar : "+ex.getMessage());
        }

    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUsuarioActionPerformed
try{
        if ("".equals(txtidUsuario.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(txtUsuario.getText()) || !"".equals(txtContraUsuario.getText()) ) {
                usu.setUsuario(txtUsuario.getText());
                usu.setContraseña(txtContraUsuario.getText());
                usu.setPersonal(cboPersonal.getSelectedItem().toString());
                usu.setHabilitado(CboHabilitado.getSelectedItem().toString());
               // usu.setHabilitado(txtHabilitado.getText());
                usu.setCodigo(Integer.parseInt(txtidUsuario.getText()));
                oUsuarioDAO.ModificarUsuario(usu);
                JOptionPane.showMessageDialog(null, "Usuario Modificado");

                listarUsuario();
                limpiarUsuarios();
                 this.txtidUsuario.setText(String.valueOf(id_auto()));
                activarBotonesUsuarios(false,false,false);
                activarCajasUsuarios(false);
                btnNuevoUsuario.setText("Nuevo");
            }
        }
 } catch (Exception ex) {
            System.out.println("Agregar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnActualizarUsuarioActionPerformed

    private void btnNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUsuarioActionPerformed
        try{if(btnNuevoUsuario.getText().equalsIgnoreCase("Nuevo")){
            limpiarUsuarios();
            activarBotonesUsuarios(true,false,false);
            activarCajasUsuarios(true);
            btnNuevoUsuario.setText("Cancelar");
           
            this.txtidUsuario.setText(String.valueOf(id_auto()));
        }else {
            limpiarUsuarios();
            activarBotonesUsuarios(false,false,false);
            activarCajasUsuarios(false);
            btnNuevoUsuario.setText("Nuevo");
           
        }
         } catch (Exception ex) {
            System.out.println("Agregar : "+ex.getMessage());
        }
    }//GEN-LAST:event_btnNuevoUsuarioActionPerformed

    private void TblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblUsuariosMouseClicked
      try{  int fila = TblUsuarios.rowAtPoint(evt.getPoint());
        txtidUsuario.setText(TblUsuarios.getValueAt(fila, 0).toString());
        txtUsuario.setText(TblUsuarios.getValueAt(fila, 1).toString());
        txtContraUsuario.setText(TblUsuarios.getValueAt(fila, 2).toString());
        cboPersonal.setSelectedItem(TblUsuarios.getValueAt(fila, 3).toString());
        CboHabilitado.setSelectedItem(TblUsuarios.getValueAt(fila, 4).toString());
        //txtHabilitado.setText(TblUsuarios.getValueAt(fila, 4).toString());
        activarBotonesUsuarios(false,true,true);
        activarCajasUsuarios(true);
         } catch (Exception ex) {
            System.out.println("Listar : "+ex.getMessage());
        }
    }//GEN-LAST:event_TblUsuariosMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       if (!"".equals(txtidUsuario.getText()) || !"".equals(txtUsuario.getText()) || !"".equals(cboPersonal.getSelectedItem()) || !"".equals(txtContraUsuario.getText()) || !"".equals(CboHabilitado.getSelectedItem())) {
            usu.setCodigo(Integer.parseInt(txtidUsuario.getText()));
            usu.setUsuario(txtUsuario.getText());
            usu.setContraseña(txtContraUsuario.getText());
            usu.setPersonal(cboPersonal.getSelectedItem().toString());
            usu.setHabilitado(CboHabilitado.getSelectedItem().toString());
            //usu.setHabilitado(txtHabilitado.getText());
            oUsuarioDAO.RegistrarUsuario(usu);
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
            listarUsuario();
            limpiarUsuarios();
              this.txtidUsuario.setText(String.valueOf(id_auto()));
            activarBotonesUsuarios(false,false,false);
            activarCajasUsuarios(false);
            btnNuevoUsuario.setText("Nuevo");

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        if(txtUsuario.getText().length()>=30)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 30 caracteres");
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtContraUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraUsuarioKeyTyped
        if(txtContraUsuario.getText().length()>=35)
        {
             getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog((rootPane), "Solo se permite 35 caracteres");
        }
    }//GEN-LAST:event_txtContraUsuarioKeyTyped

    private void CboHabilitadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboHabilitadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboHabilitadoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboHabilitado;
    private javax.swing.JTable TblUsuarios;
    private javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnNuevoUsuario;
    private javax.swing.JComboBox cboPersonal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblimgusuario;
    private javax.swing.JPanel pnlarriba;
    private javax.swing.JPanel pnlbackground;
    private javax.swing.JTextField txtContraUsuario;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtidUsuario;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}