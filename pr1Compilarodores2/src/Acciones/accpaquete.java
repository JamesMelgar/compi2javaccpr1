/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;
import pr1compilarodores2.Nodo;

public class accpaquete {
    
    public static void tipopaquete(Nodo paquete, Nodo usuario, Nodo master){
        Nodo temp = paquete;
        temp = temp.getHijos().get(0);
        if(temp.getNombre().equalsIgnoreCase("login") == true ){
           login(temp, usuario);
        }else{
            System.out.println(temp.getNombre());
        }
    }
    
    public static boolean login(Nodo paquete, Nodo usuarios){
        Nodo temp;
        String Usuario;
        String contrasena;
        String tempUsuario;
        String tempcontrasena;
        temp = usuarios;
        paquete = paquete.getHijos().get(0);
        Usuario = paquete.getNombre();
        contrasena = paquete.getValor();
        
       
        for(Nodo hijos : temp.getHijos()){  
            tempUsuario = hijos.getNombre();
            System.out.println("usu "+tempUsuario);
            tempcontrasena =hijos.getValor();
            System.out.println("con "+tempcontrasena);
            if(Usuario.equalsIgnoreCase(tempUsuario) == true){
                if(contrasena.equalsIgnoreCase(tempcontrasena) == true){
                        pr1compilarodores2.principal2.usua = Usuario;
                        System.out.println("Logueo correcto");
                        return true;
                    }
                }
            }
        System.out.println("Logueo incorrecto");
        return false;
    }
}
