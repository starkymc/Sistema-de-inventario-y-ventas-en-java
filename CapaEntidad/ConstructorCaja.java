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
public class ConstructorCaja {
    private int codigo;
  
    private float montoFinal;
    private String fecha;

    public ConstructorCaja(int codigo, float montoFinal, String fecha) {
        this.codigo = codigo;
        
        this.montoFinal = montoFinal;
        this.fecha = fecha;
    }

    public ConstructorCaja(){
        
    } 

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

   

    public float getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(float montoFinal) {
        this.montoFinal = montoFinal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
