/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;


import CapaEntidad.ConstructorCliente;
import CapaEntidad.ConstructorVentas;
import CapaEntidad.ConstructorDetalle_Venta;
import CapaEntidad.ConstructorCaja;
import Conexion.ConexionSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import CapaEntidad.ConstructorProductos;

/**
 *
 * @author USER
 */
public class VentasDAO {
   ConexionSQL oconexion = new ConexionSQL();
   Connection cn=oconexion.abrirConexion();
   PreparedStatement ps;
   ResultSet rs;
   int ra;
   
   
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
    
        public float caja(String sql){
        float mont = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        try{    
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    mont = rs.getFloat(1);
                }
        }catch(Exception ex){
            System.out.println("monto"+ex.getMessage());
            
        }       
        return mont;
    }
       
    public String agregarVentas(ConstructorVentas oDatos){
    String r = "";
    try {
        CallableStatement cs =cn.prepareCall("{call SP_INSETARVENTA (?,?,?,?,?)}");
        cs.setInt(1, oDatos.getCodigo());
        cs.setString(2, oDatos.getCliente());
        cs.setString(3, oDatos.getUsuario());
        cs.setFloat(4, oDatos.getTotal());
        cs.setString(5, oDatos.getFecha());

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
    
     public String agregarDetalleVenta(ConstructorDetalle_Venta oDatos){
    String r = "";
    try {
        CallableStatement cs =cn.prepareCall("{call SP_INSERTARDETALLEVENTA (?,?,?,?,?)}");
        cs.setInt(1, oDatos.getCodigo());
        cs.setInt(2, oDatos.getProducto());
        cs.setInt(3, oDatos.getVenta());
        cs.setInt(4, oDatos.getCantidad());
        cs.setFloat(5, oDatos.getPrecio());

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
    
   public boolean ActualizarStocks(float cant, String cod){
        String sql = "UPDATE productos SET stock = ? WHERE NOMBRE = ?";
        try {
           
            ps = cn.prepareStatement(sql);
            ps.setFloat(1,cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
     public ArrayList listarVenta(){
      ArrayList<ConstructorVentas> lista = new ArrayList<ConstructorVentas>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARVENTA()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorVentas(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getString(5)));
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  }  
  public List mostrarCliente()throws Exception{
         ResultSet res;
         List listaCliente = new ArrayList();
         try {
             
             String sql="select IDCLIENTE,NOMBRE_COMPLETO FROM CLIENTE";
             PreparedStatement pre = this.cn.prepareCall(sql);
             res = pre.executeQuery();
             while (res.next()) {                 
             ConstructorCliente pro = new ConstructorCliente();
             pro.setCodigo(res.getInt("IDCLIENTE"));
             pro.setNombre(res.getString("NOMBRE_COMPLETO"));
           
            
          
             listaCliente.add(pro);
            
             }
         } catch (Exception e) {
             throw e;
         }return listaCliente;
     }
  
   public List mostrarProducto()throws Exception{
         ResultSet res;
         List listaproducto = new ArrayList();
         try {
             
             String sql="select IDPRODUCTO,NOMBRE FROM PRODUCTOS";
             PreparedStatement pre = this.cn.prepareCall(sql);
             res = pre.executeQuery();
             while (res.next()) {                 
             ConstructorProductos pro = new ConstructorProductos();
             pro.setCodigo(res.getInt("IDPRODUCTO"));
             pro.setProducto(res.getString("NOMBRE"));
           
            
          
             listaproducto.add(pro);
            
             }
         } catch (Exception e) {
             throw e;
         }return listaproducto;
     }
   
   public ConstructorProductos BuscarPro(String cod){
        ConstructorProductos producto = new ConstructorProductos();
        String sql = "SELECT * FROM productos WHERE nombre = ?";
        try {
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
               
                producto.setStock(rs.getFloat("STOCK"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
   
 
    public ArrayList listarBoletaVenta(){
      ArrayList<ConstructorDetalle_Venta> lista = new ArrayList<ConstructorDetalle_Venta>();
      try {
         CallableStatement cs= cn.prepareCall("{call SP_LISTARDETALLEVENTA()}");
         ResultSet rs = cs.executeQuery();
         while (rs.next()){
             lista.add(new ConstructorDetalle_Venta(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5)));
              } 
         
     } catch (Exception ex) {
          System.out.println("lista datos: "+ex.getMessage());
     }
      return lista;
  }  
    public boolean ActualizarCaja(float cant, String fecha){
        String sql = "UPDATE CAJA SET MONTO_FINAL = ? WHERE FECHA = ?";
        try {
           
            ps = cn.prepareStatement(sql);
            ps.setFloat(1,cant);
            ps.setString(2, fecha);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
     public ConstructorCaja MostrarCaja(String fecha){
        ConstructorCaja caja = new ConstructorCaja();
        String sql = "SELECT * FROM CAJA WHERE FECHA = ?";
        try {
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            if (rs.next()) {
               
                caja.setMontoFinal(rs.getFloat("MONTO_FINAL"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return caja;
    }
     
     
     
 
     
}

