/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1compilarodores2;

import gramatica_usu.Token;
import java.util.ArrayList;

/**
 *
 * @author James
 */

public class Nodo {
    private String nombre;
    private ArrayList<Nodo> hijos;
    private String valor;
    private String tipo;
    private int columna;
    private int fila;
    private int numNodo;
    private String texto;

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
    


}
