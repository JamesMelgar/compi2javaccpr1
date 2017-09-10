/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1compilarodores2;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class clase_Socket extends Thread{
    
    public static int port = 55555;
    
    public clase_Socket(String msg){
        super (msg);
    }
  
    @Override
    public void run(){
        while(true){
            try {
            ServerSocket server= new ServerSocket();
            server.bind(new  InetSocketAddress("127.0.0.1", 55555));
            System.out.println("Esperando cliente");
            Socket cli = server.accept();
            principal2.enviar = "hola Mundo";
            String recibido = "", enviado = "";
            
            OutputStreamWriter outw = new OutputStreamWriter(cli.getOutputStream(), "UTF8");
            InputStreamReader inw = new InputStreamReader(cli.getInputStream(), "UTF8");

            char[] cbuf = new char[512];
            
            System.out.println("Esperando mensaje del cliente en python");
            inw.read(cbuf);

            for (char c : cbuf) {
                recibido += c;
                if (c == 00) {
                    break;
                }
            }

            System.out.println("Cliente dice: " + recibido);
            if(recibido.length()<4){
                outw.write("".toCharArray());
                outw.flush();
            }else{
                principal2.iniciar(recibido);
                if(principal2.textopaquete.equalsIgnoreCase("")==false){
                    System.out.println("Enviar a cliente: >>>" + principal2.textopaquete);
                    outw.write(principal2.textopaquete.toCharArray());
                    outw.flush();
                    principal2.textopaquete = "";
                }else{
                    String pq_datos = Acciones.crearPaquete.enviar_pqSalidaDatos();
                    String pq_ejc = Acciones.crearPaquete.enviar_pqejecucion();
                    String pq_men =Acciones.crearPaquete.enviar_pqMensaje();
                    System.out.println("Enviar a cliente: >>>" + pq_datos);
                    Thread.sleep(500);
                    outw.write(pq_datos.toCharArray());
                    outw.flush();
                    Thread.sleep(500);
                    outw.write(pq_ejc.toCharArray());
                    outw.flush();
                    Thread.sleep(500);
                    System.out.println("pr1compilarodores2.clase_Socket.run()"+pq_men);
                    outw.write(pq_men.toCharArray());
                    outw.flush();
                    String texto ;
                    texto = principal2.dj_mensaje;
                    int pos = texto.length()-2;
                    texto = texto.substring(2, pos);
                    principal2.tx_resultado.setText(texto);
                    
                    principal2.dj_ejecucion = "@# #@";
                    principal2.dj_mensaje = "@# #@";
                    principal2.dj_datos = "@# #@";
                }
                
                
            }
            
            recibido = "";

            cbuf = new char[512];
            server.close();
            
            } catch (IOException ex) {
                Logger.getLogger(clase_Socket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(clase_Socket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void enviar(){
           principal2.hilo.destroy(); 
           principal2.hilo.start();
    }
    
}
