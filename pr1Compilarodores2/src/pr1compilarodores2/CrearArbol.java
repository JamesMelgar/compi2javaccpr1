
package pr1compilarodores2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James_PC
 */
public class CrearArbol {
    public static String EnderecoArquivo,EnderecoTemporario,bufferIn;
    
    public static Nodo tryusuario(Nodo usuarios){
        String texto;
        try 
       {
           texto = muestraContenido("C:\\Users\\James_PC\\Documents\\compi2javaccpr1\\DB\\usuarios.xml");
           usuarios = archivoUsuarios(usuarios,texto);
           return usuarios;
       } catch (IOException ex)
       {
            Logger.getLogger(principal2.class.getName()).log(Level.SEVERE, null, ex);
            return null;
       }
        
    }
    
    public static Nodo trymaster(Nodo master){
        String texto;
        try 
       {
           texto = muestraContenido("C:\\Users\\James_PC\\Documents\\compi2javaccpr1\\DB\\master.xml");
           master = archivomaster(master,texto);
           return master;
       } catch (IOException ex)
       {
            Logger.getLogger(principal2.class.getName()).log(Level.SEVERE, null, ex);
            return null;
       }
        
    }
    
    public static Nodo archivoUsuarios(Nodo usuarios, String texto) throws IOException
    {
        EnderecoTemporario =  System.getProperty("java.io.tmpdir")+"temp.txt";
        File arquivo = new File(EnderecoTemporario);  
        FileWriter fw = new FileWriter(arquivo);  
        BufferedWriter bw = new BufferedWriter(fw);      
        bw.write(texto);  
        bw.flush();  
        bw.close(); 
                 
        String nomeArq = new String (EnderecoTemporario);
        try
	{
			gramatica_xml.graxml analizador = new gramatica_xml.graxml(new FileInputStream(nomeArq)) ;
			usuarios = analizador.inicio(usuarios); 
                        System.out.println("Analizador: correcto.");
                        return usuarios;
	}
	catch(gramatica_xml.ParseException e)
	{
			System.out.println(e.getMessage());
			System.out.println("Analizador: Se han encontrado errores en el analisis.");
                        return null;
       }
    }
    
     public static Nodo archivomaster(Nodo master, String texto) throws IOException
    {
        EnderecoTemporario =  System.getProperty("java.io.tmpdir")+"temp.txt";
        File arquivo = new File(EnderecoTemporario);  
        FileWriter fw = new FileWriter(arquivo);  
        BufferedWriter bw = new BufferedWriter(fw);      
        bw.write(texto);  
        bw.flush();  
        bw.close(); 
                 
        String nomeArq = new String (EnderecoTemporario);
        try
	{
			gramatica_xml.graxml analizador = new gramatica_xml.graxml(new FileInputStream(nomeArq)) ;
			master = analizador.inicio(master); 
                        System.out.println("Analizador: correcto.");
                        return master;
	}
	catch(gramatica_xml.ParseException e)
	{
			System.out.println(e.getMessage());
			System.out.println("Analizador: Se han encontrado errores en el analisis.");
                        return null;
       }
    }
    
    public static String muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        String almacenar="";
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            almacenar = almacenar + cadena + "\n";
        }
        b.close();
        return almacenar;
    }

}
