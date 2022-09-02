/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaEntidad.ConstructorCaja;
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
public class CajaDAO {
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
 public ArrayList listarCaja(){
      ArrayList<ConstructorCaja> lista = new ArrayList<ConstructorCaja>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARCAJA()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorCaja(rs.getInt(1),rs.getFloat(2),rs.getString(3)));
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  }   
 
 public String agregarCaja(ConstructorCaja oDatos){
    String r = "";
    try {
        CallableStatement cs =cn.prepareCall("{call SP_INSERTARCAJA (?,?,?)}");
        cs.setInt(1, oDatos.getCodigo());
       
        cs.setFloat(2, oDatos.getMontoFinal());
        cs.setString(3, oDatos.getFecha());
       

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
 

 
 public String actualizarCaja(ConstructorCaja oDatos){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{ call SP_ACTUALIZARCAJA (?,?,?)}");

       cs.setInt(1, oDatos.getCodigo());
        
        cs.setFloat(2, oDatos.getMontoFinal());
        cs.setString(3, oDatos.getFecha());
   
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
 
 public ConstructorCaja buscarCaja(int codigo){
    try {
        CallableStatement cs=cn.prepareCall("{call SP_BUSCARCAJA(?)}");
        
        cs.setInt(1, codigo); 
        ResultSet rs= cs.executeQuery();
        if(rs.next()){
            
            return (new ConstructorCaja(rs.getInt(1),rs.getFloat(2),rs.getString(3)));
        }
        
    } catch (Exception ex) {
        System.out.println("Buscar : "+ex.getMessage());
    }
    return null;
}
 
  public String eliminarCaja(int codigo){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{call SP_ELIMINARCAJA(?)}");
        
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
}

