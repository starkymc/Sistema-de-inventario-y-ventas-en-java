/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaEntidad;

/**
 *
 * @author USER
 */
public class ConstructorDetalle_Venta {
    private int codigo;
    private int producto;
    private int venta;
    private int cantidad;
    private float precio;

    public ConstructorDetalle_Venta(int codigo, int producto, int venta, int cantidad, float precio) {
        this.codigo = codigo;
        this.producto = producto;
        this.venta = venta;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    

  
  
    
    public ConstructorDetalle_Venta(){
        
    } 

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

  

}
