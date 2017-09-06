
package Acciones;
/**
 * @author James_PC
 */
import java.util.Stack;
import java.util.Collections;
import pr1compilarodores2.Nodo;
import pr1compilarodores2.Recorrer;

public class SentenciasSSL extends manejodetablas{
    
    public static void Sentencia_mientras(Nodo usuarios,Nodo master, Nodo paquete){
        int ambito;
        int display;
        tablasimbolos tb = expresiones.pila.peek();
        Nodo condicion = new Nodo("");
        Nodo instrucc = new Nodo("");
        int salir=0;
        condicion = clonar(condicion, paquete.getHijos().get(0));
        instrucc =  clonar(instrucc, paquete.getHijos().get(1));

        display = tb.getDisplay()+1;  tb.setDisplay(display);
        Nodo nodo8 = expresiones.expresiones(condicion);
        while(nodo8.getNombre().equalsIgnoreCase("1") && salir==0){
            ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);
            
            for(Nodo recorre : instrucc.getHijos()){
                Nodo seguir = intrucciones_ssl(usuarios, master, recorre);
                if(seguir.getNombre().equalsIgnoreCase("detener")){
                    salir=1;
                    break;
                }
            }
            Sacar_ambito();
            nodo8 = expresiones.expresiones(condicion);
        }
        display = tb.getDisplay()-1;  tb.setDisplay(display);        
    }
    
    public static Nodo Sentencia_Si(Nodo usuarios,Nodo master, Nodo paquete){
        Nodo nuevo = new Nodo("");
        int ambito;
        tablasimbolos tb = expresiones.pila.peek();
        Nodo condicion = new Nodo("");
        Nodo instrucc = new Nodo("");
        condicion = clonar(condicion, paquete.getHijos().get(0));
        instrucc =  clonar(instrucc, paquete.getHijos().get(1));
        Nodo nodo8 = expresiones.expresiones(condicion);
        if(nodo8.getNombre().equalsIgnoreCase("1")){
            ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);
            for(Nodo recorre : instrucc.getHijos()){
                    Nodo seguir = intrucciones_ssl(usuarios, master, recorre);
                    if(seguir.getNombre().equalsIgnoreCase("detener")){
                        if(tb.getDisplay()>0){
                            nuevo.setNombre("detener");
                            Sacar_ambito();
                            return nuevo;
                        }else{
                            System.out.println("Este detener no esta dentro de un if o un break");
                        }
                        break;
                    }
                }
        }else{
            if(paquete.getHijos().size()==3){
                ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);
                for(Nodo recorre : paquete.getHijos().get(2).getHijos()){
                    Nodo seguir = intrucciones_ssl(usuarios, master, recorre);
                    if(seguir.getNombre().equalsIgnoreCase("detener")){
                        if(tb.getDisplay()>0){
                            nuevo.setNombre("detener");
                            Sacar_ambito();
                            return nuevo;
                        }else{
                            System.out.println("Este detener no esta dentro de un if o un break");
                        }
                        break;
                    }
                }
            }
        }
        
        return nuevo;
    }
    
    public static void Sacar_ambito(){
        int ambito;
        tablasimbolos tb = expresiones.pila.peek();
        ambito=tb.getAmbito();
        int contador=0;
        int numero=tb.getSiguiente().size()-1;
        for (int i = numero; i >= 0; i--) {
             if(tb.getSiguiente().get(i).getAmbito()==ambito){
                   tb.getSiguiente().remove(i);
             }
        }
        ambito=tb.getAmbito()-1;   tb.setAmbito(ambito);
    }
    
    public static Nodo intrucciones_ssl(Nodo usuario,Nodo master, Nodo arbol){
        Nodo nuevo = new Nodo("");
        Nodo temp = arbol;
        if(arbol.getNombre().equalsIgnoreCase("crear usuario") == true){
           crearusuario(usuario, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("usar") == true){
            sentencia_usar(master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("otorgar idp") == true){
             sentencia_permitir_objeto(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("otorgar todos") == true){
            sentencia_permiso_todos(usuario,master,temp);
        }else if(arbol.getNombre().equalsIgnoreCase("denegar todos") == true){
            sentencia_denegar_todos(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("denegar idp") == true){
           sentencia_denegar_objeto(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("crear base") == true){
           sentencia_crear_base(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("crear objeto") == true){
            sentencia_crear_objeto(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("crear proce") == true){
            sentencia_crear_proce(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("crear funcion") == true){
            sentencia_crear_funcion(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("crear tabla") == true){
            sentencia_crear_tabla(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("imprimir") == true){
            sentencia_imprimir(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("llamada") == true){
            sentencia_llamada(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("declarar") == true){
            sentencia_declarar(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("asignar") == true){
            sentencia_asignar(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("retorno") == true){
            Nodo nodo2 = sentencia_Retorno(usuario, master, temp);   
            imprimir_nodo(nodo2, "texto****");
            return nodo2;
        }else if(arbol.getNombre().equalsIgnoreCase("actualizar cond")){
            manejodetablas.Sentencia_Actualizar_cond(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("seleccionar")){
            manejodetablas.Sentencia_seleccionar(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("Mientras")){
            Sentencia_mientras(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("detener")){
            nuevo.setNombre("detener");
        }else if(arbol.getNombre().equalsIgnoreCase("fun_si")){
            nuevo=Sentencia_Si(usuario,master, temp);
        }else{
          
        }
        return nuevo;
    }
    
        
}
