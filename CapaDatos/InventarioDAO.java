/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;
import Conexion.ConexionSQL;
import CapaEntidad.ConstructorInventario;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public class InventarioDAO {
     ConexionSQL oconexion = new ConexionSQL();
    Connection cn=oconexion.abrirConexion();
 /*   
     public List mostrarProducto()throws Exception{
         ResultSet res;
         List listaProductos = new ArrayList();
         try {
             
             String sql="select *from Inventario";
             PreparedStatement pre = this.cn.prepareCall(sql);
             res = pre.executeQuery();
             while (res.next()) {                 
             ConstructorInventario inv = new ConstructorInventario();
             pro.setId(res.getInt("Id_Producto"));
             pro.setNombre(res.getString("Nombre_Producto"));
             pro.setPrecio(res.getFloat("Precio_Unitario"));
             pro.setStock(res.getInt("Stock"));
             pro.setId_unidadmedida(res.getString("Nombre_Medida"));
             pro.setId_categoria(res.getString("Nombre_Categoria"));
            
          
             listaProductos.add(pro);
            
             }
         } catch (Exception e) {
             throw e;
         }return listaProductos;
     }*/
}
