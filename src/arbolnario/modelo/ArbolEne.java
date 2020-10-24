/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolnario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.io.Serializable;

/**
 *
 * @author giovanni
 */
public class ArbolEne implements Serializable{
    private NodoEne raiz;

    public ArbolEne() {
    }

    public ArbolEne(NodoEne raiz) {
        this.raiz = raiz;
    }

    public NodoEne getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoEne raiz) {
        this.raiz = raiz;
    }
    
    
    
    
    
    //Método para adicionar nodo
    public void adicionarNodo(DatoEne dato, NodoEne ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new NodoEne();

        } else {
            if (dato.getId() < ubicacion.getDato().getId()) {
                if (ubicacion.getDato() == null) {
                    ubicacion.setDato(new DatoEne());
                } else {
                    adicionarNodo(dato, ubicacion);
                }
            } else if (dato.getId() > ubicacion.getDato().getId()) {
                if (ubicacion.getDato() == null) {
                    ubicacion.setDato(new DatoEne());
                } else {
                    adicionarNodo(dato, ubicacion);
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
            ubicacion.getDato();
        }
    }
    
}
