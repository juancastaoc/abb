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
 * Serializable: Para que sirve?... Para que ese dato pueda ser guardado en disco o transmitido por la red.
 * Serializar es convertir de java o .net a Json y des serializar es de json a java.
 */
public class Dato implements Serializable {    
    private int numero;
    private int suma;
    
   //Constructor con parametro int
    public Dato(int numero){
        this.numero = numero;
        this.suma = numero;
    }
   

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }
    
    
    
}
