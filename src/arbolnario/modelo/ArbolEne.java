/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolnario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author giovanni
 * @author juancastaño
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
    public void adicionarNodo(DatoEne dato,String id) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new NodoEne(dato);
        } else {
            NodoEne jefe = buscarNodoJefe(id, raiz);
            NodoEne aux = buscarNodoJefe(dato.getId(), raiz);
            if(aux!=null)
                throw new ArbolBinarioException("el id ya existe");
            if(jefe!=null){
                jefe.getHijos().add(new NodoEne(dato));
            }
            else{
                throw new ArbolBinarioException("no existe jefe");
            }
        }
    }
    
    private NodoEne buscarNodoJefe(String id, NodoEne nodo){
        if(nodo!=null){
            if(nodo.getDato().getId().equals(id))
            return nodo;
            else{
                for (int i = 0; i < nodo.getHijos().size(); i++) {
                    NodoEne aux = buscarNodoJefe(id, nodo.getHijos().get(i));
                    if(aux!=null)
                        return aux;
                }
            }
        }
        return null;
    }
    
    public int nominaGrupoEmpleados(String idJefe){
        NodoEne jefe = buscarNodoJefe(idJefe, raiz);
        int totalSalario = calcularNominaGrupo(jefe);
        totalSalario = totalSalario - jefe.getDato().getSalario();
        return totalSalario;
    }
    
    
    public List<String> listaEmpleadosCiudad(String ciudad){
        List<String> empleados = new LinkedList<>();
        adicionarEmpleadosCiudad(empleados, ciudad, raiz);
        return empleados;
    }
    private void adicionarEmpleadosCiudad(List<String> empleados,String ciudad, NodoEne nodo){
        if(nodo!=null){
            if(nodo.getDato().getCiudad().equals(ciudad)){
                empleados.add(nodo.getDato().getNombre());
            }
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                adicionarEmpleadosCiudad(empleados, ciudad, nodo.getHijos().get(i));
            }
        }
    }
    private int calcularNominaGrupo(NodoEne nodo){
        int salario = 0;
        if(nodo!=null){
            salario += nodo.getDato().getSalario();
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                salario+=calcularNominaGrupo(nodo.getHijos().get(i));
            }
        }
        return salario;
    }
    
    private int obtenerSexo(NodoEne nodo, String genero){
        int cantidad = 0;
        if(nodo!=null){
            if(nodo.getDato().getGenero().equals(genero)){
                cantidad += 1;
            }
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                cantidad+=obtenerSexo(nodo.getHijos().get(i),genero);
            }
        }
        return cantidad;
    }
    
    public List<String> cantidadGenero(String id){
        List<String> genero = new LinkedList<>();
        NodoEne jefe = buscarNodoJefe(id, raiz);
        int cantidadHombres = obtenerSexo(jefe, "hombre");
        int cantidadMujeres = obtenerSexo(jefe, "mujer");
        if(jefe.getDato().getGenero().equals("hombre"))
            cantidadHombres-=1;
        else
            cantidadMujeres-=1;
        genero.add("Hombres: "+cantidadHombres);
        genero.add("Mujeres: "+cantidadMujeres);
        return genero;
    }
    
    public String ciudadTrabajo(String id) throws ArbolBinarioException{
        try{
            NodoEne empleado = buscarNodoJefe(id, raiz);
            return empleado.getDato().getCiudad();
        }catch(Exception e){
            throw new ArbolBinarioException("no existe empleado");
        }
    }
    
    public String[] cambiarDependencia(String id,String nuevaDependencia) throws ArbolBinarioException{
        try {
             String [] dependecia= new String[2];
             NodoEne empleado= buscarNodoJefe(id, raiz);
             dependecia[0]=empleado.getDato().getDependencia();
             empleado.getDato().setDependencia(nuevaDependencia);
             dependecia[1]=empleado.getDato().getDependencia();
             return dependecia;
        } catch (Exception e) {
            throw  new ArbolBinarioException("No existe empleado");
        }
    }
    
    public void despedirEmpleado(String id) throws ArbolBinarioException{
        try {
            NodoEne aux = lider(id, raiz);
            NodoEne empleado = buscarNodoJefe(id, raiz);
            if(empleado.getHijos().size()>0){
                empleado.setDato(empleado.getHijos().get(0).getDato());
                empleado.getHijos().remove(0);
            }
            else{
                aux.getHijos().remove(empleado);
            }
        } catch (Exception e) {
            throw  new ArbolBinarioException("No existe empleado");
        }
    }
    
    public void ascender(String id) throws ArbolBinarioException{
        NodoEne aux = lider(id, raiz);
        NodoEne jefe = lider(aux.getDato().getId(), raiz);
        if(aux.getDato().getId().equals(raiz.getDato().getId()))
            throw new ArbolBinarioException("Ya existe un gerente");
        NodoEne empleado = buscarNodoJefe(id, raiz);
        jefe.getHijos().add(new NodoEne(empleado.getDato()));
        aux.getHijos().remove(empleado);
        
    }
    
    private NodoEne lider(String id, NodoEne nodo){
        if(nodo!=null){
            if(nodo.getHijos().size()>0){
                for (int i = 0; i < nodo.getHijos().size(); i++) {
                    if(id.equals(nodo.getHijos().get(i).getDato().getId())){
                        return nodo;
                    }
                    NodoEne aux = lider(id, nodo.getHijos().get(i));
                    if(aux!=null)
                        return aux;
                }
            }
        }
        return null;
    }
    
    private void obtenerpersonasSexo(NodoEne nodo, String genero,List<NodoEne> personas){
        if(nodo!=null){
            if(nodo.getDato().getGenero().equals(genero)){
                personas.add(nodo);
            }
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                obtenerpersonasSexo(nodo.getHijos().get(i),genero,personas);
            }
        }
    }
    
    public List<String> promedioGenero(){
        List<String> promedio = new LinkedList<>();
        List<NodoEne> hombres = new LinkedList<>();
        List<NodoEne> mujeres = new LinkedList<>();
        obtenerpersonasSexo(raiz, "hombre", hombres);
        obtenerpersonasSexo(raiz, "mujer", mujeres);
        int edadHombres = 0;
        int edadMujeres = 0;
        for (int i = 0; i < hombres.size(); i++) {
            edadHombres+=hombres.get(i).getDato().getEdad();
        }
        for (int i = 0; i < mujeres.size(); i++) {
            edadMujeres+=mujeres.get(i).getDato().getEdad();
        }
        edadHombres=edadHombres/hombres.size();
        edadMujeres=edadMujeres/mujeres.size();
        promedio.add("Hombres: "+edadHombres);
        promedio.add("Mujeres: "+edadMujeres);
        return promedio;
    }
    
}
