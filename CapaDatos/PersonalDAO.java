/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaEntidad.ConstructorPersonal;
import Conexion.ConexionSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class PersonalDAO {
            ConexionSQL oconexion = new ConexionSQL();
            Connection cn=oconexion.abrirConexion();
    
    
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
 public ArrayList listarPersonal(){
      ArrayList<ConstructorPersonal> lista = new ArrayList<ConstructorPersonal>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARPERSONAL()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorPersonal(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5)));
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  }   
 
 public String agregarPersonal(ConstructorPersonal oDatos){
    String r = "";
    try {
        CallableStatement cs =cn.prepareCall("{call SP_INSERTARPERSONAL (?,?,?,?,?)}");
        cs.setInt(1, oDatos.getCodigo());
        cs.setString(2, oDatos.getNombre());
        cs.setInt(3, oDatos.getDocum());
        cs.setInt(4, oDatos.getTelefono());
        cs.setFloat(5, oDatos.getPaga());

        int f=cs.executeUpdate();
        if (f>0){
            r="Registro Agregado";    
        }
        else{
            r="No se realizo la operacion";
        }
    } catch (Exception ex) {
        r=ex.getMessage();
    }
    return r;
}
 
 public String eliminarPersonal(int codigo){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{call SP_ELIMINARPERSONAL(?)}");
        
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
 
 
 public String actualizarPersonal(ConstructorPersonal odatos){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{ call SP_ACTUALIZARPERSONAL (?,?,?,?,?)}");

   cs.setInt(1, odatos.getCodigo());
        cs.setString(2, odatos.getNombre());
        cs.setInt(3, odatos.getDocum());
        cs.setInt(4, odatos.getTelefono());
        cs.setFloat(5, odatos.getPaga());
   
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
 
 public ConstructorPersonal buscarPersonal(int codigo){
    try {
        CallableStatement cs=cn.prepareCall("{call SP_BUSCARPERSONAL(?)}");
        
        cs.setInt(1, codigo); 
        ResultSet rs= cs.executeQuery();
        if(rs.next()){
            
            return (new ConstructorPersonal(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5)));
        }
        
    } catch (Exception ex) {
        System.out.println("Buscar Cliente: "+ex.getMessage());
    }
    return null;
}
}
