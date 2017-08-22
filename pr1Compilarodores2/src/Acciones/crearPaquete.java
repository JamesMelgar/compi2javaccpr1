
package Acciones;

import pr1compilarodores2.Nodo;


public class crearPaquete {
    public static void pa_login(Nodo paquete, boolean valor){
        Nodo temp = paquete;
        String cadena;
        cadena = "[ \n \"validar\": ";
        cadena = cadena + temp.getValor() + ", \n \"login\": [ \n";
        temp = temp.getHijos().get(0);
        cadena = cadena + "\"usuario\":" +"\"" + temp.getNombre() + "\" ,\n";
        cadena = cadena + "\"login\":";
        if(valor == true){
            cadena = cadena + "true \n ] \n ]";
        }else{
           cadena = cadena + "false \n ] \n ]";
        }
        pr1compilarodores2.principal2.textopaquete = cadena;
    }
    
    public static void pa_crear_usuario(Nodo paquete, boolean valor){
        Nodo temp = paquete;
        String cadena;
        cadena = "[ \n \"validar\": ";
        cadena = cadena + temp.getValor() + ", \n \"crearUsuario\": [ \n";
        temp = temp.getHijos().get(0);
        cadena = cadena + "\"usuario\":" +"\"" + temp.getNombre() + "\" ,\n";
        cadena = cadena + "\"crear\":";
        if(valor == true){
            cadena = cadena + "true \n ] \n ]";
        }else{
           cadena = cadena + "false \n ] \n ]";
        }
        pr1compilarodores2.principal2.textopaquete = cadena;    
    }
}
