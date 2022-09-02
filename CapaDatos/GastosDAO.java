/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaEntidad.ConstructorGastos;
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
public class GastosDAO {
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
 public ArrayList listarGastos(){
      ArrayList<ConstructorGastos> lista = new ArrayList<ConstructorGastos>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARGASTOS()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorGastos(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4)));
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  }   
 
 public String agregarGastos(ConstructorGastos oDatos){
    String r = "";
    try {
        CallableStatement cs =cn.prepareCall("{call SP_INSERTARGASTOS (?,?,?,?)}");
        cs.setInt(1, oDatos.getCodigo());
        cs.setString(2, oDatos.getNombre());
        cs.setString(3, oDatos.getDescripcion());
        cs.setFloat(4, oDatos.getMonto());
      

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
 
 public String eliminarGastos(int codigo){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{call SP_ELIMINARGASTOS(?)}");
        
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
 
 
 public String actualizarGastos(ConstructorGastos oDatos){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{ call SP_ACTUALIZARGASTOS (?,?,?,?)}");

        cs.setInt(1, oDatos.getCodigo());
        cs.setString(2, oDatos.getNombre());
        cs.setString(3, oDatos.getDescripcion());
        cs.setFloat(4, oDatos.getMonto());
   
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
 
 public ConstructorGastos buscarGastos(int codigo){
    try {
        CallableStatement cs=cn.prepareCall("{call SP_BUSCARGASTOS(?)}");
        
        cs.setInt(1, codigo); 
        ResultSet rs= cs.executeQuery();
        if(rs.next()){
            
            return (new ConstructorGastos(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4)));
        }
        
    } catch (Exception ex) {
        System.out.println("Buscar : "+ex.getMessage());
    }
    return null;
}
}

