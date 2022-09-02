/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;
import Conexion.ConexionSQL;
import CapaEntidad.ConstructorCliente;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author USER
 */
public class ClienteDAO {
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
 public ArrayList listarCliente(){
      ArrayList<ConstructorCliente> lista = new ArrayList<ConstructorCliente>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARCLIENTE()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorCliente(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)));
             
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  }   
 
 public String agregarCliente(ConstructorCliente oDatos) throws SQLException{
   String r="";
    try {
   
        CallableStatement cs =cn.prepareCall("{call SP_INSERTARCLIENTE (?,?,?,?,?)}");
         cs.setInt(1, oDatos.getCodigo());
        cs.setString(2, oDatos.getNombre());
        cs.setInt(3, oDatos.getDocumento());
        cs.setString(4, oDatos.getDireccion());
        cs.setInt(5, oDatos.getTelefono());
      
        
        int f=cs.executeUpdate();
        if (f>0){
            r="Registro Agregado";    
        } else{
             r="No se realizo la operacion";
         }
        
    } catch (Exception ex) {
      System.out.println("Agregar : "+ex.getMessage());
    }
    return r;
}
 
 public String eliminarCliente(int codigo){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{call SP_ELIMINARCLIENTE(?)}");
        
        cs.setInt(1,codigo);
        cs.executeUpdate();
        int f=cs.executeUpdate();
        if (f>0){
            r="No se realizo la operacion ";    
        }
        else{
            r="Registro Eliminado";
        }
    } catch(Exception ex) {
        r=ex.getMessage();
    }
    return r;  
}
 
 
 public String actualizarCliente(ConstructorCliente odatos){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{ call SP_ACTUALIZARCLIENTE (?,?,?,?,?)}");

         cs.setInt(1, odatos.getCodigo());
        cs.setString(2, odatos.getNombre());
        cs.setInt(3, odatos.getDocumento());
        cs.setString(4, odatos.getDireccion());
        cs.setInt(5, odatos.getTelefono());
   
        cs.executeUpdate();
        int f=cs.executeUpdate();
        if (f>0){
            r="Registro actualizado";
        }
        else{
            r="No se realizo la operacion";
        }
        
        
    } catch (Exception ex) {
        r=ex.getMessage();
    }
    return r;
}
 
 public ConstructorCliente buscarCliente(int codigo){
    try {
        CallableStatement cs=cn.prepareCall("{call SP_BUSCARCLIENTE(?)}");
        
        cs.setInt(1, codigo); 
        ResultSet rs= cs.executeQuery();
        if(rs.next()){
            
            return (new ConstructorCliente(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)));
        }
        
    } catch (Exception ex) {
        System.out.println("Buscar : "+ex.getMessage());
    }
    return null;
}
 
 

}


