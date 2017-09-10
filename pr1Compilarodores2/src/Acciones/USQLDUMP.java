
package Acciones;
import static Acciones.expresiones.crear_nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import pr1compilarodores2.Nodo;

/**
 *
 * @author James_PC
 */
public class USQLDUMP extends accpaquete {
    
    public static String ruta_usqldump = "C:\\Users\\James_PC\\Documents\\compi2javaccpr1\\DB\\USQLDUMP";
    
    public static void crear_pitacora(String mensaje){
          String ruta;
          String  usuario = pr1compilarodores2.principal2.db;
          String ext = ".udmp";
          String texto="";
          if(usuario.equalsIgnoreCase("")==false){
              ruta = USQLDUMP.ruta_usqldump + "\\" + usuario + ext;
              try {
                  texto = muestraContenido(ruta);
              } catch (IOException ex) {
                  Logger.getLogger(USQLDUMP.class.getName()).log(Level.SEVERE, null, ex);
              }
              texto = texto + mensaje;
              crearArchivo(ruta, texto);
          }
    }
    
    public static void usql_pitacora(String db,String mensaje){
          String ruta;
          String  usuario = pr1compilarodores2.principal2.db;
          String ext = ".udmp";
          String texto="";
          if(usuario.equalsIgnoreCase("")==false){
              ruta = USQLDUMP.ruta_usqldump + "\\" + usuario + ext;
              try {
                  texto = muestraContenido(ruta);
              } catch (IOException ex) {
                  Logger.getLogger(USQLDUMP.class.getName()).log(Level.SEVERE, null, ex);
              }
              texto = texto + mensaje;
              crearArchivo(ruta, texto);
          }
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
    
    public static String muestraContenido(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        String almacenar="";
        File af = new File(archivo);
        crearPaquete.pq_ejecucion("verificando si exite el archivo");
        if(af.exists()){
            FileReader f = new FileReader(archivo);
            try (BufferedReader b = new BufferedReader(f)) {
                while((cadena = b.readLine())!=null) {
                    almacenar = almacenar + cadena + "\n";
                }
            }
        }        
        return almacenar;
    }
    
    public static void backup_usql(Nodo paquete){
        String ruta;
        String ext = ".udmp";
        ruta = USQLDUMP.ruta_usqldump + "\\" + paquete.getValor() + ext;
        String texto = "";
        try {
            texto = muestraContenido2(ruta);
        } catch (IOException ex) {
            Logger.getLogger(USQLDUMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(texto.equalsIgnoreCase("")==false){
            String usu = pr1compilarodores2.principal2.usua;
            if(usu.equalsIgnoreCase("")==false){
                ruta = USQLDUMP.ruta_usqldump + "\\" + paquete.getValor() + "_" + usu + "_" + paquete.getTipo() + ext;
                crearArchivo(ruta, texto);
                crearPaquete.pq_mensaje("Bakup creado exitosamente");
            }else{
                crearPaquete.pq_mensaje("Backup no creado no hay usuario logueado");
            }
        }
    }
    
    public static String muestraContenido2(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        String almacenar="";
        File af = new File(archivo);
        crearPaquete.pq_ejecucion("verificando si exite el archivo .udmp");
        if(af.exists()){
            FileReader f = new FileReader(archivo);
            try (BufferedReader b = new BufferedReader(f)) {
                while((cadena = b.readLine())!=null) {
                    almacenar = almacenar + cadena + "\n";
                }
            }
        }else{
            crearPaquete.pq_mensaje("No existe el archivo .udmp");
        }
        return almacenar;
    }
    
    public static void restaurar_usql(Nodo paquete){
        String usuario = pr1compilarodores2.principal2.usua;
        String db = pr1compilarodores2.principal2.db;
        String ext = ".udmp";
        String ruta = "";
        String texto = "";
        if(usuario.equals("") || db.equals("")){
            crearPaquete.pq_mensaje("Error en restaurur usql no hay usuario o tabla logueadoa en el sistema");
        }else{
            ruta = USQLDUMP.ruta_usqldump + "\\" + db  + "_" + usuario + "_" + paquete.getValor() + ext;
            try {
                texto = muestraContenido2(ruta);
                crearPaquete.pq_ejecucion("Buscando si existe archivo");
                if(texto.equalsIgnoreCase("")==false){
                    ruta = USQLDUMP.ruta_usqldump + "\\" + db + ext;
                    crearArchivo(ruta, texto);
                    crearPaquete.pq_mensaje("Instruccion Restaurar USQLDUMP correcta");
                }else{
                   crearPaquete.pq_mensaje("Error en restaurur usql no hay usuario o tabla logueadoa en el sistema");
                }
            } catch (IOException ex) {
                Logger.getLogger(USQLDUMP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        imprimir_nodo(paquete, "ruta_usqldump");
        
    }
}
