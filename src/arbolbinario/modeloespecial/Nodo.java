/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modeloespecial;

import java.io.Serializable;

/**
 *
 * @author giovanni
 */
public class Nodo implements Serializable{
    private Dato dato;
    private Nodo izquierda;
    private Nodo derecha;
    
    //Constructor
    public Nodo(Dato dato) {
        this.dato = dato;
    }

    public Dato getDato() {
        return dato;
    }

    public void setDato(Dato dato) {
        this.dato = dato;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
    
    
   // Método para calcular la suma
    public int calcularSuma()
    {
        int acumulador=0;
        acumulador = this.dato.getNumero();
        if(this.izquierda != null)
        {
            acumulador = acumulador + izquierda.getDato().getSuma(); //izquierda.calcularSuma();
        }
        if(this.derecha != null)
        {
           acumulador = acumulador + derecha.getDato().getSuma();//derecha.calcularSuma();
        }
        this.dato.setSuma(acumulador);
        return acumulador;
    }
    
    
}
