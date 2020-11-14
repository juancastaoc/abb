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
 * @author juancastaño
 */
public class DatoEne implements Serializable{

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero; 
   }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the teletrabajo
     */
    public boolean isTeletrabajo() {
        return teletrabajo;
    }

    /**
     * @param teletrabajo the teletrabajo to set
     */
    public void setTeletrabajo(boolean teletrabajo) {
        this.teletrabajo = teletrabajo;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the salario
     */
    public int getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(int salario) {
        this.salario = salario;
    }

    /**
     * @return the dependencia
     */
    public String getDependencia() {
        return dependencia;
    }

    /**
     * @param dependencia the dependencia to set
     */
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }
    private String nombre;
    private String id;
    private String ciudad;
    private int salario;
    private String dependencia;
    private String genero;
    private int edad;
    private boolean teletrabajo;
    public DatoEne() {
    }

    public DatoEne(String id) {
        this.id = id;    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
}
