/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1compilarodores2;

import gramatica_usu.Token;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */

public class Nodo implements Cloneable{
    private String nombre;
    private ArrayList<Nodo> hijos;
    private String valor;
    private String tipo;
    private int columna;
    private int fila;
    private int numNodo;
    private String tabla;
    private String campo;
    private String obj;
    private String texto;

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    

    public Nodo(String nombre)
    {
        setNombre(nombre);
        hijos = new ArrayList<>();
        setValor("");
        setNumNodo(0);
        setFila(0);
        setColumna(0);
        setTipo("");
        setTexto("");
        setTabla("");
        setCampo("");
        setObj("");
    }

    public Nodo(int columna, int fila) {
        this.columna = columna;
        this.fila = fila;
    }
    
    public void addHijo(Nodo hijo)
    {
        hijos.add(hijo);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getNumNodo() {
        return numNodo;
    }

    public void setNumNodo(int numNodo) {
        this.numNodo = numNodo;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Object clone(){
        Nodo obj=null;
        try{
            obj=(Nodo)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }

       
        return obj;
    }
    
    public Object clone(Nodo objeto){
        Nodo obj=null;
        try{
            obj=(Nodo)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }

        return obj;
    }
    







    
}
