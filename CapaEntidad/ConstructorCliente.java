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
public class ConstructorCliente {
    private int codigo;
    private String nombre;
 
    private int documento;
    private String direccion;
    private int telefono;

    public ConstructorCliente(int codigo, String nombre,  int documento, String direccion, int telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
       
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    
    public ConstructorCliente(){
        
    } 

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
