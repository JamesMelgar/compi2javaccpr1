
package Acciones;

import java.util.ArrayList;


/**
 *
 * @author James_PC
 */
public class tablasimbolos {
    private String nombre;
    private ArrayList<tablasimbolos> siguiente;
    private String tipo;
    private String obj;
    private String valor;
    private String extra;
    
    public tablasimbolos(String nombre,String tipo, String valor) {
        super();
        this.nombre = nombre;
        this.tipo = tipo;
        siguiente = new ArrayList<>();
        this.valor = valor;
        setObj("");
        setExtra("");
    }
    
    public ArrayList<tablasimbolos> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ArrayList<tablasimbolos> siguiente) {
        this.siguiente = siguiente;
    }
    
     public void addHijo(tablasimbolos sigui)
    {
        siguiente.add(sigui);
    }
     
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
 
}
