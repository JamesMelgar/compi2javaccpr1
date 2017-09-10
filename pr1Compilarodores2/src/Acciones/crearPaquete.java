
package Acciones;


import java.util.Date;
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
    
    public static void pq_salidadatos(String Mensaje){
        String palabra = pr1compilarodores2.principal2.dj_datos;
        int pos = palabra.length()-2;
        palabra = palabra.substring(0, pos);
         pr1compilarodores2.principal2.dj_datos = palabra + " >> " + Mensaje + " \n #@";
    }
    
    public static void pq_ejecucion(String Mensaje){
        String palabra = pr1compilarodores2.principal2.dj_ejecucion;
        int pos = palabra.length()-2;
        palabra = palabra.substring(0, pos);
        pr1compilarodores2.principal2.dj_ejecucion = palabra + " >> "+Mensaje + " \n #@";
    }
    
    public static void pq_mensaje(String Mensaje){
        String palabra = pr1compilarodores2.principal2.dj_mensaje;
        int pos = palabra.length()-2;
        palabra = palabra.substring(0, pos);
        java.util.Date fecha = new Date();
        Mensaje = fecha + " " + pr1compilarodores2.principal2.usua + " "+ Mensaje; 
        pr1compilarodores2.principal2.dj_mensaje = palabra + " >> " + Mensaje + " \n #@";
    }
    //    [
//	"validar": 1500,
//	"paquete": "Salida_datos",
//	"Mensaje": [ 
//	@# >>Primer mensaje 
//>>segundo mensaje
//>>tercer Mensaje @#
//	]	
//] 
    
    public static String enviar_pqSalidaDatos(){
        String cadena;
        cadena = "[ \n \"validar\": ";
        cadena = cadena + pr1compilarodores2.principal2.Num_aleatorio + ", \n";
        cadena = cadena + " \"paquete\": \"datos\" , \n";
        cadena = cadena + " \"mensaje\": [ \n";
        cadena = cadena + pr1compilarodores2.principal2.dj_datos + " ] \n";
        cadena = cadena + "]";
        return cadena;
    }
    
     public static String enviar_pqejecucion(){
        String cadena;
        cadena = "[ \n \"validar\": ";
        cadena = cadena + pr1compilarodores2.principal2.Num_aleatorio + ", \n";
        cadena = cadena + " \"paquete\": \"exec\" , \n";
        cadena = cadena + " \"mensaje\": [ \n";
        cadena = cadena + pr1compilarodores2.principal2.dj_ejecucion + " ] \n";
        cadena = cadena + "]";
        return cadena;
    }
     
      public static String enviar_pqMensaje(){
        String cadena;
        cadena = "[ \n \"validar\": ";
        cadena = cadena + pr1compilarodores2.principal2.Num_aleatorio + ", \n";
        cadena = cadena + " \"paquete\": \"Mensajes\" , \n";
        cadena = cadena + " \"mensaje\": [ \n";
        cadena = cadena + pr1compilarodores2.principal2.dj_mensaje + " ] \n";
        cadena = cadena + "]";
        return cadena;
    }
    

}
//////////