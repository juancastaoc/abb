/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;

/**
 *
 * @author carloaiza
 */
public class Nodo {
    
    private int dato;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(int dato) {
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
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
    
    public boolean isHoja()
    {
        return izquierda== null && derecha == null;
    }
    
    
    public boolean isLleno()
    {
        return izquierda!=null && derecha!=null;
    }
     
    /*
    Método para obtener el grado del nodo
    @return int Numero de hijos
*/
    
   public int obtenerGradoNodo()
   {
    int cont = 0;
   if (this.izquierda != null)
   {
      cont++; 
   } 
    if (this.derecha != null)
    {
        cont++;
    }
    return cont;
   }
   
   /*
   Método para calcular la altura de un nodo
   @return int altura de mi hijo mas alto +1
   */
   
   public int obtenerAlturaNodo()
   {
       if (isHoja())
       {
           return 0;
       }
       else
       {
           int altizq = 0, altder = 0;
           if (this.izquierda != null)
           {
               altizq = izquierda.obtenerAlturaNodo();
           }
           if (this.derecha != null)
           {
               altder = derecha.obtenerAlturaNodo();
           }
           //Obtuve las alturas de mis hijos
           // Operación ternaria
   //        if (altizq > altder)
   //       {
   //        return altizq+1;
   //       }
   //       else
   //       {
   //       return altder+1;
   //       }
           
           return altizq > altder ? altizq+1 : altder+1;
           
       }
   
   }
}
