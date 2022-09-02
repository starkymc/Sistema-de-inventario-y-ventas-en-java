    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*; 

/**
 *
 * @author USER
 */
public class ConexionSQL {
        Connection cn=null;


public Connection abrirConexion(){
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BD_VENTAS;user=sa;password=123");
        
    } catch (Exception error) {
        System.out.println("Error Abrir:"+error.getMessage());
    }
    return cn;
    }

 
 
  
public void cerrarConexion(){
    try {
        cn.close();
        
    } catch (Exception e) {
        System.out.println("Error al cerrar:"+e.getMessage());
    }
}
}
