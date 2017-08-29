/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import pr1compilarodores2.Nodo;

public class expresiones {
    
    public static Nodo expresiones(Nodo raiz){
        String cadena;
        Nodo nodo1, nodo2;
        int n1, n2,n3;
        double d1, d2,d3;
        String c1, c2, c3;
        boolean b1, b2, b3;
        if(raiz.getHijos().size()==2){
            Nodo izq = expresiones(raiz.getHijos().get(0));
            Nodo der = expresiones(raiz.getHijos().get(1));
            
          if(raiz.getNombre().equalsIgnoreCase("mas")){   
             //num
              if(izq.getTipo().equalsIgnoreCase("num")){
                  if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n2+n1;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n2+n1;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1+d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      c1 = izq.getNombre();
                      c2 = der.getNombre();
                      cadena= c1 + c2;
                      nodo1=crear_nodo(cadena, "", "texto");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    b1=convertirboolean(izq.getNombre());
                    b2=convertirboolean(der.getNombre());
                    b3=b1 || b2;
                    cadena=convertirString(b3);
                    nodo1=crear_nodo(cadena,"","bool");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n2+n1;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1+d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      c1 = izq.getNombre();
                      c2 = der.getNombre();
                      cadena= c1 + c2;
                      nodo1=crear_nodo(cadena, "", "texto");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op double
              }else if(izq.getTipo().equalsIgnoreCase("double")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1+d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1+d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1+d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      c1 = izq.getNombre();
                      c2 = der.getNombre();
                      cadena= c1 + c2;
                      nodo1=crear_nodo(cadena, "", "texto");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("texto")){
                  if(der.getTipo().equalsIgnoreCase("texto")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op date
              }else if(izq.getTipo().equalsIgnoreCase("datatime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }
              }
              
          }
        }else{
            System.out.println("tipo "+raiz.getTipo());
            System.out.println("valor "+raiz.getValor());
            return raiz;
        }
        return raiz;
    }


public static Nodo crear_nodo(String cadena, String valor, String tipo){
           Nodo nodo1 = new Nodo(cadena);
           nodo1.setValor(valor);
           nodo1.setTipo(tipo);
           nodo1.setNumNodo(gramatica_usu.grausu.contador++);
           return nodo1;
}

public static boolean convertirboolean(String cadena){
    if(cadena.equalsIgnoreCase("0")){
        return false;
    }else{
        return true;
    }
}

public static String convertirString(boolean valor){
    if(valor){
        return "1";
    }else{
        return "0";
    }
}
    
}
