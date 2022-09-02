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
public class ConstructorVentas {
    private int codigo;
    private String cliente;
    private String usuario;
    private float total;
    private String fecha;

    public ConstructorVentas(int codigo, String cliente, String usuario, float total, String fecha) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.usuario = usuario;
        this.total = total;
        this.fecha = fecha;
    }

 

  
    
    public ConstructorVentas(){
        
    } 

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
