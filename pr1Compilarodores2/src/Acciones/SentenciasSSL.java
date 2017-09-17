
package Acciones;
/**
 * @author James_PC
 */
import java.util.Stack;
import java.util.Collections;
import pr1compilarodores2.Nodo;
import pr1compilarodores2.Recorrer;

public class SentenciasSSL extends manejodetablas{
    
    public static Nodo Sentencia_mientras(Nodo usuarios,Nodo master, Nodo paquete){
        int ambito;
        int display;
        Nodo vacio = new Nodo("");
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
                }else if(seguir.getObj().equalsIgnoreCase("retorno")){
                     return seguir;
                }
            }
            Sacar_ambito();
            nodo8 = expresiones.expresiones(condicion);
        }
        display = tb.getDisplay()-1;  tb.setDisplay(display);        
        return vacio;
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
                            crearPaquete.pq_mensaje("Error se encontro detener dentro de un if");
                        }
                        break;
                    }else if(seguir.getObj().equalsIgnoreCase("retorno")){
                             return seguir;
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
                    }else if(seguir.getObj().equalsIgnoreCase("retorno")){
                             return seguir;
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
    
    public static Nodo sentencia_switch(Nodo usuarios,Nodo master, Nodo paquete){
        int ambito;
        int display;
        Nodo vacio = new Nodo("");
        tablasimbolos tb = expresiones.pila.peek(); //revisa tabla de simbolo
        Nodo condicion = new Nodo("");
        Nodo casos = new Nodo("");
        int seguir=0;
        condicion = clonar(condicion, paquete.getHijos().get(0));
        casos =  clonar(casos, paquete.getHijos().get(1));
        Nodo nodo8 = expresiones.expresiones(condicion); //nombre: valor tipo: tipo
        imprimir_nodo(nodo8, "reporte");
        display = tb.getDisplay()+1;  tb.setDisplay(display);
        for(Nodo Ucaso : casos.getHijos()){
            if(seguir==0){
               if(Ucaso.getNombre().equalsIgnoreCase("caso")){
                   if(nodo8.getTipo().equalsIgnoreCase(Ucaso.getTipo())){
                       if(nodo8.getNombre().equalsIgnoreCase(Ucaso.getValor())){
                           seguir=1;
                           ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);  //agrego un numero mas al ambito
                            for(Nodo recorre : Ucaso.getHijos()){  //recorro las sentencias
                                Nodo seguiendo = intrucciones_ssl(usuarios, master, recorre); //el nodo que devuelve
                                if(seguiendo.getNombre().equalsIgnoreCase("detener")){
                                    seguir=2; //para que ya no siga haciendo nada en el switch
                                    break;
                                }else if(seguiendo.getObj().equalsIgnoreCase("retorno")){
                                    return seguiendo;
                                }
                            }
                            Sacar_ambito();
                       }
                   }else{
                       crearPaquete.pq_ejecucion("Se encontro error de tipo en switch pero se siguio la ejecucion");
                   }
               }else if(Ucaso.getNombre().equalsIgnoreCase("defecto")){
                    ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);
                   for(Nodo recorre : Ucaso.getHijos()){  //recorro las sentencias
                                Nodo seguiendo = intrucciones_ssl(usuarios, master, recorre); //el nodo que devuelve
                                if(seguiendo.getNombre().equalsIgnoreCase("detener")){
                                    seguir=2; //para que ya no siga haciendo nada en el switch
                                    break;
                                }else if(seguiendo.getObj().equalsIgnoreCase("retorno")){
                                    return seguiendo;
                                }
                            }
                            Sacar_ambito();
               }
            }else if(seguir==1){
               if(Ucaso.getNombre().equalsIgnoreCase("caso")){ //verifico que se un caso
                ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);  //agrego un numero mas al ambito
                for(Nodo recorre : Ucaso.getHijos()){  //recorro las sentencias
                    Nodo seguiendo = intrucciones_ssl(usuarios, master, recorre); //el nodo que devuelve
                    if(seguiendo.getNombre().equalsIgnoreCase("detener")){
                        seguir=2; //para que ya no siga haciendo nada en el switch
                        break;
                    }else if(seguiendo.getObj().equalsIgnoreCase("retorno")){
                         return seguiendo;
                    }
                }
                Sacar_ambito();
               }
            }
        }
        display = tb.getDisplay()-1;  tb.setDisplay(display); 
        return vacio;
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
            nodo2.setObj("retorno");
            return nodo2;
        }else if(arbol.getNombre().equalsIgnoreCase("actualizar cond")){
            manejodetablas.Sentencia_Actualizar_cond(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("seleccionar")){
            manejodetablas.Sentencia_seleccionar(usuario, master, temp);
        }else if(arbol.getNombre().equalsIgnoreCase("detener")){
            nuevo.setNombre("detener");
        }else if(arbol.getNombre().equalsIgnoreCase("fun_si")){
            nuevo=Sentencia_Si(usuario,master, temp);
            if(nuevo.getObj().equalsIgnoreCase("retorno")){
                    return nuevo;
            }
        }else if (arbol.getNombre().equalsIgnoreCase("switch")){
                Nodo nodo2 = SentenciasSSL.sentencia_switch(usuario, master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                    return nodo2;
                }
        }else if(arbol.getNombre().equalsIgnoreCase("mientras")){
                Nodo nodo2 = SentenciasSSL.Sentencia_mientras(usuario, master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                   return nodo2;
                }
        }else if(arbol.getNombre().equalsIgnoreCase("fechah")){
                obtenerfechayhora();
        }else if(arbol.getNombre().equalsIgnoreCase("fecha")){
                obtenerfecha();
        }else if(arbol.getNombre().equalsIgnoreCase("ciclo para")){
                Nodo nodo2 = SentenciasSSL.Sentencia_para(usuario, master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                   return nodo2;
                }
        }else{
          
        }
        return nuevo;
    }
    
    public static Nodo Sentencia_para(Nodo usuarios,Nodo master, Nodo paquete){
        int ambito;
        int display;
        Nodo vacio = new Nodo("");
        tablasimbolos tb = expresiones.pila.peek();
        Nodo valor = new Nodo("");
        Nodo cond = new Nodo("");
        Nodo dec = new Nodo("");
        Nodo sentencias = new Nodo("");
        int salir=0;
        valor = clonar(valor, paquete.getHijos().get(0));
        cond =  clonar(cond, paquete.getHijos().get(1));
        dec =  clonar(dec, paquete.getHijos().get(2));
        sentencias = clonar(sentencias, paquete.getHijos().get(3));
        String variable = valor.getNombre();
        Nodo nodo8 = expresiones.expresiones(valor.getHijos().get(0));
        display = tb.getDisplay()+1;  tb.setDisplay(display);
        if(nodo8.getTipo().equalsIgnoreCase("bool")){
            nodo8.setTipo("int");
        }
        if(nodo8.getTipo().equalsIgnoreCase("int")){
            if(devolver_elemento_tb(variable)==null){
                ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);
                agregar_a_tabla(variable, "int", nodo8.getNombre());
                Nodo nodo7 = expresiones.expresiones(cond);
                while(nodo7.getNombre().equalsIgnoreCase("1") && salir==0){ 
                    ambito=tb.getAmbito()+1;   tb.setAmbito(ambito);
                    for(Nodo recorre : sentencias.getHijos()){
                        Nodo seguir = intrucciones_ssl(usuarios, master, recorre);
                        if(seguir.getNombre().equalsIgnoreCase("detener")){
                            salir=1;
                            break;
                        }else if(seguir.getObj().equalsIgnoreCase("retorno")){
                             return seguir;
                        }
                    }
                    Sacar_ambito();
                    tablasimbolos elemento = devolver_elemento_tb(variable); //valor de la tabla a cambiar
                    if(dec.getNombre().equalsIgnoreCase("decremento")){
                        int numero = Integer.valueOf(elemento.getValor());
                        numero = numero - 1;
                        elemento.setValor(Integer.toString(numero));
                    }else{
                        int numero = Integer.valueOf(elemento.getValor());
                        numero = numero + 1;
                        elemento.setValor(Integer.toString(numero));
                    }
                    nodo7 = expresiones.expresiones(cond);
                }
                Sacar_ambito();
            }else{
                 crearPaquete.pq_mensaje("Error en ciclo para esta variable ya existe");
            }
        }else{
            crearPaquete.pq_mensaje("Error en para igualando un tipo distinto");
        }
        display = tb.getDisplay()-1;  tb.setDisplay(display); 
        return vacio;
    }
    
        
}
