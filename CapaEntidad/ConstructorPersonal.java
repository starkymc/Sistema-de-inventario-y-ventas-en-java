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
public class ConstructorPersonal {
    private int codigo;
    private String nombre;
    private int docum;
    private int telefono;
    private float paga;

    public ConstructorPersonal(int codigo, String nombre, int docum, int telefono, float paga) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.docum = docum;
        this.telefono = telefono;
        this.paga = paga;
    }


    public ConstructorPersonal(){
        
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

    public int getDocum() {
        return docum;
    }

    public void setDocum(int docum) {
        this.docum = docum;
    }


    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public float getPaga() {
        return paga;
    }

    public void setPaga(float paga) {
        this.paga = paga;
    }
}
