
package Acciones;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import pr1compilarodores2.Nodo;

/**
/**
 *
 * @author James_PC
 */

public class Crearmaster {
    public static String tepruta;
    public static String newruta;
    public static void master(){
        String cadena;
        String ruta = "C:\\Users\\James_PC\\Documents\\compi2javaccpr1\\DB\\master.xml";
        Nodo temp = pr1compilarodores2.principal2.master;
        Nodo nodo1;
        
        cadena = "<maestro> \n";
        for(Nodo hijos : temp.getHijos()){  
           cadena = cadena + "<DB> \n";
           cadena = cadena +  "<nombre>" + hijos.getNombre() + "</nombre> \n";
           cadena = cadena +  "<path>" + hijos.getValor()+ "</path> \n";
           tepruta = hijos.getValor();
           String newcadena="<registro> \n";
           for(Nodo arbol : hijos.getHijos()){
               if(arbol.getNombre().equalsIgnoreCase("procedure")){
                   newcadena = newcadena + "<Procedure> \n";
                   newcadena = newcadena +  "<path> " + arbol.getValor()+ "</path> \n";
                   archivoproce(arbol.getValor(), arbol);
                   newcadena = newcadena + "</Procedure> \n";
               }else if(arbol.getNombre().equalsIgnoreCase("objeto")){
                   newcadena = newcadena + "<Object> \n";
                   newcadena = newcadena +  "<path>" + arbol.getValor()+ "</path> \n";
                   archioobjeto(arbol.getValor(), arbol);
                   newcadena = newcadena + "</Object> \n";
               }else{
                   newcadena = newcadena + "<Tabla> \n";
                   Nodo nodo2 = arbol.getHijos().get(1);
                   newcadena = newcadena +  "<nombre>" + arbol.getNombre()+ "</nombre> \n";
                   newcadena = newcadena +  "<path>" + arbol.getValor()+ "</path> \n";
                   Nodo nodo3 = arbol.getHijos().get(0);
                   archivotabla(arbol.getValor(), nodo3);
                   newcadena = newcadena + "<rows> \n";
                   for(Nodo primo : nodo2.getHijos()){ 
                       newcadena=newcadena + "<"+primo.getValor()+">" +primo.getNombre() + "</"+ primo.getValor()+">";
                       for(Nodo tio : primo.getHijos()){
                           if(tio.getNombre().equalsIgnoreCase("<fk>")){
                               newcadena = newcadena + "<fk>"+tio.getValor()+"  "+tio.getTipo()+"</fk>";
                           }else{
                               newcadena = newcadena+tio.getNombre();
                           }
                       }
                       newcadena=newcadena+"\n";
                   }
                    newcadena = newcadena + "</rows> \n";
                  // System.out.println("nombre"+arbol.getNombre());
                  // 
                   newcadena = newcadena + "</Tabla> \n";
               }
           }
           newcadena = newcadena + "</registro> \n";
           crearArchivo(tepruta, newcadena);
           
           
           
           cadena = cadena + " </db> \n";
        }        
        cadena = cadena + " </maestro>";
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
    
    public static void crearcarpeta(String texto){
        File directorio=new File(texto); 
        directorio.mkdir(); 
    }
    
    public static void archioobjeto(String ruta, Nodo raiz){
        String cadena;
        cadena = "<arobj> \n";
        for(Nodo arbol : raiz.getHijos()){
            cadena = cadena + "<obj> \n";
            cadena = cadena + "<Nombre>"+arbol.getNombre()+"</nombre> \n";
            cadena = cadena + "<attr> \n";
            for(Nodo primo : arbol.getHijos()){
                cadena = cadena + "<"+primo.getValor()+">" +primo.getNombre() + "</"+ primo.getValor()+"> \n";
            }
            cadena = cadena + "</attr> \n";
            cadena = cadena + "</obj> \n";
            
        }
        cadena = cadena + "</arobj>";
        crearArchivo(ruta, cadena);
    }
    
    public static void archivoproce(String ruta, Nodo raiz){
        String cadena;
        cadena = "<archivo_procedimiento> \n";
        for(Nodo arbol : raiz.getHijos()){
            if(arbol.getTipo().equalsIgnoreCase("fun")){
                //<nombre>nombre_proc1</nombre> nombre
                //<retorno>nombre_proc1</retorno> valor
                cadena=cadena+"<fun> \n";
                cadena=cadena+"<nombre>"+arbol.getNombre()+"</nombre> \n";
                cadena=cadena+"<retorno>"+arbol.getValor()+"</retorno> \n";
                cadena=cadena+"<params> \n";
                Nodo nodo1 = arbol.getHijos().get(0);
                for(Nodo primo: nodo1.getHijos()){
                    cadena = cadena + "<"+primo.getValor()+">" +primo.getNombre() + "</"+ primo.getValor()+"> \n";
                }
                cadena=cadena+"</params> \n";
                //<src>%  %</src>
                Nodo nodo2 = arbol.getHijos().get(1);
                cadena=cadena+"<src> \n";
                cadena=cadena+nodo2.getValor()+"\n";
                cadena=cadena+"</src> \n";
                cadena=cadena+"</fun> \n";
            }else{
                 cadena=cadena+"<proc> \n";
                cadena=cadena+"<nombre>"+arbol.getNombre()+"</nombre> \n";
                cadena=cadena+"<params> \n";
                Nodo nodo1 = arbol.getHijos().get(0);
                for(Nodo primo: nodo1.getHijos()){
                    cadena = cadena + "<"+primo.getValor()+">" +primo.getNombre() + "</"+ primo.getValor()+"> \n";
                }
                cadena=cadena+"</params> \n";
                //<src>%  %</src>
                Nodo nodo2 = arbol.getHijos().get(1);
                cadena=cadena+"<src> \n";
                cadena=cadena+nodo2.getValor()+"\n";
                cadena=cadena+"</src> \n";
                cadena=cadena+"</proc> \n";
            }
        }
        crearArchivo(ruta, cadena);
    }
    
    public static void archivotabla(String ruta, Nodo raiz){
        String cadena;
        cadena = "<artabla> \n";
        for(Nodo arbol : raiz.getHijos()){
            cadena = cadena + "<Row> \n";
            for(Nodo primo : arbol.getHijos()){
                if(primo.getNombre().equalsIgnoreCase("obj")){
                    cadena=cadena+"<obj>"+primo.getValor()+"<"+primo.getTipo()+">"+"\n";
                    for(Nodo abue: primo.getHijos()){
                        cadena = cadena + "<"+abue.getNombre()+">" +abue.getValor() + "</"+ abue.getNombre()+"> \n";
                    }
                    cadena=cadena+"</obj> \n";
                }else{
                    cadena = cadena + "<"+primo.getNombre()+">" +primo.getValor() + "</"+ primo.getNombre()+"> \n";
                }
            }
            cadena = cadena + "</Row> \n";
        }
        cadena = cadena+"</artabla>";
        crearArchivo(ruta, cadena);
    }
}
