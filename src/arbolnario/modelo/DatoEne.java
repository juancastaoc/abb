/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolnario.modelo;

import java.io.Serializable;

/**
 *
 * @author giovanni
 */
public class DatoEne implements Serializable{
    private String nombre;
    private int id;
    private int edad;

    public DatoEne() {
    }

    public DatoEne(int id) {
        this.id = id;    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    
    
    
}
