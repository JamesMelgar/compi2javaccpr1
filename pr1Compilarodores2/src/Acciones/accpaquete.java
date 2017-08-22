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
        temp.setValor(paquete.getValor());
        if(temp.getNombre().equalsIgnoreCase("login") == true ){
           login(temp, usuario);
        }else if(temp.getNombre().equalsIgnoreCase("usql") == true){
           tipousql(paquete,usuario,master,temp);
        }else{
            System.out.println(temp.getNombre());
        }
    }
    
    public static boolean login(Nodo paquete, Nodo usuarios){
        Nodo temp;
        Nodo temppaquete;
        String Usuario;
        String contrasena;
        String tempUsuario;
        String tempcontrasena;
        temp = usuarios;
        temppaquete = paquete.getHijos().get(0);

        
        Usuario = temppaquete.getNombre();
        contrasena = temppaquete.getValor();
        System.out.println("Acciones.accpaquete.login()"+Usuario);
        System.out.println("Acciones.accpaquete.login()"+contrasena);
        if(Usuario.equalsIgnoreCase("admin")==true && contrasena.equalsIgnoreCase("admin")==true){
            pr1compilarodores2.principal2.usua = "admin";
            crearPaquete.pa_login(paquete, true);
            System.out.println("Logueo correcto");
            return true;
        }
        for(Nodo hijos : temp.getHijos()){  
            tempUsuario = hijos.getNombre();
            System.out.println("usu "+tempUsuario);
            tempcontrasena =hijos.getValor();
            System.out.println("con "+tempcontrasena);
            if(Usuario.equalsIgnoreCase(tempUsuario) == true){
                if(contrasena.equalsIgnoreCase(tempcontrasena) == true){
                        pr1compilarodores2.principal2.usua = Usuario;
                        crearPaquete.pa_login(paquete, true);
                        System.out.println("Logueo correcto");
                        return true;
                    }
                }
            }
        System.out.println("Logueo incorrecto");
        crearPaquete.pa_login(paquete, false);
        return false;
    }
    
    public static boolean tipousql(Nodo paquete, Nodo usuario, Nodo master, Nodo hijo){
        Nodo temp;
        boolean valor;
        for(Nodo arbol : hijo.getHijos()){  
            if(arbol.getNombre().equalsIgnoreCase("crear usuario") == true){
                temp = arbol;
                crearusuario(usuario, temp);
                return true;
            }
        }
        return true;
    }
    
    public static void crearusuario(Nodo usuario, Nodo hijo){
        boolean valor;
        String cadena;
        Nodo temp;
        Nodo nodo1;
        Nodo nodo2;
        
        temp = hijo.getHijos().get(0);
        valor = existeusuario(usuario, temp);
        if(valor == false){
             cadena = temp.getValor().substring(1, temp.getValor().length()-1);
             temp.setValor(cadena);
             nodo1 = crearnodo("Nopermiso", " ");
             nodo2 = crearnodo("permiso", " ");
             temp.addHijo(nodo1);
             temp.addHijo(nodo2);
             pr1compilarodores2.principal2.usuarios.addHijo(temp);
             crearPaquete.pa_crear_usuario(hijo, true);
        }else{
            crearPaquete.pa_crear_usuario(hijo, false);
        }
        
    }
    
    public static boolean existeusuario(Nodo usuarios,Nodo tipo){
        String tempUsuario;
        Nodo temp;
        temp = usuarios;
        System.out.println("prueba"+tipo.getNombre());
        if(tipo.getNombre().equalsIgnoreCase("admin")==true){
            return true;
        }
        for(Nodo hijos : temp.getHijos()){  
            tempUsuario = hijos.getNombre();

            if(tipo.getNombre().equalsIgnoreCase(tempUsuario) == true){
                    return true;   
                }
        }
        return false;
    }
    
    public static Nodo crearnodo(String cadena, String valor){
           Nodo nodo1 = new Nodo(cadena);
           nodo1.setValor(valor);
           nodo1.setNumNodo(gramatica_usu.grausu.contador++);
           return nodo1;
     }
}
