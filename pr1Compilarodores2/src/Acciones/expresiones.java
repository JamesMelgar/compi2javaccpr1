/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import static Acciones.accpaquete.imprimir_tabla_simbolos;
import static Acciones.accpaquete.llenar_tabla;
import static Acciones.accpaquete.verificar_parametros_tipos;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import pr1compilarodores2.Nodo;
import static pr1compilarodores2.principal2.paquete;

public class expresiones {
    public static Stack<tablasimbolos> pila = new Stack<tablasimbolos>();
    public static Stack<tablasimbolos> pivote = new Stack<tablasimbolos>();    
    
    
public static Nodo expresiones(Nodo raiz){
        String cadena;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Nodo nodo1, nodo2;
        int n1, n2,n3;
        double d1, d2,d3;
        String c1, c2, c3;
        boolean b1, b2, b3;
        if(raiz.getNombre().equalsIgnoreCase("llamada")){
            Nodo nodo5=llamada_funcion(raiz);
            return nodo5;
        }else if(raiz.getHijos().size()==2){
            Nodo izq = expresiones(raiz.getHijos().get(0));
            Nodo der = expresiones(raiz.getHijos().get(1));
            
          if(raiz.getNombre().equalsIgnoreCase("mas")){   
             //num
              if(izq.getTipo().equalsIgnoreCase("int")){
                  if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1+n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1+n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1+d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      c1 = izq.getNombre();
                      c2 = der.getNombre();
                      cadena= concacadena(c1, c2);
                      nodo1=crear_nodo(cadena, "", "text");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else{ 
                    nodo1=crear_nodo("error","","error");
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
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1+n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1+d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      c1 = izq.getNombre();
                      c2 = der.getNombre();
                      cadena= concacadena(c1, c2);
                      nodo1=crear_nodo(cadena, "", "text");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
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
                  }else if(der.getTipo().equalsIgnoreCase("int")){
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
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      c1 = izq.getNombre();
                      c2 = der.getNombre();
                      cadena=  concacadena(c1, c2);
                      nodo1=crear_nodo(cadena, "", "text");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("text")){
                  if(der.getTipo().equalsIgnoreCase("text")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena= concacadena(c1, c2);;
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=concacadena(c1, c2);
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena = concacadena(c1, c2);
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena= concacadena(c1, c2);
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena= concacadena(c1, c2);
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op date
              }else if(izq.getTipo().equalsIgnoreCase("datetime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena= concacadena(c1, c2);;
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }
              }else if(izq.getTipo().equalsIgnoreCase("error")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              } //***************************************************************************************Menos       
          }else if(raiz.getNombre().equalsIgnoreCase("menos")){
              //num
              if(izq.getTipo().equalsIgnoreCase("int")){
                  if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1-n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1-n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1-d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    nodo1=crear_nodo("", "", "error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1-n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1-d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op double
              }else if(izq.getTipo().equalsIgnoreCase("double")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1-d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1-d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1-d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("error","","error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("text")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1; //op date
              }else if(izq.getTipo().equalsIgnoreCase("datetime") || izq.getTipo().equalsIgnoreCase("date")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("error")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              }     //***********************************************************************************MULTIPLICACION
          }else if(raiz.getNombre().equalsIgnoreCase("mult")){   
             //num
              if(izq.getTipo().equalsIgnoreCase("int")){
                  if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1*n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1*n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    b1=convertirboolean(izq.getNombre());
                    b2=convertirboolean(der.getNombre());
                    b3=b1 && b2;
                    cadena=convertirString(b3);
                    nodo1=crear_nodo(cadena,"","bool");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1*n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
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
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("text")){
                 nodo1=crear_nodo("", "", "error");
                 return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("datetime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("text")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=concacadena(c1, c2);;
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else{
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }
              }else if(izq.getTipo().equalsIgnoreCase("error")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              } //***************************************************************************************Division      
          }else if(raiz.getNombre().equalsIgnoreCase("divi")){   
             //num
              if(izq.getTipo().equalsIgnoreCase("int")){
                  if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1/n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1/n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    nodo1=crear_nodo("", "", "error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1/n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
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
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("text")){
                 nodo1=crear_nodo("", "", "error");
                 return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("datetime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("text")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else{
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }
              }else if(izq.getTipo().equalsIgnoreCase("error")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              } //***************************************************************************************potencia    
          }else if(raiz.getNombre().equalsIgnoreCase("pote")){   
             //num
              if(izq.getTipo().equalsIgnoreCase("int")){
                  if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3= (int) Math.pow(n1, n2);
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=(int) Math.pow(n1, n2);
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3= Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    nodo1=crear_nodo("", "", "error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=(int) Math.pow(n1, n2);
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
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
                     d3=Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("int")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("text")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datetime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("text")){
                 nodo1=crear_nodo("", "", "error");
                 return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("datetime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("text")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena= concacadena(c1, c2);
                    nodo1=crear_nodo(cadena,"","text");
                    return nodo1;
                  }else{
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }
              }else if(izq.getTipo().equalsIgnoreCase("error")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              } //***************************************************************************************igual    
          }else if(raiz.getNombre().equalsIgnoreCase("==")){
               c1=izq.getNombre();
               c2=der.getNombre();
               if(c1.equalsIgnoreCase("NULO")){
                   if(c2.equalsIgnoreCase("NULO")){
                        nodo1=crear_nodo("1","","bool");
                        return nodo1; 
                   }else{
                       nodo1=crear_nodo("0","","bool");
                       return nodo1;
                   }
               }else if(c2.equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1;
               }else if(izq.getTipo().equalsIgnoreCase(der.getTipo())){
                   if(izq.getTipo().equalsIgnoreCase("error")){
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }else if(izq.getTipo().equalsIgnoreCase("date")){
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf.parse(dt1);
                           Date date2 = sdf.parse(dt2);
                           if(date1.compareTo(date2)==0){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       }  
                   }else{
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("0","","bool");
                          return nodo1;
                       }
                    }
               }else if(izq.getTipo().equalsIgnoreCase("int")){
                   if(der.getTipo().equalsIgnoreCase("bool")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("0","","bool");
                          return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("bool")){
                   if(der.getTipo().equalsIgnoreCase("int")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("0","","bool");
                          return nodo1;
                       }
                   }else if(der.getTipo().equalsIgnoreCase("double")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("0","","bool");
                          return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("int")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("0","","bool");
                          return nodo1;
                       }
                   }else if(der.getTipo().equalsIgnoreCase("bool")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("0","","bool");
                          return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }
                //***************************************************************************************diferente   
          }else if(raiz.getNombre().equalsIgnoreCase("!=")){
               c1=izq.getNombre();
               c2=der.getNombre();
               if(c1.equalsIgnoreCase("NULO")){
                   if(c2.equalsIgnoreCase("NULO")){
                        nodo1=crear_nodo("0","","bool");
                        return nodo1; 
                   }else{
                       nodo1=crear_nodo("1","","bool");
                       return nodo1;
                   }
               }else if(c2.equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("1","","bool");
                    return nodo1;
               
               }else if(izq.getTipo().equalsIgnoreCase(der.getTipo())){
                   if(izq.getTipo().equalsIgnoreCase("error")){
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }else{
                       if(c1.equalsIgnoreCase(c2)==false){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("0","","bool");
                          return nodo1;
                       }
                    }
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf.parse(dt1);
                           Date date2 = sdf.parse(dt2);
                           if(date1.compareTo(date2)==0){
                               nodo1=crear_nodo("0","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("1","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
               }else if(izq.getTipo().equalsIgnoreCase("bool")){
                   if(der.getTipo().equalsIgnoreCase("int")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("0","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("1","","bool");
                          return nodo1;
                       }
                   }else if(der.getTipo().equalsIgnoreCase("double")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("0","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("1","","bool");
                          return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("int")){
                   if(der.getTipo().equalsIgnoreCase("double")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("0","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("1","","bool");
                          return nodo1;
                       }
                   }else if(der.getTipo().equalsIgnoreCase("bool")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("0","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("1","","bool");
                          return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("int")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("0","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("1","","bool");
                          return nodo1;
                       }
                   }else if(der.getTipo().equalsIgnoreCase("bool")){
                       if(c1.equalsIgnoreCase(c2)){
                            nodo1=crear_nodo("0","","bool");
                            return nodo1; 
                       }else{
                          nodo1=crear_nodo("1","","bool");
                          return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               } //**************************************************************************************mayor   
          }else if(raiz.getNombre().equalsIgnoreCase(">")){
              if(izq.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(der.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(izq.getTipo().equalsIgnoreCase("int")){
                   if(der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){
                        n1=Integer.parseInt(izq.getNombre());
                        n2=Integer.parseInt(der.getNombre());
                        if(n1>n2){
                            nodo1=crear_nodo("1","","bool");
                        }else{
                            nodo1=crear_nodo("0","","bool");
                        }                 
                        return nodo1;
                   }else if(der.getTipo().equalsIgnoreCase("double")){
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1>d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1>d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("bool")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1>d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("text")){
                   if(der.getTipo().equalsIgnoreCase("text")){ 
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       if (c1.compareTo(c2) > 0){
                           nodo1=crear_nodo("1","","bool");
                           return nodo1;
                       }else{
                           nodo1=crear_nodo("0","","bool");
                           return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("datetime")){
                   if(der.getTipo().equalsIgnoreCase("datetime")){
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf2.parse(dt1);
                           Date date2 = sdf2.parse(dt2);
                           if(date1.after(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf.parse(dt1);
                           Date date2 = sdf.parse(dt2);
                           if(date1.after(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }  //***************************************************************************************menor 
          }else if(raiz.getNombre().equalsIgnoreCase("<")){
               if(izq.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(der.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(izq.getTipo().equalsIgnoreCase("int")){
                   if(der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){
                        n1=Integer.parseInt(izq.getNombre());
                        n2=Integer.parseInt(der.getNombre());
                        if(n1<n2){
                            nodo1=crear_nodo("1","","bool");
                        }else{
                            nodo1=crear_nodo("0","","bool");
                        }                 
                        return nodo1;
                   }else if(der.getTipo().equalsIgnoreCase("double")){
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1<d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1<d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("bool")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1<d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("text")){
                   if(der.getTipo().equalsIgnoreCase("text")){ 
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       if (c1.compareTo(c2) < 0){
                           nodo1=crear_nodo("1","","bool");
                           return nodo1;
                       }else{
                           nodo1=crear_nodo("0","","bool");
                           return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("datetime")){
                   if(der.getTipo().equalsIgnoreCase("datetime")){
                      c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf2.parse(dt1);
                           Date date2 = sdf2.parse(dt2);
                           if(date1.before(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf.parse(dt1);
                           Date date2 = sdf.parse(dt2);
                           if(date1.before(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }  //***************************************************************************************menor o igual
          } else if(raiz.getNombre().equalsIgnoreCase("<=")){
               if(izq.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(der.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(izq.getTipo().equalsIgnoreCase("int")){
                   if(der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){
                        n1=Integer.parseInt(izq.getNombre());
                        n2=Integer.parseInt(der.getNombre());
                        if(n1<=n2){
                            nodo1=crear_nodo("1","","bool");
                        }else{
                            nodo1=crear_nodo("0","","bool");
                        }                 
                        return nodo1;
                   }else if(der.getTipo().equalsIgnoreCase("double")){
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1<=d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1<=d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("bool")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1<=d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("text")){
                   if(der.getTipo().equalsIgnoreCase("text")){ 
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       if (c1.compareTo(c2) <= 0){
                           nodo1=crear_nodo("1","","bool");
                           return nodo1;
                       }else{
                           nodo1=crear_nodo("0","","bool");
                           return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("datetime")){
                   if(der.getTipo().equalsIgnoreCase("datetime")){
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf2.parse(dt1);
                           Date date2 = sdf2.parse(dt2);
                           if(date1.after(date2)==true || date1.equals(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                      c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf.parse(dt1);
                           Date date2 = sdf.parse(dt2);
                           if(date1.after(date2)==true || date1.equals(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               } //***************************************************************************************mayor o igual
          } else if(raiz.getNombre().equalsIgnoreCase(">=")){
               if(izq.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(der.getNombre().equalsIgnoreCase("NULO")){
                    nodo1=crear_nodo("0","","bool");
                    return nodo1; 
               }else if(izq.getTipo().equalsIgnoreCase("int")){
                   if(der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){
                        n1=Integer.parseInt(izq.getNombre());
                        n2=Integer.parseInt(der.getNombre());
                        if(n1>=n2){
                            nodo1=crear_nodo("1","","bool");
                        }else{
                            nodo1=crear_nodo("0","","bool");
                        }                 
                        return nodo1;
                   }else if(der.getTipo().equalsIgnoreCase("double")){
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1>=d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1>=d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("bool")){
                   if(der.getTipo().equalsIgnoreCase("double") || der.getTipo().equalsIgnoreCase("int") || der.getTipo().equalsIgnoreCase("bool")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                        if(d1>=d2){
                            nodo1=crear_nodo("1","","bool");
                            return nodo1;
                        }else{
                            nodo1=crear_nodo("0","","bool");
                            return nodo1;
                        } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("text")){
                   if(der.getTipo().equalsIgnoreCase("text")){ 
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       if (c1.compareTo(c2) >= 0){
                           nodo1=crear_nodo("1","","bool");
                           return nodo1;
                       }else{
                           nodo1=crear_nodo("0","","bool");
                           return nodo1;
                       }
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("datetime")){
                   if(der.getTipo().equalsIgnoreCase("datetime")){
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf2.parse(dt1);
                           Date date2 = sdf2.parse(dt2);
                           if(date1.before(date2)==true || date1.equals(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                       c1=izq.getNombre();
                       c2=der.getNombre();
                       try {
                           String dt1;
                           String dt2;
                           dt1 = c1.replace("'", "");
                           dt2 = c2.replace("'", "");
                           Date date1 = sdf.parse(dt1);
                           Date date2 = sdf.parse(dt2);
                           if(date1.before(date2)==true || date1.equals(date2)==true){
                               nodo1=crear_nodo("1","","bool");
                               return nodo1; 
                            }else{
                               nodo1=crear_nodo("0","","bool");
                               return nodo1;
                            }
                       } catch (ParseException ex) {
                           Logger.getLogger(expresiones.class.getName()).log(Level.SEVERE, null, ex);
                       } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }  //***********************************************************OR
          }else if(raiz.getNombre().equalsIgnoreCase("or")){
            if(izq.getTipo().equalsIgnoreCase("bool")){
               if(der.getTipo().equalsIgnoreCase("bool")){
                    b1=convertirboolean(izq.getNombre());
                    b2=convertirboolean(der.getNombre());
                    b3=b1 || b2;
                    cadena=convertirString(b3);
                    nodo1=crear_nodo(cadena,"","bool");
                    return nodo1;
              }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }
            }else{
                 nodo1=crear_nodo("error","","error");
                 return nodo1;
            }  //************************************************************************and 
          }else if(raiz.getNombre().equalsIgnoreCase("and")){
            if(izq.getTipo().equalsIgnoreCase("bool")){
               if(der.getTipo().equalsIgnoreCase("bool")){
                    b1=convertirboolean(izq.getNombre());
                    b2=convertirboolean(der.getNombre());
                    b3=b1 && b2;
                    cadena=convertirString(b3);
                    nodo1=crear_nodo(cadena,"","bool");
                    return nodo1;
              }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }
            }else{
                 nodo1=crear_nodo("error","","error");
                 return nodo1;
            }   
          }
        }else if(raiz.getHijos().size()==1){
            //*******************************************************************************************Union
            if(raiz.getNombre().equalsIgnoreCase("union")){
                Nodo nuevo = expresiones(raiz.getHijos().get(0));
                if(nuevo.getTipo().equalsIgnoreCase("int")){
                    n1 = Integer.parseInt(nuevo.getNombre());
                    n3 = n1 * -1;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","int");
                    return nodo1;
                }else if(nuevo.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(nuevo.getNombre());
                     d3=d1*-1;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                }else{
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                }
            }else if(raiz.getNombre().equalsIgnoreCase("not")){
                Nodo nuevo = expresiones(raiz.getHijos().get(0));
                if(nuevo.getTipo().equalsIgnoreCase("bool")){
                    if(nuevo.getNombre().equalsIgnoreCase("1")){
                        nodo1=crear_nodo("0", "", "bool");
                    }else{
                        nodo1=crear_nodo("1", "", "bool");
                    }
                    return nodo1;
                }
            }
        }else{
            raiz = acciones_exp(raiz);
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

public static void prueba(){
    tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor");
    tablasimbolos tb1 = new tablasimbolos("uno", "tres", "cuatro");
    tablasimbolos tb2 = new tablasimbolos("Dos", "tres", "cuatro");
    tb.addHijo(tb1);
    tb.addHijo(tb2);
    expresiones.pila.push(tb);
    tablasimbolos tb3 = new tablasimbolos("nombre", "tipo", "valor");
    tablasimbolos tb4 = new tablasimbolos("uno1", "seis", "cuatro");
    tablasimbolos tb5 = new tablasimbolos("Dos2", "tres", "cuatro");
    tb3.addHijo(tb4);
    tb3.addHijo(tb5);
    expresiones.pila.push(tb3);
    expresiones.pila.pop();
    tablasimbolos tb6 = expresiones.pila.pop();
     for(tablasimbolos arbol : tb6.getSiguiente()){
        System.out.println("ojala "+arbol.getNombre()+"tipo "+arbol.getTipo());
        }
        expresiones.pila.push(tb3);
    }

public static String[] cortarCadenaPorcomilla(String cadena) {
    return cadena.split("\\'");
}
     
public static void imprimir_estenodo(Nodo imp, String texto){
   System.out.println(texto+" Nombre:"+imp.getNombre()+" Valor:"+imp.getValor()+" tipo:"+imp.getTipo());
}
     
public static Nodo acciones_exp(Nodo raiz){
    if(raiz.getTipo().equalsIgnoreCase("variable") || raiz.getTipo().equalsIgnoreCase("variable1p")){
        tablasimbolos tb = expresiones.pila.peek();
        boolean valor = false;
        for(tablasimbolos hoja : tb.getSiguiente()){
            if(hoja.getNombre().equalsIgnoreCase(raiz.getNombre())){
//                    imprimir_estenodo(raiz, "raiz");
                if(hoja.getTipo().equalsIgnoreCase("obj")){ //cuando se quiere hacer asignaciones de objeto a objeto
                    Nodo nodo1 = crear_nodo(hoja.getNombre(),"obj", "error");
                    nodo1.setTexto(hoja.getObj());
                    imprimir_estenodo(nodo1, "objeto");
                    return nodo1;
                }else{
                    if(hoja.getValor().equalsIgnoreCase("")){ //si no fue declado
                     System.out.println("error esta variable no a sido asignada");
                     Nodo nodo1=crear_nodo("error","","error");
                     return nodo1;
                    }else{
                        Nodo nodo1 = crear_nodo(hoja.getValor(), hoja.getNombre(), hoja.getTipo());
                        return nodo1; }
                }
            }
        }
    }else if(raiz.getTipo().equalsIgnoreCase("id")
                      || raiz.getTipo().equalsIgnoreCase("idp")
                           || raiz.getTipo().equalsIgnoreCase("Tid") )
    {
        tablasimbolos tb = expresiones.pila.peek();
        boolean valor = false;
        for(tablasimbolos hoja : tb.getSiguiente()){
 
            if(hoja.getNombre().equalsIgnoreCase(raiz.getNombre())){
                
                if(hoja.getValor().equalsIgnoreCase("")){ //si no fue declado
                     System.out.println("error esta variable no a sido asignada");
                     Nodo nodo1=crear_nodo("error","","error");
                     return nodo1;
                 }else{
                        Nodo nodo1 = crear_nodo(hoja.getValor(), hoja.getNombre(), hoja.getTipo());
                        return nodo1; 
                }
            }
        }
    }else if(raiz.getNombre().equalsIgnoreCase("fecha()")){
        String fecha = "";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        fecha = "'"+dateFormat.format(date)+"'";
        Nodo nodo1=crear_nodo(fecha,"","date"); 
        return nodo1;
    }else if(raiz.getNombre().equalsIgnoreCase("fecha_hora()")){
        String fecha = "";
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        fecha = "'"+hourdateFormat.format(date)+"'";
        Nodo nodo1=crear_nodo(fecha,"","datetime"); 
        return nodo1;
    }else if(raiz.getNombre().equalsIgnoreCase("nulo")){
            return raiz;
    }else{
        if(raiz.getTipo().equalsIgnoreCase("text")){
            raiz.setNombre(concacadena(raiz.getNombre(), ""));
        }
//            imprimir_estenodo(raiz, "normal");
        return raiz;
    }
    Nodo nodo1=crear_nodo("error","","error");
    return nodo1;
}

    public static Nodo llamada_funcion(Nodo llamada){
        Nodo master = pr1compilarodores2.principal2.master;
        String base = pr1compilarodores2.principal2.db;    
        Nodo usuario = pr1compilarodores2.principal2.usuarios;
        String usu = pr1compilarodores2.principal2.usua;       
        Nodo db=accpaquete.nodo_buscar_bd(master, base); 
        Nodo fun=accpaquete.nodo_buscar_proce(db, llamada.getValor());
        boolean valor = accpaquete.tiene_permiso(usuario, master, base, pr1compilarodores2.principal2.usua , llamada.getValor());
        if(db != null && fun != null && valor){
            if(fun.getTipo().equalsIgnoreCase("fun")){ 
                Acciones.accpaquete.realizar_ope_para(llamada); //realizo las  expresiones
                valor = verificar_parametros_tipos(llamada, fun.getHijos().get(0));
                if(valor){
                    if(!expresiones.pila.empty()){
                         tablasimbolos pivote = expresiones.pila.peek();
                         tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                         tb.setAmbito(0); //ambito cero
                         expresiones.pila.push(tb);
                         accpaquete.llenar_tabla_concontenido(llamada, pivote);
                     }else{
                        tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                        tb.setAmbito(0); //ambito cero
                        expresiones.pila.push(tb); //ingreso a la pila un tabla de simbolos
                        llenar_tabla(llamada);
                     }
                    Nodo nodo2=accpaquete.fun_tipousql( usuario, master, fun.getHijos().get(1));//verificar que lo retornado sea igual
//                    imprimir_estenodo(fun, "llamada"); //nombre: nombre fun
                    expresiones.pivote.push(expresiones.pila.peek());
                    expresiones.pila.pop();
                    if(nodo2.getValor().equalsIgnoreCase("error")){
                        nodo2=crear_nodo("error","","error");
                        return nodo2;
                    }else if(nodo2.getValor().equalsIgnoreCase("obj")){ //modifique esto
//                             System.out.println("correcto");
//                             imprimir_estenodo(nodo2, "***********");
//                             System.out.println("****"+nodo2.getTexto());
                             return nodo2;
                    }else if(fun.getValor().equalsIgnoreCase(nodo2.getTipo())){
                        //imprimir_tabla_simbolos();
                        System.out.println("correcto");
                        return nodo2;
                    }else{
                        System.out.println("Error retorno de tipos diferentes");
                    }
                        nodo2=crear_nodo("error","","error");
                        return nodo2;
                }else{
                    System.out.println("Parametros incorrectos");
                }
            }else{
                System.out.println("Error esta es un metodo");
            }
        }else{ System.out.println("Error no existe funcion o no tiene permiso");}
        Nodo nodo1=crear_nodo("error","","error");
        return nodo1;
    }
    
    public static String concacadena(String cadena1, String cadena2){
        cadena1=cadena1.replaceAll("\"", "");
        cadena2=cadena2.replaceAll("\"", "");
        String cadena3 = "\""+cadena1+cadena2+"\"";
        return cadena3;
    }
}
