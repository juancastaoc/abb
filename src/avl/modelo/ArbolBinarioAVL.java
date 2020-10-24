/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinarioAVL {
    
    int cant;
    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    private NodoAVL raiz;
     private int altura;

    //public void adicionarNodo()
    public NodoAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAVL raiz) {
        this.raiz = raiz;
    }

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    public void adicionarNodo(int dato, NodoAVL ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new NodoAVL(dato);

        } else {

            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new NodoAVL(dato));

                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new NodoAVL(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }

            ubicacion.actualizarAltura();
            balancear(ubicacion);

        }

    }

    public ArrayList inOrden() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        inOrden(raiz, l);
        return l;
    }

    private void inOrden(NodoAVL reco, ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(), l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(), l);
        }
    }

    public void llenarArbol(String datos) throws ArbolBinarioException {
        String[] arrayDatos = datos.split(",");
        for (String cadena : arrayDatos) {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }

    }
    
    //imprimir preorden
    
    public ArrayList preOrden() {
        ArrayList l=new ArrayList();
        preOrden(raiz,l);
        return l;
    }

    private void preOrden(NodoAVL reco, ArrayList l) {
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

    private void postOrden(NodoAVL reco, ArrayList l) {
        if (reco != null) {
            postOrden(reco.getIzquierda(),l);
            postOrden(reco.getDerecha(),l);
            l.add(reco.getDato() + " ");
        }
    } 

    
//
//    public void rotarSimpleNuevo(NodoAVL princ, boolean sentido) {
//
//        if (!sentido) {
//             if (princ.getDerecha()!= null) {
//                NodoAVL nodo = princ.getDerecha();
//                princ.setDerecha(new NodoAVL(princ.getDato()));
//                princ.getDerecha().setDerecha(nodo);
//                 try {
//                     // adicionarNodo(nodo.getDato(), princ);
//                     adicionarNodo(nodo.getDato(), princ);
//                     //princ.getDerecha().setDerecha(nodo);
//                 } catch (ArbolBinarioException ex) {
//                     Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//            } else {
//                princ.setDerecha(new NodoAVL(princ.getDato()));
//            }
//            princ.setDato(princ.getIzquierda().getDato());
//            if (princ.getIzquierda().getDerecha()!= null) {
//                NodoAVL nodo = princ.getIzquierda().getDerecha();
//                 princ.setIzquierda(princ.getIzquierda().getIzquierda());
//                 princ.getIzquierda().setDerecha(nodo);
//            } else {
//                princ.setIzquierda(princ.getIzquierda().getIzquierda());
//            }            
//            princ.getDerecha().actualizarAltura();
//        } else {
//            if (princ.getIzquierda() != null) {
//                NodoAVL nodo = princ.getIzquierda();
//                princ.setIzquierda(new NodoAVL(princ.getDato()));
//                princ.getIzquierda().setIzquierda(nodo);
//            } else {
//                princ.setIzquierda(new NodoAVL(princ.getDato()));
//            }
//            princ.setDato(princ.getDerecha().getDato());
//            if (princ.getDerecha().getIzquierda() != null) {
//                NodoAVL nodo = princ.getDerecha().getIzquierda();
//                princ.setDerecha(princ.getDerecha().getDerecha());
//                try {
//                    //princ.getDerecha().setIzquierda(nodo);
//                    adicionarNodo(nodo.getDato(), princ.getDerecha());
//                } catch (ArbolBinarioException ex) {
//                    Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            } else {
//                princ.setDerecha(princ.getDerecha().getDerecha());
//            }            
//            princ.getIzquierda().actualizarAltura();
//        }
//        princ.actualizarAltura();
//    }
//    
//    
    
     public void rotarSimpleNuevo(NodoAVL princ, boolean sentido)  {

        if (!sentido) {
            if (princ.getDerecha() != null) {
                NodoAVL nodo = princ.getDerecha();
                princ.setDerecha(new NodoAVL(princ.getDato()));
                princ.getDerecha().setDerecha(nodo);
            } else {
                princ.setDerecha(new NodoAVL(princ.getDato()));
            }
            princ.setDato(princ.getIzquierda().getDato());
            if (princ.getIzquierda().getDerecha() != null) {
                NodoAVL nodo = princ.getIzquierda().getDerecha();
                princ.setIzquierda(princ.getIzquierda().getIzquierda());
                try {
                    adicionarNodo(nodo.getDato(), princ);
//                princ.getIzquierda().setDerecha(nodo);
                } catch (ArbolBinarioException ex) {
                    Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                princ.setIzquierda(princ.getIzquierda().getIzquierda());
            }
            princ.getDerecha().actualizarAltura();
        } else {
            if (princ.getIzquierda() != null) {
                NodoAVL nodo = princ.getIzquierda();
                princ.setIzquierda(new NodoAVL(princ.getDato()));
                princ.getIzquierda().setIzquierda(nodo);
            } else {
                princ.setIzquierda(new NodoAVL(princ.getDato()));
            }
            princ.setDato(princ.getDerecha().getDato());
            if (princ.getDerecha().getIzquierda() != null) {
                NodoAVL nodo = princ.getDerecha().getIzquierda();
                princ.setDerecha(princ.getDerecha().getDerecha());
                try {
                    adicionarNodo(nodo.getDato(), princ);
//                princ.getDerecha().setIzquierda(nodo);
                } catch (ArbolBinarioException ex) {
                    Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                princ.setDerecha(princ.getDerecha().getDerecha());
            }
            princ.getIzquierda().actualizarAltura();
        }
        princ.actualizarAltura();
    }

    public void balancear(NodoAVL principal) {
        if (!principal.esVacio()) {
            int fe = principal.obtenerFactorEquilibrio();
            boolean signo = true;
            if (fe < 0) {
                signo = false;
                fe = fe * -1;
            }
            if (fe >= 2) {
                //Esta desequilibrado
                //hacia donde
                if (signo) {
                    //Desequilibrio a la derecha
                    //Valido desequilibrio simple a la izq
                    if (principal.getDerecha().obtenerFactorEquilibrio() > 0) {
                        //Desequilibrio simple - Rotacion simple
                        rotarSimpleNuevo(principal, signo);

                    } else {
                        //Desequilibrio doble - Rotación doble
                        rotarSimpleNuevo(principal.getDerecha(), false);
                        rotarSimpleNuevo(principal, true);
                    }
                } else {
                    //Desequilibrio a la izquierda
                    //Valido desequilibrio simple a la izq
                    if (principal.getIzquierda().obtenerFactorEquilibrio() < 0) {
                        rotarSimpleNuevo(principal, signo);
                    } else {
                        //Tengo un zig zag
                        //rotar doble
                        rotarSimpleNuevo(principal.getIzquierda(), true);
                        rotarSimpleNuevo(principal, false);
                    }
                }
            }
        }

    }

 //Imprimir vista por niveles ordenado
    
     
    public ArrayList imprimirNivel() {
        ArrayList l=new ArrayList();
        if (raiz != null)
        {
        String[] niveles = new String[raiz.obtenerAlturaNodo() + 1];        
        imprimirNivel(raiz, 0, niveles);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            System.out.println(niveles[i] + " ");
        }
        }
        return l;
    }
      public void imprimirNivel(NodoAVL pivote, int nivel2, String[] niveles) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDerecha(), nivel2 + 1, niveles);
            imprimirNivel(pivote.getIzquierda(), nivel2 + 1, niveles);
        }
    }
    
       //Imprimir arbol vista por hojas
    public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }

    public void getHojas(NodoAVL r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }

    }
    protected boolean esHoja(NodoAVL x) {
        return (x != null && x.getIzquierda()== null && x.getDerecha()== null);
    }
    
    
        
     //Imprimir buscar y buscar padre
    
    public boolean buscar(int x) {
        return (buscar(this.raiz, x));

    }
    
       private boolean buscar(NodoAVL r, int x) {
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
        NodoAVL x = padre(this.raiz, info);
        if (x == null) {
            return 0;
        }
        return (x.getDato());
    }

    private NodoAVL padre(NodoAVL x, int info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzquierda()!= null && x.getIzquierda().getDato()==(info)) || (x.getDerecha()!= null && x.getDerecha().getDato()==(info))) {
            return (x);
        }
        NodoAVL y = padre(x.getIzquierda(), info);
        if (y == null) {
            return (padre(x.getDerecha(), info));
        } else {
            return (y);
        }
    } 
    
    //Método para borrar por niveles  
    
    
    public void borrarNivel(int NivelDeseado) throws ArbolBinarioException, Exception {                
                if (NivelDeseado > getAltura()){
        ArrayList <Integer> nodosNivel=new ArrayList();
        buscarNodosNivel(raiz, 1, nodosNivel, NivelDeseado);
        for (int i = 0; i <nodosNivel.size(); i++) {
            borrar(nodosNivel.get(i));        
        }              
      } 
       else {
                throw new ArbolBinarioException("No esta");
            }
                
    }
    
    private void buscarNodosNivel(NodoAVL reco, int nivel,ArrayList nodos, int nivelDeseado) throws Exception{
        if(nivel==nivelDeseado && reco!=null){
            nodos.add(reco.getDato());
        }
        else if(raiz.obtenerAlturaNodo()<nivelDeseado){
            throw new Exception();
        }
        else if(reco!=null){
            buscarNodosNivel(reco.getIzquierda(), nivel+1, nodos, nivelDeseado);
            buscarNodosNivel(reco.getDerecha(), nivel+1, nodos, nivelDeseado);
            
        }
    }
    //Método para eliminar hojas
    
     public void podar() {
        podar(this.raiz);
    }

    private void podar(NodoAVL x) {
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
        x.actualizarAltura();
        balancear(x);
    }
    
     //Método para borrar cualquier nodo
    
    public int borrar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }

        NodoAVL z = borrar(this.raiz, x);
        this.setRaiz(z);
        return x;

    }

    private NodoAVL borrar(NodoAVL r, int x) {
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
            NodoAVL padre = padre(this.raiz, x);
            if (r.getIzquierda()!= null && r.getDerecha()!= null) {
                /*
                 *	Buscar el menor de los derechos y lo intercambia por el dato
                 *	que desea borrar. La idea del algoritmo es que el dato a borrar 
                 *	se coloque en una hoja o en un nodo que no tenga una de sus ramas.
                 **/
                NodoAVL cambiar = buscarMin(r.getDerecha());
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
    
    private NodoAVL buscarMin(NodoAVL r) {
        for (; r.getIzquierda()!= null; r = r.getIzquierda());
        return (r);
    }
    
    int numeroRamas = 0;
    
    public ArrayList<String>ObtenerRamaMayor(){
    obtenernumeroRamas(this.raiz, 0);
    return ObtenerRamamayor(this.raiz, 0, "", new ArrayList<String>());
    }
    private void obtenernumeroRamas(NodoAVL pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzquierda(), contador);
            obtenernumeroRamas(pivote.getDerecha(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }

    private ArrayList<String> ObtenerRamamayor(NodoAVL pivote, int contador, String dato, ArrayList lista){
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
    // Método borrar mayor
    
    public String borrarMayor() {
        NodoAVL reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                NodoAVL anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha()!= null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
                anterior.actualizarAltura();
                balancear(anterior);
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    // Método Borrar menor
    
    public String borrarMenor() {
        NodoAVL reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda()== null) {
                raiz = raiz.getDerecha();
            } else {
                NodoAVL anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda()!= null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
                anterior.actualizarAltura();
                balancear(anterior);
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

    private void cambiar(NodoAVL reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato() * 3);
            cambiar(reco.getIzquierda(), nivel + 1);
            //System.out.print(reco.getDato() + " Nivel: (" + nivel + ") ,");
            cambiar(reco.getDerecha(), nivel + 1);
        }
    }
    
    // Método cantidad de nodos del arbol
    
    public String cantidadNodos() {
        cant = 0;
        cantidadNodos(raiz);
        return ""+cant;
    }

    private void cantidadNodos(NodoAVL reco) {
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
      private void cantidadNodosHoja(NodoAVL reco) {
        if (reco != null) {
            if (reco.getIzquierda() == null && reco.getDerecha() == null) {
                cant++;
            }
            cantidadNodosHoja(reco.getIzquierda());
            cantidadNodosHoja(reco.getDerecha());
        }
    }
    
    // Método Menor Valor
      
      public String menorValor() throws ArbolBinarioException {
          
        String menor = "0";
        if (raiz == null){
            return menor;
        }
        else{
             ArrayList <String> inOrden = inOrden();
             menor = inOrden.get(0);
       
        return menor;
        }       
    }
       
     // Método Mayor Valor
      
      public String mayorValor() throws ArbolBinarioException {
        String mayor = "0";
        if (raiz == null){
            return mayor;
        }
        else{
             ArrayList <String> inOrden = inOrden();
             mayor = inOrden.get(inOrden.size()-1);
       
        return mayor;
        }
    }
}
