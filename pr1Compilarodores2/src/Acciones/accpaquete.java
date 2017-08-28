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
    
    public static void tipousql(Nodo paquete, Nodo usuario, Nodo master, Nodo hijo){
        
        for(Nodo arbol : hijo.getHijos()){  
            Nodo temp = arbol;
            if(arbol.getNombre().equalsIgnoreCase("crear usuario") == true){
                crearusuario(usuario, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("usar") == true){
                sentencia_usar(master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("otorgar idp") == true){
                 sentencia_permitir_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("otorgar todos") == true){
                sentencia_permiso_todos(usuario,master,temp);
            }else if(arbol.getNombre().equalsIgnoreCase("denegar todos") == true){
                sentencia_denegar_todos(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("denegar idp") == true){
               sentencia_denegar_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear base") == true){
               sentencia_crear_base(usuario, master, temp);
            }
        }
    }
    
    public static void crearusuario(Nodo usuario, Nodo hijo){
        boolean valor;
        String cadena;
        Nodo temp;
        Nodo nodo1;
        Nodo nodo2;
        
        temp = hijo.getHijos().get(0);
        valor = existeusuario(usuario, temp.getNombre());
        if(valor == false){
             cadena = temp.getValor().substring(1, temp.getValor().length()-1);
             temp.setValor(cadena);
             nodo1 = crearnodo("Nopermiso", " ");
             nodo2 = crearnodo("permiso", " ");
             temp.addHijo(nodo1);
             temp.addHijo(nodo2);
             pr1compilarodores2.principal2.usuarios.addHijo(temp);
             crearPaquete.pa_crear_usuario(hijo, true);
             crearxml.Usuario();
        }else{
            crearPaquete.pa_crear_usuario(hijo, false);
        }
        
    }
    
    public static boolean existeusuario(Nodo usuarios,String texto){
        String tempUsuario;
        Nodo temp;
        temp = usuarios;
        if(texto.equalsIgnoreCase("admin")==true){
            return true;
        }
        for(Nodo hijos : temp.getHijos()){  
            tempUsuario = hijos.getNombre();

            if(texto.equalsIgnoreCase(tempUsuario) == true){
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
    
    public static boolean buscar_bd (Nodo master,String texto){
        Nodo tmp;
        tmp = master;
        for(Nodo arbol : tmp.getHijos()){ 
            if(arbol.getNombre().equalsIgnoreCase(texto) == true){
                return true;
            }
        } 
        return false;
    }
    
    public static Nodo nodo_buscar_bd(Nodo master,String texto){
        for(Nodo arbol : master.getHijos()){ 
            if(arbol.getNombre().equalsIgnoreCase(texto) == true){
                return arbol;
            }
        } 
        return null;
    }
    
    public static Nodo nodo_existeusuario(Nodo usuarios,String texto){
        String tempUsuario;
        if(texto.equalsIgnoreCase("admin")==true){
            return null;
        }
        for(Nodo hijos : usuarios.getHijos()){  
            tempUsuario = hijos.getNombre();
            if(texto.equalsIgnoreCase(tempUsuario) == true){
                    return hijos;   
                }
        }
        return null;
    }
    
    public static void sentencia_usar(Nodo master, Nodo usar){
        boolean valor;
        valor=buscar_bd(master, usar.getValor());
        if(valor == true){
            pr1compilarodores2.principal2.db=usar.getValor();
        }else{
            System.out.println("No");
        }
    }
    
    public static void sentencia_permiso_todos(Nodo usuarios,Nodo master , Nodo permiso){
        int contador=0;
        if(pr1compilarodores2.principal2.usua.equalsIgnoreCase("admin") == true ){
            Nodo persona = nodo_existeusuario(usuarios, permiso.getValor());
            Nodo db = nodo_buscar_bd(master, permiso.getTipo());
            if (persona != null && db != null){
                 //verificar si en la base de datos exite una base de datos con ese nombre
                 Nodo nodo1 = persona.getHijos().get(0);
                 String texto = Integer.toString(nodo1.getHijos().size());
                 for(Nodo hijo : nodo1.getHijos()){
                     //eliminar nodo si exite el nodo
                     if(hijo.getNombre().equalsIgnoreCase(permiso.getTipo())){
                         nodo1.getHijos().remove(contador);
                         break;
                     }
                     ++contador; 
                 }
                 contador=0;
                 //verificar si exite nodo en permiso
                 Nodo nodo2 = persona.getHijos().get(1);
                 for(Nodo hijo : nodo2.getHijos()){
                     //eliminar nodo si exite el nodo
                     if(hijo.getNombre().equalsIgnoreCase(permiso.getTipo())){
                         nodo2.getHijos().remove(contador);
                         break;
                     }
                     ++contador; 
                 } 
                 Nodo tmp1 = new Nodo(permiso.getTipo());
                 tmp1.setNumNodo(++gramatica_xml.graxml.contador);
                 Nodo tmp = new Nodo("Todos");
                 tmp.setNumNodo(++gramatica_xml.graxml.contador);
                 tmp1.addHijo(tmp);
                 nodo2.addHijo(tmp1);
                 crearxml.Usuario();
            }
        }
    }
    
     public static void sentencia_denegar_todos(Nodo usuarios,Nodo master , Nodo permiso){
        int contador=0;
        if(pr1compilarodores2.principal2.usua.equalsIgnoreCase("admin") == true ){
            Nodo persona = nodo_existeusuario(usuarios, permiso.getValor());
            Nodo db = nodo_buscar_bd(master, permiso.getTipo());
            if (persona != null && db != null){
                 //verificar si en la base de datos exite una base de datos con ese nombre
                 Nodo nodo1 = persona.getHijos().get(0);
                 String texto = Integer.toString(nodo1.getHijos().size());
                 for(Nodo hijo : nodo1.getHijos()){
                     //eliminar nodo si exite el nodo
                     if(hijo.getNombre().equalsIgnoreCase(permiso.getTipo())){
                         nodo1.getHijos().remove(contador);
                         break;
                     }
                     ++contador; 
                 }
                 contador=0;
                 //verificar si exite nodo en permiso
                 Nodo nodo2 = persona.getHijos().get(1);
                 for(Nodo hijo : nodo2.getHijos()){
                     //eliminar nodo si exite el nodo
                     if(hijo.getNombre().equalsIgnoreCase(permiso.getTipo())){
                         nodo2.getHijos().remove(contador);
                         break;
                     }
                     ++contador; 
                 } 
                 crearxml.Usuario();
            }
        }
    }
     
    public static String[] cortarCadenaPorPuntos(String cadena) {
        return cadena.split("\\.");
    }
    
    public static boolean tiene_permiso(Nodo usuario,Nodo master,String baseD,String usu,String permiso){
        Nodo persona, db;
        Nodo nodo1;
        persona=nodo_existeusuario(usuario, usu); 
        db=nodo_buscar_bd(master, baseD);
        
        if(persona != null && db != null){
            //verificando en noper
             nodo1 = persona.getHijos().get(0);
             for(Nodo hijos : db.getHijos()){ 
                 if(hijos.getNombre().equalsIgnoreCase(baseD)){
                     for(Nodo primos : hijos.getHijos()){
                         if(primos.getNombre().equalsIgnoreCase(permiso)){
                             return false;
                         }
                     } 
                 }
             }
            //verificando en permisos
             nodo1 = persona.getHijos().get(1);
             for(Nodo hijos : db.getHijos()){ 
                 if(hijos.getNombre().equalsIgnoreCase(baseD)){
                     for(Nodo primos : hijos.getHijos()){
                         if(primos.getNombre().equalsIgnoreCase(permiso)){
                             return true;
                         }if(primos.getNombre().equalsIgnoreCase("todos")){
                             return true;
                         }
                     } 
                 }
             }
        } 
        return false;
    }
    
    public static boolean exite_objusql(Nodo master,String base, String objeto){
        Nodo db;
        db = nodo_buscar_bd(master, base);
        if(db != null){
            for(Nodo hijos : db.getHijos()){
                if(hijos.getNombre().equalsIgnoreCase("procedure")){
                    for(Nodo primo : hijos.getHijos()){
                        if(primo.getNombre().equalsIgnoreCase(objeto)){
                            return true;
                        }
                    }
                }else if(hijos.getNombre().equalsIgnoreCase("objeto")){
                    for(Nodo primo : hijos.getHijos()){
                        if(primo.getNombre().equalsIgnoreCase(objeto)){
                            return true;
                        }
                    }
                }else{
                    if(hijos.getNombre().equalsIgnoreCase(objeto)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void sentencia_permitir_objeto(Nodo usuarios,Nodo master , Nodo permiso){
        int contador=0;
        int con=0;
        boolean valor;
        int prueba=0;
        if(pr1compilarodores2.principal2.usua.equalsIgnoreCase("admin") == true ){
            Nodo persona = nodo_existeusuario(usuarios, permiso.getValor());
            String cadena[] = cortarCadenaPorPuntos(permiso.getTipo());
            Nodo db = nodo_buscar_bd(master, cadena[0]);
            valor = exite_objusql(master, cadena[0], cadena[1]);       
            if (persona != null && db != null && valor==true){
                 Nodo nodo1 = persona.getHijos().get(0);
                 for(Nodo hijo : nodo1.getHijos()){
                     //eliminar nodo si exite el nodo
                     if(hijo.getNombre().equalsIgnoreCase(cadena[0])){
                        for(Nodo primo : hijo.getHijos() ){
                            if(primo.getNombre().equalsIgnoreCase(cadena[1])){
                                  hijo.getHijos().remove(con);
                                  break;
                            }
                            ++con;
                        }
                        break;
                     }
                     ++contador; 
                 }
               contador=0; con=0;
                 //verificar si exite nodo en permiso
                 Nodo nodo2 = persona.getHijos().get(1);
                 for(Nodo hijo : nodo2.getHijos()){
                     if(hijo.getNombre().equalsIgnoreCase(cadena[0])){
                         int numero=0;
                         for(Nodo primo : hijo.getHijos()){
                             if(primo.getNombre().equalsIgnoreCase("todos")){
                                prueba = 1; numero=1;
                             }else if(primo.getNombre().equalsIgnoreCase(cadena[1])){
                                 numero=1; prueba=1;
                             }
                             ++con;
                         }if(numero == 0){
                             Nodo tmp = new Nodo(cadena[1]); //objeto
                             tmp.setNumNodo(++gramatica_xml.graxml.contador);
                             hijo.addHijo(tmp);
                             prueba = 1;
                         }
                         break;
                     }
                     ++contador; 
                } 
                 if(prueba == 0){ 
                      Nodo tmp = new Nodo(cadena[0]); //tabla
                      tmp.setNumNodo(++gramatica_xml.graxml.contador);
                      Nodo tmp1 = new Nodo(cadena[1]); //objeto
                      tmp1.setNumNodo(++gramatica_xml.graxml.contador);
                      tmp.addHijo(tmp1);
                      nodo2.addHijo(tmp);
                 } 
                crearxml.Usuario();
            }
        }
    }
    
    public static void sentencia_denegar_objeto(Nodo usuarios,Nodo master , Nodo permiso){
        int contador=0;
        int con=0;
        boolean valor;
        int prueba=0;
        if(pr1compilarodores2.principal2.usua.equalsIgnoreCase("admin") == true ){
            System.out.println("usuario s "+permiso.getValor());
            Nodo persona = nodo_existeusuario(usuarios, permiso.getValor());
            String cadena[] = cortarCadenaPorPuntos(permiso.getTipo());
            Nodo db = nodo_buscar_bd(master, cadena[0]);
            valor = exite_objusql(master, cadena[0], cadena[1]);       
            if (persona != null && db != null && valor==true){
                Nodo nodo2 = persona.getHijos().get(1); //per
                 for(Nodo hijo : nodo2.getHijos()){
                     if(hijo.getNombre().equalsIgnoreCase(cadena[0])){
                         for(Nodo primo : hijo.getHijos()){
                             if(primo.getNombre().equalsIgnoreCase("todos")){
                                prueba = 1;
                             }else if(primo.getNombre().equalsIgnoreCase(cadena[1])){
                                 hijo.getHijos().remove(con);
                             }
                             ++con;
                         }
                         break;
                     }
                } 
                if(prueba == 1){  
                 Nodo nodo1 = persona.getHijos().get(0);
                 int numero = 0;
                 for(Nodo hijo : nodo1.getHijos()){
                     if(hijo.getNombre().equalsIgnoreCase(cadena[0])){
                        for(Nodo primo : hijo.getHijos() ){
                            if(primo.getNombre().equalsIgnoreCase(cadena[1])){
                                  numero = 1;
                                  break;
                            }
                            ++con;
                        }
                        if(numero==0){
                            Nodo tmp = new Nodo(cadena[1]); //objeto
                            tmp.setNumNodo(++gramatica_xml.graxml.contador);
                            hijo.addHijo(tmp);
                            numero = 1;
                        }
                        break;
                     }
                 }
                 if(numero==0){
                      Nodo tmp = new Nodo(cadena[0]); //tabla
                      tmp.setNumNodo(++gramatica_xml.graxml.contador);
                      Nodo tmp1 = new Nodo(cadena[1]); //objeto
                      tmp1.setNumNodo(++gramatica_xml.graxml.contador);
                      tmp.addHijo(tmp1);
                      nodo1.addHijo(tmp);
                 }
                } 
                crearxml.Usuario();
            }
        }
    }
     
    public static void sentencia_crear_base(Nodo usuarios,Nodo master, Nodo paquete){
        Nodo arbol = paquete.getHijos().get(0);
        String ruta,texto;
        String newruta;
        boolean valor;
        valor=buscar_bd(master, arbol.getNombre());
        if(valor==false){
            ruta = pr1compilarodores2.principal2.ruta_master+"\\"+arbol.getNombre()+"\\db.usac";
            texto=remplazar(ruta);
            String cadena = texto.substring(0, texto.length()-9);
            System.out.println("ruta "+cadena);
            Crearmaster.crearcarpeta(cadena);
            Nodo nodo1=crearnodo(arbol.getNombre(), texto);
            newruta = cadena + "\\\\" +"proce.usac"; 
            texto=newruta;
            Nodo proc=crearnodo("procedure", texto);
            newruta = cadena + "\\\\" +"obj.usac"; 
            texto=newruta;
            Nodo obj=crearnodo("Objeto", newruta);
            nodo1.addHijo(proc);
            nodo1.addHijo(obj);
            master.addHijo(nodo1);
            Crearmaster.master();
       if(pr1compilarodores2.principal2.usua.equalsIgnoreCase("admin")==false){
           Nodo nuevo=nodo_existeusuario(usuarios, pr1compilarodores2.principal2.usua);
           if(nuevo != null){
               Nodo tmp = crearnodo(arbol.getNombre(), "");
               Nodo tmp1 = crearnodo("todos", "");
               valor = tiene_permiso(usuarios, master,arbol.getNombre() ,pr1compilarodores2.principal2.usua,"objeto");
               if(valor==false){
                   Nodo tmp2 = nuevo.getHijos().get(1);
                   tmp.addHijo(tmp1);
                   tmp2.addHijo(tmp);
                   System.out.println("Se creo permiso");
               }
           }
       }
        }
       
    }
    
    public static String remplazar(String cadena){
        String palabra = cadena.replace("\\", "\\\\");
        return palabra;
    }
}
