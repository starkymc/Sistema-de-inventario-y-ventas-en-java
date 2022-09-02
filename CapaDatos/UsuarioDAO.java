    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import Conexion.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import CapaEntidad.ConstructorUsuario;
import java.sql.CallableStatement;
import javax.swing.JComboBox;

/**
 *
 * @author USER
 */
public class UsuarioDAO {
     ConexionSQL oconexion = new ConexionSQL();
    Connection cn=oconexion.abrirConexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
    
       public int auto_increm(String sql){
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        try{    
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    id = rs.getInt(1)+1;
                }
        }catch(Exception ex){
            System.out.println("idmaximo"+ex.getMessage());
            id = 1;
        }
        finally{
            try{
                ps.close();
                rs.close();
               
            }catch(Exception ex){}
        }
        return id;
    }
    public boolean RegistrarUsuario(ConstructorUsuario usu){
        String sql = "INSERT INTO USUARIO (IDUSUARIO, NOMBREUSUARIO, CONTRASEÑA, PERSONAL, HABILITADO) VALUES (?,?,?,?,?)";
        try {
           
            ps = cn.prepareStatement(sql);
            ps.setInt(1, usu.getCodigo());
            ps.setString(2, usu.getUsuario());
            ps.setString(3, usu.getContraseña());
            ps.setString(4, usu.getPersonal());
            ps.setString(5, usu.getHabilitado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public void ConsultarPersonal(JComboBox pers){
        String sql = "SELECT nombre_completo FROM personal";
        try {
            
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
               pers.addItem(rs.getString("nombre_completo"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public ArrayList listarUsuario(){
      ArrayList<ConstructorUsuario> lista = new ArrayList<ConstructorUsuario>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARUSUARIO()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorUsuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  } 
    
    public boolean EliminarUsuario(int id){
       String sql = "DELETE FROM USUARIO WHERE IDUSUARIO = ?";
       try {
           ps = cn.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
       
   }
    
    public boolean ModificarUsuario(ConstructorUsuario usu){
       String sql = "UPDATE USUARIO SET  NOMBREUSUARIO=?, CONTRASEÑA=?, PERSONAL=?, HABILITADO=? WHERE IDUSUARIO=?";
       try {
           ps = cn.prepareStatement(sql);
            
            ps.setString(1, usu.getUsuario());
            ps.setString(2, usu.getContraseña());
            ps.setString(3, usu.getPersonal());
            ps.setString(4, usu.getHabilitado());
            ps.setInt(5,usu.getCodigo());
            ps.execute();
            return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
   }
    
    public ConstructorUsuario BuscarPro(int cod){
        ConstructorUsuario usu = new ConstructorUsuario();
        String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = ?";
        try {
            
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                usu.setUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setContraseña(rs.getString("CONTRASEÑA"));
                usu.setPersonal(rs.getString("PERSONAL"));
                usu.setHabilitado(rs.getString("HABILITADO"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return usu;
    }
    
   
}
