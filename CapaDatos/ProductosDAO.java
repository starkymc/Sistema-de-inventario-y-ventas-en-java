/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaEntidad.ConstructorProductos;
import Conexion.ConexionSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ProductosDAO {
    PreparedStatement ps;
    ResultSet rs;
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
               
            }catch(Exception ex){ System.out.println("autoincrement: "+ex.getMessage());}
        }
        return id;
    }
       
 public ArrayList listarProductos(){
      ArrayList<ConstructorProductos> lista = new ArrayList<ConstructorProductos>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARPRODUCTOS()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorProductos(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getFloat(6)));
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  }   
 
 public String agregarProductos(ConstructorProductos oDatos){
    String r = "";
    try {
        CallableStatement cs =cn.prepareCall("{call SP_INSERTARPRODUCTO (?,?,?,?,?,?)}");
        cs.setInt(1, oDatos.getCodigo());
        cs.setString(2, oDatos.getProducto());
        cs.setString(3, oDatos.getCategoria());
        cs.setFloat(4, oDatos.getStock());
        cs.setFloat(5, oDatos.getpCompra());
        cs.setFloat(6, oDatos.getpVenta());

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
 
 public String eliminarProductos(int codigo){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{call SP_ELIMINARPRODUCTO(?)}");
        
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
 
 
 public String actualizarProductos(ConstructorProductos odatos){
    String r="";
    try {
        CallableStatement cs=cn.prepareCall("{ call SP_ACTUALIZARPRODUCTO (?,?,?,?,?,?)}");

           cs.setInt(1, odatos.getCodigo());
        cs.setString(2, odatos.getProducto());
        cs.setString(3, odatos.getCategoria());
        cs.setFloat(4, odatos.getStock());
        cs.setFloat(5, odatos.getpCompra());
        cs.setFloat(6, odatos.getpVenta());
   
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
 
 public ConstructorProductos buscarProductos(int codigo){
    try {
        CallableStatement cs=cn.prepareCall("{call SP_BUSCARPRODUCTO(?)}");
        
        cs.setInt(1, codigo); 
        ResultSet rs= cs.executeQuery();
        if(rs.next()){
            
            return (new ConstructorProductos(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getFloat(6)));
        }
        
    } catch (Exception ex) {
        System.out.println("Buscar : "+ex.getMessage());
    }
    return null;
}

 
 
}
