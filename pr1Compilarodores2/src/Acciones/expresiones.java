/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import pr1compilarodores2.Nodo;

public class expresiones {
    public static Stack<tablasimbolos> pila = new Stack<tablasimbolos>();
    
    
    public static Nodo expresiones(Nodo raiz){
        String cadena;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
                    n3=n1+n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1+n2;
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
                    n3=n1+n2;
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
              }else if(izq.getTipo().equalsIgnoreCase("error")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              } //***************************************************************************************Menos       
          }else if(raiz.getNombre().equalsIgnoreCase("menos")){
              //num
              if(izq.getTipo().equalsIgnoreCase("num")){
                  if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1-n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1-n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1-d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    nodo1=crear_nodo("", "", "error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1-n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1-d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
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
                  }else if(der.getTipo().equalsIgnoreCase("num")){
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
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("error","","error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("texto")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1; //op date
              }else if(izq.getTipo().equalsIgnoreCase("datatime") || izq.getTipo().equalsIgnoreCase("date")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("error")){
                  nodo1=crear_nodo("error","","error");
                  return nodo1;
              }     //***********************************************************************************MULTIPLICACION
          }else if(raiz.getNombre().equalsIgnoreCase("mult")){   
             //num
              if(izq.getTipo().equalsIgnoreCase("num")){
                  if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1*n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1*n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
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
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1*n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
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
                     d3=d1*d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
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
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("texto")){
                 nodo1=crear_nodo("", "", "error");
                 return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("datatime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("texto")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
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
              if(izq.getTipo().equalsIgnoreCase("num")){
                  if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1/n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1/n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    nodo1=crear_nodo("", "", "error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=n1/n2;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
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
                     d3=d1/d2;
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
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
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("texto")){
                 nodo1=crear_nodo("", "", "error");
                 return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("datatime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("texto")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
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
              if(izq.getTipo().equalsIgnoreCase("num")){
                  if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3= (int) Math.pow(n1, n2);
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("bool")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=(int) Math.pow(n1, n2);
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3= Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }//op bool 
              }else if(izq.getTipo().equalsIgnoreCase("bool")){
                  if(der.getTipo().equalsIgnoreCase("bool")){
                    nodo1=crear_nodo("", "", "error");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    n3=(int) Math.pow(n1, n2);
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
                    return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("double")){
                     d1=Double.parseDouble(izq.getNombre());
                     d2=Double.parseDouble(der.getNombre());
                     d3=Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
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
                     d3=Math.pow(d1, d2);
                     cadena=String.valueOf(d3);
                     nodo1=crear_nodo(cadena, "", "double");
                     return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("num")){
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
                  }else if(der.getTipo().equalsIgnoreCase("texto")){
                      nodo1=crear_nodo("", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("datatime") || der.getTipo().equalsIgnoreCase("date") ){
                      nodo1=crear_nodo("error", "", "error");
                      return nodo1;
                  }else if(der.getTipo().equalsIgnoreCase("error")){
                    nodo1=crear_nodo("error","","error");
                    return nodo1;
                  }//texto
              }else if(izq.getTipo().equalsIgnoreCase("texto")){
                 nodo1=crear_nodo("", "", "error");
                 return nodo1;
              }else if(izq.getTipo().equalsIgnoreCase("datatime") || izq.getTipo().equalsIgnoreCase("date")){
                  if(der.getTipo().equalsIgnoreCase("texto")){
                    c1=izq.getNombre();
                    c2=der.getNombre();
                    cadena=c1 + c2;
                    nodo1=crear_nodo(cadena,"","texto");
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
               if(c1.equalsIgnoreCase(c2)){
                  nodo1=crear_nodo("1","","bool");
                  return nodo1; 
               }else{
                  nodo1=crear_nodo("0","","bool");
                  return nodo1;
               } //***************************************************************************************diferente   
          }else if(raiz.getNombre().equalsIgnoreCase("!=")){
               c1=izq.getNombre();
               c2=der.getNombre();
               if(c1.equalsIgnoreCase(c2)==false){
                  nodo1=crear_nodo("1","","bool");
                  return nodo1; 
               }else{
                  nodo1=crear_nodo("0","","bool");
                  return nodo1;
               } //**************************************************************************************mayor   
          }else if(raiz.getNombre().equalsIgnoreCase(">")){
               if(izq.getTipo().equalsIgnoreCase("num")){
                   if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    if(n1>n2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    }                 
                    return nodo1;
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                    if(d1>d2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("texto")){
                   if(der.getTipo().equalsIgnoreCase("texto")){ 
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
               }else if(izq.getTipo().equalsIgnoreCase("datatime")){
                   if(der.getTipo().equalsIgnoreCase("datatime")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }  //***************************************************************************************menor 
          }else if(raiz.getNombre().equalsIgnoreCase("<")){
               if(izq.getTipo().equalsIgnoreCase("num")){
                   if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    if(n1<n2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    }                 
                    return nodo1;
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                    if(d1<d2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("texto")){
                   if(der.getTipo().equalsIgnoreCase("texto")){ 
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
               }else if(izq.getTipo().equalsIgnoreCase("datatime")){
                   if(der.getTipo().equalsIgnoreCase("datatime")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               }  //***************************************************************************************menor o igual
          } else if(raiz.getNombre().equalsIgnoreCase("<=")){
               if(izq.getTipo().equalsIgnoreCase("num")){
                   if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    if(n1<=n2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    }                 
                    return nodo1;
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                    if(d1<=d2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("texto")){
                   if(der.getTipo().equalsIgnoreCase("texto")){ 
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
               }else if(izq.getTipo().equalsIgnoreCase("datatime")){
                   if(der.getTipo().equalsIgnoreCase("datatime")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
               }else{
                   nodo1=crear_nodo("error","","error");
                   return nodo1;
               } //***************************************************************************************mayor o igual
          } else if(raiz.getNombre().equalsIgnoreCase(">=")){
               if(izq.getTipo().equalsIgnoreCase("num")){
                   if(der.getTipo().equalsIgnoreCase("num")){
                    n1=Integer.parseInt(izq.getNombre());
                    n2=Integer.parseInt(der.getNombre());
                    if(n1>=n2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    }                 
                    return nodo1;
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("double")){
                   if(der.getTipo().equalsIgnoreCase("double")){ 
                       d1=Double.parseDouble(izq.getNombre());
                       d2=Double.parseDouble(der.getNombre());
                    if(d1>=d2){
                        nodo1=crear_nodo("1","","bool");
                    }else{
                        nodo1=crear_nodo("0","","bool");
                    } 
                   }else{
                       nodo1=crear_nodo("error","","error");
                       return nodo1;
                   }
               }else if(izq.getTipo().equalsIgnoreCase("texto")){
                   if(der.getTipo().equalsIgnoreCase("texto")){ 
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
               }else if(izq.getTipo().equalsIgnoreCase("datatime")){
                   if(der.getTipo().equalsIgnoreCase("datatime")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
               }else if(izq.getTipo().equalsIgnoreCase("date")){
                   if( der.getTipo().equalsIgnoreCase("date")){
                       String cad[] = cortarCadenaPorcomilla(izq.getNombre());
                       String cad2[] = cortarCadenaPorcomilla(der.getNombre());
                       c1=cad[1];
                       c2=cad2[1];
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
                if(nuevo.getTipo().equalsIgnoreCase("num")){
                    n1 = Integer.parseInt(nuevo.getNombre());
                    n3 = n1 * -1;
                    cadena=Integer.toString(n3);
                    nodo1=crear_nodo(cadena,"","num");
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
        }else{
//            imprimir_estenodo(raiz, "normal");
            return raiz;
        }
        Nodo nodo1=crear_nodo("error","","error");
        return nodo1;
    }
}
