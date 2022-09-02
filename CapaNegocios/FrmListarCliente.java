/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import CapaDatos.VentasDAO;
import CapaEntidad.ConstructorCliente;
/**
 *
 * @author USER
 */
public class FrmListarCliente extends javax.swing.JFrame {
  VentasDAO oVentaDAO = new VentasDAO();
  ConstructorCliente pro = new  ConstructorCliente();
       public void tablaP(){
        String[] columnas ={"COD","NOMBRE"};
        Object[] obj = new Object[2];
        DefaultTableModel tabla = new DefaultTableModel(null,columnas);
        List ls;
        try {
            ls = oVentaDAO.mostrarCliente();
            for (int i = 0; i < ls.size(); i++) {
                pro = (ConstructorCliente) ls.get(i);
                obj[0] = pro.getCodigo();
                obj[1] = pro.getNombre();  
                
                tabla.addRow(obj);

            }
            this.tblCliente.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(this,e.toString(),"Aviso",1);
        }
        personalizarTabla();
    }
         private void personalizarTabla(){
        tblCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
      
    }
    public FrmListarCliente() {
        initComponents();
        tablaP();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCliente.setBackground(new java.awt.Color(255, 255, 204));
        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tblCliente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 230, 120));

        jLabel3.setFont(new java.awt.Font("Javanese Text", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLIENTE");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 230, 46));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrmListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmListarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCliente;
    // End of variables declaration//GEN-END:variables
}