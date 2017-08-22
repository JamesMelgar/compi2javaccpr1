/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import java.io.FileWriter;
import java.io.PrintWriter;
import pr1compilarodores2.Nodo;

/**
 *
 * @author James_PC
 */
public class crearxml {
    public static void Usuario(){
        String cadena;
        String ruta = "C:\\Users\\James_PC\\Documents\\compi2javaccpr1\\DB\\usuarios.xml";
        Nodo temp = pr1compilarodores2.principal2.usuarios;
        Nodo nodo1;
        
        cadena = "<Arusu> \n";
        for(Nodo hijos : temp.getHijos()){  
           cadena = cadena + " <usu> \n";
           cadena = cadena +  "<nombre> " + hijos.getNombre() + " </nombre> \n";
           cadena = cadena +  "<conta> " + hijos.getValor()+ " </conta> \n";
           cadena = cadena +  "<noper>  \n";
           nodo1 = hijos.getHijos().get(0);
           for(Nodo primos : nodo1.getHijos()){  
               cadena = cadena + "<db>";
               cadena = cadena + primos.getNombre();
               cadena = cadena + "</db> \n";
               for(Nodo nieto : primos.getHijos()){
                   if(nieto.getNombre().equalsIgnoreCase("todos") == true){
                       cadena = cadena + "<Todos> +\n";
                   }else{
                       cadena =  cadena + "<obusu> " + nieto.getNombre() + " </obusu> \n";
                   }
               }
           }
           cadena = cadena +  "</noper> \n";
           cadena = cadena +  "<permiso> \n";
           nodo1 = hijos.getHijos().get(1);
           for(Nodo primos : nodo1.getHijos()){  
               cadena = cadena + "<db>";
               cadena = cadena + primos.getNombre();
               cadena = cadena + "</db> \n";
               for(Nodo nieto : primos.getHijos()){
                   if(nieto.getNombre().equalsIgnoreCase("todos") == true){
                       cadena = cadena + "<Todos> \n";
                   }else{
                       cadena =  cadena + "<obusu> " + nieto.getNombre() + " </obusu> \n";
                   }
               }
           }
           cadena = cadena +  "</permiso> \n";
           cadena = cadena + " </usu> \n";
        }        
        cadena = cadena + " </Arusu>";
        crearArchivo(ruta, cadena);
    }
    
    public static void crearArchivo(String ruta, String archivo){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.println(archivo);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
    }
}
