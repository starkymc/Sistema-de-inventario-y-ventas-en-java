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
public class ConstructorProductos {
    private int codigo;
    private String producto;
    private String categoria;
    private float stock;
    private float pCompra;
    private float pVenta;

    public ConstructorProductos(int codigo, String producto, String categoria, float stock, float pCompra, float pVenta) {
        this.codigo = codigo;
        this.producto = producto;
        this.categoria = categoria;
        this.stock = stock;
        this.pCompra = pCompra;
        this.pVenta = pVenta;
    }
    

  
    
    public ConstructorProductos(){
        
    }   

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public float getpCompra() {
        return pCompra;
    }

    public void setpCompra(float pCompra) {
        this.pCompra = pCompra;
    }

    public float getpVenta() {
        return pVenta;
    }

    public void setpVenta(float pVenta) {
        this.pVenta = pVenta;
    }
}
