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
            System.out.println("Enviar a cliente: >>>" + principal2.enviar);

            outw.write(principal2.enviar.toCharArray());
            outw.flush();
            recibido = "";

            cbuf = new char[512];
            server.close();
            
            } catch (IOException ex) {
                Logger.getLogger(clase_Socket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void enviar(){
           principal2.hilo.destroy(); 
           principal2.hilo.start();
    }
    
}
