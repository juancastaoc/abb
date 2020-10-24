/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolnario.modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class NodoEne implements Serializable {
    private DatoEne dato;
    private List<NodoEne> hijos;

    public NodoEne() {
    }

    public NodoEne(DatoEne dato, List<NodoEne> hijos) {
        this.dato = dato;
        this.hijos = hijos;
    }

    public DatoEne getDato() {
        return dato;
    }

    public void setDato(DatoEne dato) {
        this.dato = dato;
    }

    public List<NodoEne> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoEne> hijos) {
        this.hijos = hijos;
    }
    
    
    
    
    
}
