/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario {
    
    
     public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    private Nodo raiz;
    int cant;
    int altura;

     //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    public void adicionarNodo(int dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

        } else {
            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
        }
    }
    
    
     public ArrayList inOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }

    private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(),l);
        }
    }
    
     //imprimir preorden
    
    public ArrayList preOrden() {
        ArrayList l=new ArrayList();
        preOrden(raiz,l);
        return l;
    }

    private void preOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            l.add(reco.getDato() + " ");
            preOrden(reco.getIzquierda(),l);
            preOrden(reco.getDerecha(),l);
        }
    }
    
    
      //imprimir postOrden
    
       public ArrayList postOrden() {
        ArrayList l=new ArrayList();
        postOrden(raiz,l);
        return l;
    }

    private void postOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            postOrden(reco.getIzquierda(),l);
            postOrden(reco.getDerecha(),l);
            l.add(reco.getDato() + " ");
        }
    } 
    
    //Imprimir vista por niveles
    
    public ArrayList impNiveles() {
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }

    private void impNiveles(Nodo reco, int nivel,ArrayList l) {
        if (reco != null) {
            impNiveles(reco.getIzquierda(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDerecha(), nivel + 1, l);
        }
    }
    
        //Imprimir vista por niveles ordenado
    
     String[] niveles;
    public ArrayList imprimirNivel() {
        niveles = new String[altura + 1];
        ArrayList l=new ArrayList();
        imprimirNivel(raiz, 0);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            System.out.println(niveles[i] + " ");
        }
        
        return l;
    }
      public void imprimirNivel(Nodo pivote, int nivel2) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDerecha(), nivel2 + 1);
            imprimirNivel(pivote.getIzquierda(), nivel2 + 1);
        }
    }
    
       //Imprimir arbol vista por hojas
    public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }

    public void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }

    }
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzquierda()== null && x.getDerecha()== null);
    }
    
    
        
     //Imprimir buscar y buscar padre
    
    public boolean buscar(int x) {
        return (buscar(this.raiz, x));

    }
    
       private boolean buscar(Nodo r, int x) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(r.getIzquierda(), x));
        } else if (compara < 0) {
            return (buscar(r.getDerecha(), x));
        } else {
            return (true);
        }
    }

    public int padre(int info) {
        if (info == 0 || this.raiz == null) {
            return 0;
        }
        Nodo x = padre(this.raiz, info);
        if (x == null) {
            return 0;
        }
        return (x.getDato());
    }

    private Nodo padre(Nodo x, int info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzquierda()!= null && x.getIzquierda().getDato()==(info)) || (x.getDerecha()!= null && x.getDerecha().getDato()==(info))) {
            return (x);
        }
        Nodo y = padre(x.getIzquierda(), info);
        if (y == null) {
            return (padre(x.getDerecha(), info));
        } else {
            return (y);
        }
    } 
    
    //Método para eliminar hojas
    
     public void podar() {
        podar(this.raiz);
    }

    private void podar(Nodo x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzquierda())) {
            x.setIzquierda(null);
        }
        if (this.esHoja(x.getDerecha())) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
     //Método para borrar cualquier nodo
    
    public int borrar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }

        Nodo z = borrar(this.raiz, x);
        this.setRaiz(z);
        return x;

    }

    private Nodo borrar(Nodo r, int x) {
        if (r == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            r.setIzquierda(borrar(r.getIzquierda(), x));
        } else if (compara < 0) {
            r.setDerecha(borrar(r.getDerecha(), x));
        } else {
            System.out.println("Encontro el dato:" + x);
            if (r.getIzquierda()!= null && r.getDerecha()!= null) {
                /*
                 *	Buscar el menor de los derechos y lo intercambia por el dato
                 *	que desea borrar. La idea del algoritmo es que el dato a borrar 
                 *	se coloque en una hoja o en un nodo que no tenga una de sus ramas.
                 **/
                Nodo cambiar = buscarMin(r.getDerecha());
                int aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDerecha(borrar(r.getDerecha(), x));
                System.out.println("2 ramas");
            } else {
                r = (r.getIzquierda()!= null) ? r.getIzquierda(): r.getDerecha();
                System.out.println("Entro cuando le faltan ramas ");
            }
        }
        return r;
    }
    
     // Método para buscar minimo
    
    private Nodo buscarMin(Nodo r) {
        for (; r.getIzquierda()!= null; r = r.getIzquierda());
        return (r);
    }
    
    //Método Balance
    
    int subizq = 0;
    int subder = 0;

    public String imprimirBalance() {
         subizq = 0;
         subder = 0;

        Balance(this.raiz, true, 0);
        //System.out.println("lado Izquierdo " + subizq + " Lado Derecho " + subder);
        if (subizq - subder == 0) {
            return ("El balance es: 0 ");
        } else if (subizq - subder == -1) {
            return("El balance es -1, derecha");
        } else if (subizq - subder == 1) {
            return("El balance 1, izquierda");

        } else {
            return("No es balanceado.."
                    + "porque es mas grande el lado "
                    + ((subizq > subder) ? "Izquierdo" : "Derecho"));
        }

    }

    public void Balance(Nodo reco, boolean lado, int i) {

        if (reco != null) {

            if (reco.getDerecha()== null && reco.getIzquierda()== null) {
                if (lado) {
                    subder = (i > subder) ? i : subder;
                } else {
                    subizq = (i > subizq) ? i : subizq;
                }
            }

            Balance(reco.getDerecha(), lado, i + 1);
            if (i == 0) {
                lado = false;
            }
            Balance(reco.getIzquierda(), lado, i + 1);
        }

    }
    
    // Método borrar mayor
    
    public String borrarMayor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha()!= null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    // Método Borrar menor
    
    public String borrarMenor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda()== null) {
                raiz = raiz.getDerecha();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda()!= null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    // Método cambiar valores
    
    public boolean cambiar() {
            cambiar(raiz, 1);
            //System.out.println();
            return true;
    }

    private void cambiar(Nodo reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato() * 3);
            cambiar(reco.getIzquierda(), nivel + 1);
            //System.out.print(reco.getDato() + " Nivel: (" + nivel + ") ,");
            cambiar(reco.getDerecha(), nivel + 1);
        }
    }
    
    // Método obtener el número de ramas
    
    int numeroRamas = 0;
    public ArrayList<String>ObtenerRamaMayor(){
    obtenernumeroRamas(this.raiz, 0);
    return ObtenerRamamayor(this.raiz, 0, "", new ArrayList<String>());
    }
    public void obtenernumeroRamas(Nodo pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzquierda(), contador);
            obtenernumeroRamas(pivote.getDerecha(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }

    public ArrayList<String> ObtenerRamamayor(Nodo pivote, int contador, String dato, ArrayList lista){
        if (pivote != null ) {
            dato+=pivote.getDato()+",";
            contador ++;
            lista=ObtenerRamamayor(pivote.getIzquierda(), contador, dato, lista);
            lista=ObtenerRamamayor(pivote.getDerecha(), contador, dato, lista);
            
            if (contador == this.numeroRamas) {
                lista.add(dato);
            }
        }
        return lista;
    }
    
    // Método cantidad de nodos del arbol
    
    public String cantidadNodos() {
        cant = 0;
        cantidadNodos(raiz);
        return ""+cant;
    }

    private void cantidadNodos(Nodo reco) {
        if (reco != null) {
            cant++;
            cantidadNodos(reco.getIzquierda());
            cantidadNodos(reco.getDerecha());
        }
    }
    
    
    // Método cantidad nodos hoja
    
    public String cantidadNodosHoja() {
        cant = 0;
        cantidadNodosHoja(raiz);
        return ""+cant;
    }
      private void cantidadNodosHoja(Nodo reco) {
        if (reco != null) {
            if (reco.getIzquierda() == null && reco.getDerecha() == null) {
                cant++;
            }
            cantidadNodosHoja(reco.getIzquierda());
            cantidadNodosHoja(reco.getDerecha());
        }
    }
    
    // Método Menor Valor
      
      public String menorValor() {
        cant = 0;
        menorValor(raiz);
        return ""+cant;
    }
      
     private void menorValor(Nodo reco) {
         
        if (reco != null) {
            
            while (reco.getIzquierda() != null) {
                reco = reco.getIzquierda();
            }
            menorValor(reco.getIzquierda());
        }        
    }
    
    
     // Método Mayor Valor
      
      public String mayorValor() {
        cant = 0;
        mayorValor(raiz);
        return ""+cant;
    }
      
     private void mayorValor(Nodo reco) {
         
        if (reco != null) {
            
            while (reco.getIzquierda() != null) {
                reco = reco.getIzquierda();
            }
            menorValor(reco.getIzquierda());
        }        
    } 
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    
    
    
    
    
    public void llenarArbol(String datos) throws ArbolBinarioException
    {
        String[] arrayDatos= datos.split(",");
        for(String cadena: arrayDatos)
        {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }
        
    }

}
