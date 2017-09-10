/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;
import static Acciones.expresiones.crear_nodo;
import pr1compilarodores2.Nodo;

public class accpaquete {

    public static void tipopaquete(Nodo paquete, Nodo usuario, Nodo master){
        Nodo temp = paquete;
        temp = temp.getHijos().get(0);
        temp.setValor(paquete.getValor());
        pr1compilarodores2.principal2.Num_aleatorio = paquete.getValor();
        System.out.println("Acciones.accpaquete.tipopaquete()");
        if(temp.getNombre().equalsIgnoreCase("login") == true ){
           login(temp, usuario);
        }else if(temp.getNombre().equalsIgnoreCase("usql") == true){
           tipousql(usuario,master,temp);
        }else if(temp.getNombre().equalsIgnoreCase("reporte") == true){
            tipopaq(usuario,master,temp);
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
    
    public static void tipousql( Nodo usuario, Nodo master, Nodo hijo){
        
        for(Nodo arbol : hijo.getHijos()){  
            Nodo temp = arbol;
            if(arbol.getNombre().equalsIgnoreCase("crear usuario") == true){
                USQLDUMP.crear_pitacora(">> Instruccion crear usuario ");
                crearusuario(usuario, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("usar") == true){
                sentencia_usar(master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("otorgar idp") == true){
                USQLDUMP.crear_pitacora(">> Instruccion otorgar permiso objeto");
                 sentencia_permitir_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("otorgar todos") == true){
                USQLDUMP.crear_pitacora(">> Instruccion otorgar permiso DB");
                sentencia_permiso_todos(usuario,master,temp);
            }else if(arbol.getNombre().equalsIgnoreCase("denegar todos") == true){
                USQLDUMP.crear_pitacora(">> Instruccion denegar todos los permisos");
                sentencia_denegar_todos(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("denegar idp") == true){
                USQLDUMP.crear_pitacora(">> Instruccion denegar permiso objeto");
               sentencia_denegar_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear base") == true){
               USQLDUMP.crear_pitacora(">> Instruccion crear base de datos");
               sentencia_crear_base(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear objeto") == true){
                USQLDUMP.crear_pitacora(">> instruccion crear objeto");
                sentencia_crear_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear proce") == true){
                USQLDUMP.crear_pitacora(">> instruccion crear procedimiento");
                sentencia_crear_proce(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear funcion") == true){
                USQLDUMP.crear_pitacora(">> Instruccion crear funcion ");
                sentencia_crear_funcion(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear tabla") == true){
                USQLDUMP.crear_pitacora(">> Instruccion crear tabla");
                sentencia_crear_tabla(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("imprimir") == true){
                USQLDUMP.crear_pitacora(">> Instruccion imprimir");
                sentencia_imprimir(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("llamada") == true){
                USQLDUMP.crear_pitacora(">> Instruccion  llamada");
                sentencia_llamada(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("declarar") == true){
                USQLDUMP.crear_pitacora(">> Instruccion declarar");
                sentencia_declarar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("asignar") == true){
                sentencia_asignar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("insertar") == true){
                USQLDUMP.crear_pitacora(">> Instruccion insertar");
                manejodetablas.sentencia_insertar_en_tabla(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("insertar Valores") == true){
                USQLDUMP.crear_pitacora(">> Instruccion Insertar Valores");
                manejodetablas.sentencia_insertar_especial_tabla(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("actualizar")){
                USQLDUMP.crear_pitacora(">> Instruccion  Actualizar");
                manejodetablas.Sentencia_Actualizar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("actualizar cond")){
                USQLDUMP.crear_pitacora(">> Instruccion actualizar con condicion");
                manejodetablas.Sentencia_Actualizar_cond(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("seleccionar")){
                manejodetablas.Sentencia_seleccionar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("mientras")){
                Nodo nodo2 = SentenciasSSL.Sentencia_mientras(usuario, master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                    crearPaquete.pq_mensaje("Error retorno vino dentro de un procedimiento");
                    System.out.println("error");
                }
            }else if(arbol.getNombre().equalsIgnoreCase("detener")){
                 crearPaquete.pq_mensaje("Error detener no se encuentra dentro de un while o para");
            }else if(arbol.getNombre().equalsIgnoreCase("backup usql")){
                USQLDUMP.backup_usql(temp);
            }else if(arbol.getNombre().equalsIgnoreCase("restaurar usql")){
                USQLDUMP.restaurar_usql(temp);
            }else if (arbol.getNombre().equalsIgnoreCase("switch")){
                Nodo nodo2 = SentenciasSSL.sentencia_switch(usuario, master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                    crearPaquete.pq_mensaje("Error retorno vino dentro de un procedimiento");
                    System.out.println("error");
                }
            }else if(arbol.getNombre().equalsIgnoreCase("fun_si")){
                Nodo nodo2 = SentenciasSSL.Sentencia_Si(usuario,master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                   crearPaquete.pq_mensaje("Error retorno vino dentro de un procedimiento");
                    System.out.println("error");
                }
            }
        }
    }
    
    public static void tipopaq( Nodo usuario, Nodo master, Nodo hijo){
        for(Nodo arbol : hijo.getHijos()){  
            Nodo temp = arbol;
            if(arbol.getNombre().equalsIgnoreCase("seleccionar")){
                manejodetablas.Reporte_seleccionar(usuario, master, temp);
            }else{
                
            }
        }
    }
     
    public static Nodo fun_tipousql( Nodo usuario, Nodo master, Nodo hijo){
        for(Nodo arbol : hijo.getHijos()){  
            Nodo temp = arbol;
            if(arbol.getNombre().equalsIgnoreCase("crear usuario") == true){
                USQLDUMP.crear_pitacora(">> Instruccion  crear usuario");
               crearusuario(usuario, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("usar") == true){
                sentencia_usar(master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("otorgar idp") == true){
                USQLDUMP.crear_pitacora(">> Instruccion  otorgar permiso objeto");
                 sentencia_permitir_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("otorgar todos") == true){
                USQLDUMP.crear_pitacora(">> Instruccion  otorgar permiso todos");
                sentencia_permiso_todos(usuario,master,temp);
            }else if(arbol.getNombre().equalsIgnoreCase("denegar todos") == true){
                USQLDUMP.crear_pitacora(">> Instruccion denegar permiso todos");
                sentencia_denegar_todos(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("denegar idp") == true){
                USQLDUMP.crear_pitacora(">> Instruccion  denegar objeto todos");
                sentencia_denegar_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear base") == true){
               USQLDUMP.crear_pitacora(">> Instruccion  crear base");
               sentencia_crear_base(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear objeto") == true){
                USQLDUMP.crear_pitacora(">> Instruccion  crear objeto");
                sentencia_crear_objeto(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear proce") == true){
                USQLDUMP.crear_pitacora(">> Instruccion crear proce");
                sentencia_crear_proce(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear funcion") == true){
                USQLDUMP.crear_pitacora(">> Instruccion crear funcion");
                sentencia_crear_funcion(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("crear tabla") == true){
                USQLDUMP.crear_pitacora(">> Instruccion crear tabla");
                sentencia_crear_tabla(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("imprimir") == true){
                USQLDUMP.crear_pitacora(">> Instruccion imprimir");
                sentencia_imprimir(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("llamada") == true){
                USQLDUMP.crear_pitacora(">> Instruccion llamada");
                sentencia_llamada(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("declarar") == true){
                sentencia_declarar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("asignar") == true){
                sentencia_asignar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("retorno") == true){
                Nodo nodo2 = sentencia_Retorno(usuario, master, temp);   
                imprimir_nodo(nodo2, "texto****");
                nodo2.setObj("retorno");
                return nodo2;
            }else if(arbol.getNombre().equalsIgnoreCase("insertar") == true){
                USQLDUMP.crear_pitacora(">> Instruccion insertar");
                manejodetablas.sentencia_insertar_en_tabla(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("insertar Valores") == true){
                USQLDUMP.crear_pitacora(">> Instruccion Insertar Valores");
                manejodetablas.sentencia_insertar_especial_tabla(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("actualizar")){
                USQLDUMP.crear_pitacora(">> Instruccion  Actualizar");
                manejodetablas.Sentencia_Actualizar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("actualizar cond")){
                
                manejodetablas.Sentencia_Actualizar_cond(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("seleccionar")){
                manejodetablas.Sentencia_seleccionar(usuario, master, temp);
            }else if(arbol.getNombre().equalsIgnoreCase("mientras")){
                Nodo nodo2 = SentenciasSSL.Sentencia_mientras(usuario, master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                   return nodo2;
                }
            }else if(arbol.getNombre().equalsIgnoreCase("detener")){
                System.out.println("Error no se encuentra dentro de un while o para");
            }else if(arbol.getNombre().equalsIgnoreCase("fun_si")){
                Nodo nodo2 = SentenciasSSL.Sentencia_Si(usuario,master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                    return nodo2;
                }
            }else if(arbol.getNombre().equalsIgnoreCase("backup usql")){
                USQLDUMP.backup_usql(temp);
            }else if (arbol.getNombre().equalsIgnoreCase("switch")){
                Nodo nodo2 = SentenciasSSL.sentencia_switch(usuario, master, temp);
                if(nodo2.getObj().equalsIgnoreCase("retorno")){
                    return nodo2;
                }
            }
        }
        return null;
    }
    
    public static void crearusuario(Nodo usuario, Nodo hijo){
        boolean valor;
        String cadena;
        Nodo temp;
        Nodo nodo1;
        Nodo nodo2;
        crearPaquete.pq_ejecucion("Verificando si es administrador");
        temp = hijo.getHijos().get(0);
        valor = existeusuario(usuario, temp.getNombre());
        crearPaquete.pq_ejecucion("Verificando si el usuario "+temp.getNombre()+" exite");
        if(valor == false){
             cadena = temp.getValor().substring(1, temp.getValor().length()-1);
             temp.setValor(cadena);
             nodo1 = crearnodo("Nopermiso", " ");
             nodo2 = crearnodo("permiso", " ");
             temp.addHijo(nodo1);
             temp.addHijo(nodo2);
             pr1compilarodores2.principal2.usuarios.addHijo(temp);
             crearPaquete.pq_mensaje("Sentencia Usar correcta db: "+temp.getNombre());
             crearxml.Usuario();
        }else{
            crearPaquete.pq_mensaje("No se pudo crear el usuario: "+temp.getNombre());
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
        crearPaquete.pq_ejecucion("Buscando si bd: "+texto+" existe");
        for(Nodo arbol : tmp.getHijos()){ 
            if(arbol.getNombre().equalsIgnoreCase(texto) == true){
                return true;
            }
        } 
        return false;
    }
    
    public static Nodo nodo_buscar_bd(Nodo master,String texto){
        crearPaquete.pq_ejecucion("Buscando si la db:"+texto+" exite en el sistema");
        for(Nodo arbol : master.getHijos()){ 
            if(arbol.getNombre().equalsIgnoreCase(texto) == true){
                return arbol;
            }
        } 
        return null;
    }
    
    public static Nodo nodo_buscar_tabla(Nodo db,String texto){
        crearPaquete.pq_ejecucion("Buscando si tabla "+texto+" exite en la db");
        for(Nodo arbol : db.getHijos()){ 
            if(arbol.getNombre().equalsIgnoreCase(texto) == true){
                return arbol;
            }
        } 
        return null;
    }
    
    public static Nodo nodo_buscar_proce(Nodo db,String texto){
        crearPaquete.pq_ejecucion("Buscando si el procedimiento: "+texto+" exite en el sistema");
        for(Nodo arbol : db.getHijos()){ 
           if(arbol.getNombre().equalsIgnoreCase("procedure")){
                for(Nodo primo : arbol.getHijos()){
                    if(primo.getNombre().equalsIgnoreCase(texto)){
                        return primo;
                      }
                }
            } 
        }
        return null;
    }
    
    public static Nodo nodo_buscar_objeto(Nodo db,String texto){
        crearPaquete.pq_ejecucion("Buscando si el objeto  "+texto+"existe en el sistema");
        for(Nodo arbol : db.getHijos()){ 
           if(arbol.getNombre().equalsIgnoreCase("objeto")){
                for(Nodo primo : arbol.getHijos()){
                    if(primo.getNombre().equalsIgnoreCase(texto)){
                        return primo;
                      }
                }
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
        crearPaquete.pq_ejecucion("Buscando si "+usar.getValor()+" exite en el sistema");
        if(valor == true){
            pr1compilarodores2.principal2.db=usar.getValor(); //agrego a la base de datos
            crearPaquete.pq_salidadatos(" ");
            crearPaquete.pq_mensaje("Sentencia Usar correcta db: "+usar.getValor());
            
        }else{
            crearPaquete.pq_mensaje("Sentencia Usar incorrecta db: "+usar.getValor());
            System.out.println("No");
        }
    }
    
    public static void sentencia_permiso_todos(Nodo usuarios,Nodo master , Nodo permiso){
        int contador=0;
        crearPaquete.pq_ejecucion("Verificando si es usuario admin");
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
                 crearPaquete.pq_mensaje("Sentencia permiso Todo correcta");
                 crearxml.Usuario();
            }
        }else{
            crearPaquete.pq_mensaje("Sentencia permiso todo incorrecto el usuario no es admin");
        }
    }
    
     public static void sentencia_denegar_todos(Nodo usuarios,Nodo master , Nodo permiso){
        int contador=0;
        crearPaquete.pq_ejecucion("verificando si es usuario admin");
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
                         crearPaquete.pq_mensaje("Se denegaron permisos correctamente");
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
                         crearPaquete.pq_mensaje("Se denegaron permisos correctamete de"+permiso.getTipo());
                         break;
                     }
                     ++contador; 
                 } 
                 crearxml.Usuario();
            }
        }else{
            crearPaquete.pq_mensaje("Sentencia denegar todo incorrecta");
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
        //modique este metodo revisar luego************
        crearPaquete.pq_ejecucion("Verifiando si el usuario: "+usu+" tiene permisos");
        if(persona != null && db != null){
            //verificando en noper
             nodo1 = persona.getHijos().get(0);
             for(Nodo hijos : nodo1.getHijos()){ 
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
             for(Nodo hijos : nodo1.getHijos()){ 
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
        crearPaquete.pq_ejecucion("Buscando si el objeto "+objeto+" exite en "+base);
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
    
     public static boolean exite_solo_obj(Nodo master,String base, String objeto){
        Nodo db;
        db = nodo_buscar_bd(master, base);
        crearPaquete.pq_ejecucion("buscando el objeto: "+objeto);
        if(db != null){
            for(Nodo hijos : db.getHijos()){
                if(hijos.getNombre().equalsIgnoreCase("procedure")){
                    for(Nodo primo : hijos.getHijos()){
                        if(primo.getNombre().equalsIgnoreCase(objeto)){
                            return false;
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
                        return false;
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
        crearPaquete.pq_ejecucion("Verificando si es usuario admin");
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
                                  crearPaquete.pq_ejecucion("Otorgando permisos");
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
                             crearPaquete.pq_mensaje("Se agregaron permisos del objeto");
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
                      crearPaquete.pq_mensaje("Se agregaron permisos");
                 } 
                crearxml.Usuario();
            }
        }else{
            crearPaquete.pq_mensaje("Sentencia permisos objeto incorrecta");
        }
    }
    
    public static void sentencia_denegar_objeto(Nodo usuarios,Nodo master , Nodo permiso){
        int contador=0;
        int con=0;
        boolean valor;
        int prueba=0;
        crearPaquete.pq_ejecucion("Verificando si es admin");
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
                            crearPaquete.pq_mensaje("Se denegaron permisos del objeto");
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
                      crearPaquete.pq_mensaje("Se denegaron permisos del objeto ");
                 }
                } 
                crearxml.Usuario();
            }
        }else{
            crearPaquete.pq_mensaje("Sentencia denegar objeto incorrecta");
        }
    }
     
    public static void sentencia_crear_base(Nodo usuarios,Nodo master, Nodo paquete){
        Nodo arbol = paquete.getHijos().get(0);
        String ruta,texto;
        String newruta;
        boolean valor;
        valor=buscar_bd(master, arbol.getNombre());
        if(valor==false){
            crearPaquete.pq_mensaje("Base de datos creada");
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
                   crearxml.Usuario();
               }
           }
       }
     }else{
           crearPaquete.pq_mensaje("Sentencia crear base incorrecta");
        }
       
    }
    
    public static String remplazar(String cadena){
        String palabra = cadena.replace("\\", "\\\\");
        return palabra;
    }
    
    public static void sentencia_crear_objeto(Nodo usuarios,Nodo master, Nodo paquete){
        //verificar que el objeto exita
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
        boolean valor = exite_objusql(master, pr1compilarodores2.principal2.db, paquete.getValor());
        if(db != null && valor==false){
             Nodo nodo1=crearnodo(paquete.getValor(), "");
             crearPaquete.pq_mensaje("El objeto "+ paquete.getValor() +"a sido creado");
             for(Nodo arbol : paquete.getHijos()){
                 Nodo nodo2 = crearnodo(arbol.getNombre(), arbol.getTipo());
                 nodo1.addHijo(nodo2);
             } 
              Nodo tmp=db.getHijos().get(1);
              tmp.addHijo(nodo1);
              Crearmaster.master();
              agregar_permiso(usuarios, master, pr1compilarodores2.principal2.db, pr1compilarodores2.principal2.usua, paquete.getValor());
        }
    }
    
    public static void agregar_permiso(Nodo usuarios,Nodo master ,String base, String usuario, String objeto){
        int contador=0;
        int con=0;
        boolean valor;
        int prueba=0;
        if(pr1compilarodores2.principal2.usua.equalsIgnoreCase("admin") == false ){
            Nodo persona = nodo_existeusuario(usuarios, usuario);
            Nodo db = nodo_buscar_bd(master, base);
            valor = exite_objusql(master, base, objeto);       
            if (persona != null && db != null && valor==true){
                 Nodo nodo1 = persona.getHijos().get(0);
                 for(Nodo hijo : nodo1.getHijos()){
                     //eliminar nodo si exite el nodo
                     if(hijo.getNombre().equalsIgnoreCase(base)){
                        for(Nodo primo : hijo.getHijos() ){
                            if(primo.getNombre().equalsIgnoreCase(objeto)){
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
                     if(hijo.getNombre().equalsIgnoreCase(base)){
                         int numero=0;
                         for(Nodo primo : hijo.getHijos()){
                             if(primo.getNombre().equalsIgnoreCase("todos")){
                                prueba = 1; numero=1;
                             }else if(primo.getNombre().equalsIgnoreCase(objeto)){
                                 numero=1; prueba=1;
                             }
                             ++con;
                         }if(numero == 0){
                             Nodo tmp = new Nodo(objeto); //objeto
                             tmp.setNumNodo(++gramatica_xml.graxml.contador);
                             hijo.addHijo(tmp);
                             prueba = 1;
                         }
                         break;
                     }
                     ++contador; 
                } 
                 if(prueba == 0){ 
                      Nodo tmp = new Nodo(base); //tabla
                      tmp.setNumNodo(++gramatica_xml.graxml.contador);
                      Nodo tmp1 = new Nodo(objeto); //objeto
                      tmp1.setNumNodo(++gramatica_xml.graxml.contador);
                      tmp.addHijo(tmp1);
                      nodo2.addHijo(tmp);
                 } 
                crearxml.Usuario();
            }
        }
    }
    
    public static String partirtextoenlineas(String cadena,int inf, int sup ){
        String[] texto=cadena.split("\\n");
        String palabra="";
        int contador=0;
        for (int i = 0; i < texto.length; i++) {
            if(contador>=inf && contador<=sup){
                palabra=palabra+texto[contador]+"\n";
            }
            ++contador;
        }
        System.out.println("texto "+palabra);
        return palabra;
    }
    
    public static void sentencia_crear_proce(Nodo usuarios,Nodo master, Nodo paquete){
        //verificar que el objeto exita
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
        boolean valor = exite_objusql(master, pr1compilarodores2.principal2.db, paquete.getValor());
        if(db != null && valor==false){
             Nodo nodo1=crearnodo(paquete.getValor(), "");
             Nodo nuew=paquete.getHijos().get(0);
             Nodo n2 = crearnodo("parametros", "");
             for(Nodo arbol: nuew.getHijos()){
                 Nodo hijo1=crearnodo(arbol.getNombre(), arbol.getTipo());
                 n2.addHijo(hijo1);
             }
             nodo1.addHijo(n2);
             Nodo n1=paquete.getHijos().get(1);
             int f=n1.getFila()-2;
             int i=n1.getColumna();
             System.out.println("ini "+Integer.toString(i)+" fin "+Integer.toString(f));
             String texto=partirtextoenlineas(pr1compilarodores2.principal2.texfun, i, f);
             texto="%$ "+texto+" %";
             Nodo n3=crearnodo("sentencias", texto);
             nodo1.addHijo(n3);
             Nodo tmp=db.getHijos().get(0);
             tmp.addHijo(nodo1);
             crearPaquete.pq_mensaje("Procedimiento "+ paquete.getValor() + " creado");
             Crearmaster.master();
             agregar_permiso(usuarios, master, pr1compilarodores2.principal2.db, pr1compilarodores2.principal2.usua, paquete.getValor());
        }else{
            crearPaquete.pq_mensaje("No se pudo crear el procedimiento"+paquete.getValor());
        }
    }
    
    public static void sentencia_crear_funcion(Nodo usuarios,Nodo master, Nodo paquete){
        //verificar que el objeto exita
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
        boolean valor = exite_objusql(master, pr1compilarodores2.principal2.db, paquete.getValor());
        if(db != null && valor==false){
             Nodo nodo1=crearnodo(paquete.getValor(), paquete.getTipo());
             nodo1.setTipo("fun");
             Nodo nuew=paquete.getHijos().get(0);
             Nodo n2 = crearnodo("parametros", "");
             for(Nodo arbol: nuew.getHijos()){
                 Nodo hijo1=crearnodo(arbol.getNombre(), arbol.getTipo());
                 n2.addHijo(hijo1);
             }
             nodo1.addHijo(n2);
             Nodo n1=paquete.getHijos().get(1);
             int f=n1.getFila()-2;
             int i=n1.getColumna();
             System.out.println("ini "+Integer.toString(i)+" fin "+Integer.toString(f));
             String texto=partirtextoenlineas(pr1compilarodores2.principal2.texfun, i, f);
             texto="%$ "+texto+" %";
             Nodo n3=crearnodo("sentencias", texto);
             nodo1.addHijo(n3);
             Nodo tmp=db.getHijos().get(0);
             tmp.addHijo(nodo1);
             crearPaquete.pq_mensaje(""+paquete.getValor());
             Crearmaster.master();
             agregar_permiso(usuarios, master, pr1compilarodores2.principal2.db, pr1compilarodores2.principal2.usua, paquete.getValor());
        }else{
            crearPaquete.pq_mensaje("No se pudo crear la funcion "+paquete.getValor());
        }
    }
    
    public static void sentencia_crear_tabla(Nodo usuarios,Nodo master, Nodo paquete){
        //verificar que el objeto exita
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
        boolean valor = exite_objusql(master, pr1compilarodores2.principal2.db, paquete.getValor());
        if(db != null && valor==false ){
              valor = verificar_1_pk(paquete);
              boolean valor2 = verificar_autoincreme(paquete);
              boolean valor3 = verificar_objeto(paquete, master);
              boolean valor4 = verificar_fk(master, paquete);
              crearPaquete.pq_ejecucion("Verificando complementos");
              if(valor==true && valor2==true && valor3==true && valor4==true){ 
               
              String ruta = pr1compilarodores2.principal2.ruta_master+"\\"+pr1compilarodores2.principal2.db+"\\"+paquete.getValor()+".usac";
              String texto=remplazar(ruta);
              System.out.println("Acciones.accpaquete.sentencia_crear_tabla()"+texto);
              Nodo nodo1 = crearnodo(paquete.getValor(), texto);
              
              Nodo n1 = crearnodo("datos", "");
              Nodo n2 = crearnodo("campos", "");
              for(Nodo arbol : paquete.getHijos()){
                  arbol.setValor(arbol.getTipo());
                  n2.addHijo(arbol);
              }
              nodo1.addHijo(n1);
              nodo1.addHijo(n2);
              db.addHijo(nodo1);
              crearPaquete.pq_mensaje("Sentencia crear tabla correcta");
              }
              else{ 
                  crearPaquete.pq_ejecucion("Sentencia crear tabla incorrecta");
              }
              crearPaquete.pq_ejecucion("");
              Crearmaster.master();
              agregar_permiso(usuarios, master, pr1compilarodores2.principal2.db, pr1compilarodores2.principal2.usua, paquete.getValor());
        }else{
            crearPaquete.pq_mensaje("Sentencia crear tabla incorrecta");
        }
    }
    
    public static boolean verificar_1_pk(Nodo tabla){
        int contador=0;
        for(Nodo arbol : tabla.getHijos()){
            for(Nodo primo : arbol.getHijos()){
                if(primo.getNombre().equalsIgnoreCase("<pk>")){
                    ++contador;
                }
            }
        }
        if(contador > 1){ return false;
        }else{ return true;}
    }
    
    public static boolean verificar_autoincreme(Nodo tabla){
        int error=0;
        for(Nodo arbol : tabla.getHijos()){
            String tipo=arbol.getTipo();
            for(Nodo primo : arbol.getHijos()){
                if(primo.getNombre().equalsIgnoreCase("<auto>")){
                    if(tipo.equalsIgnoreCase("int")==false){
                        error=1;
                    }
                }
            }
        }
        if(error==1){ return false;
        }else{ return true;}
    }
    
    public static boolean verificar_objeto(Nodo tabla, Nodo master){
        boolean valor;
        for(Nodo arbol : tabla.getHijos()){
            if(arbol.getValor() != null){
                 valor = exite_solo_obj(master, pr1compilarodores2.principal2.db, arbol.getTipo());
                if(valor==false){
                    return false;
                }
            } else { }
        }
       return true;
    }
    
    public static boolean verificar_fk(Nodo master,Nodo tabla){
        int contador=0;
        for(Nodo arbol : tabla.getHijos()){
            for(Nodo primo : arbol.getHijos()){
                if(primo.getNombre().equalsIgnoreCase("<fk>")){
                    Nodo db = nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
                    Nodo tb = nodo_buscar_tabla(db, primo.getValor());
                    if(db != null && tb != null){
                        boolean valor = es_pkllaveforanea(tb.getHijos().get(1), primo.getTipo());
                        if(valor==false){
                            return false;
                        }else{
                            System.out.println("exite tabla"+primo.getValor());
                        } 
                    }else{ return false; }
                }
            }
        }
        return true;
    }
    
    public static boolean es_pkllaveforanea(Nodo tabla, String id){
        for(Nodo arbol : tabla.getHijos()){
            for(Nodo primo : arbol.getHijos()){
                if(primo.getNombre().equalsIgnoreCase("<pk>")){
                    if(arbol.getNombre().equalsIgnoreCase(id)){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    public static void sentencia_imprimir(Nodo usuarios,Nodo master, Nodo paquete){
            Nodo nodo1 = paquete.getHijos().get(0); 
            crearPaquete.pq_ejecucion("Realizando expresion");
            Nodo nodo2 = expresiones.expresiones(nodo1); 
            crearPaquete.pq_salidadatos("Imprimir:"+nodo2.getNombre());
            imprimir_nodo(nodo2, "imprimir: ");
    }
    
    public static void sentencia_llamada(Nodo usuarios,Nodo master, Nodo paquete){
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db); 
        Nodo proce=nodo_buscar_proce(db, paquete.getValor());
        boolean valor = tiene_permiso(usuarios, master, pr1compilarodores2.principal2.db, pr1compilarodores2.principal2.usua , paquete.getValor());
        crearPaquete.pq_ejecucion("realizando una llamada de funcion o procedimiento");
        if(db != null && proce != null && valor){
            if(proce.getTipo().equalsIgnoreCase("proce")){
                realizar_ope_para(paquete); //realizo las  expresiones
                valor = verificar_parametros_tipos(paquete, proce.getHijos().get(0)); //verifico que los tipos sean iguales
                if(valor){
                     if(!expresiones.pila.empty()){
                         tablasimbolos pivote = expresiones.pila.peek();
                         tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                         tb.setAmbito(0); //ambito cero
                         expresiones.pila.push(tb);
                         llenar_tabla_concontenido(paquete, pivote);
                         tipousql( usuarios, master, proce.getHijos().get(1));//realizar sentencias
                         expresiones.pila.pop();
                         System.out.println("correctoss");
                     }else{
                         tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                         tb.setAmbito(0); //ambito cero
                         expresiones.pila.push(tb); //ingreso a la pila un tabla de simbolos
                        llenar_tabla(paquete);//lleno la tabla de simbolos
//                      imprimir_tabla_simbolos();
                        tipousql( usuarios, master, proce.getHijos().get(1));//realizar sentencias
                        expresiones.pila.pop();
                        System.out.println("correcto");
                     }
                     
                }
            }else{
                crearPaquete.pq_mensaje("Usted esta intentando llamar a una funcion"); }
        }else{
            crearPaquete.pq_mensaje("No tiene permisos del procedimiento ");
        }
    }
    
    public static void llenar_tabla_concontenido(Nodo paquete,tablasimbolos pivote){
        
        tablasimbolos actual = expresiones.pila.peek();
        crearPaquete.pq_ejecucion("Llenando tabla de simbolos");
        for(Nodo arbol : paquete.getHijos()){
            if(arbol.getTexto()!=""){//este es un objeto
                boolean valor=exite_entabla_pivote(arbol.getNombre(),pivote);
                Nodo db=nodo_buscar_bd(pr1compilarodores2.principal2.master, pr1compilarodores2.principal2.db);
                Nodo obj=nodo_buscar_objeto(db,arbol.getTexto());
                boolean val = exite_entabladesimbolo(paquete.getValor());//si en la tabla de simbolos actual no exite la variable
                if(valor==true && obj!=null && val==false){//Si exite en la tabla pivote
                    String tamano = Integer.toString(obj.getHijos().size());
                    agregar_a_tabla_obj(arbol.getValor(), "obj", tamano, arbol.getTexto());
                    for(Nodo primo : obj.getHijos()){ //recorriendo arbol master de objeto
                        String cadena = arbol.getNombre()+"."+primo.getNombre();//@emp + .val
                        String cadena2 = arbol.getValor()+"."+primo.getNombre();//@comision +val
                        tablasimbolos tep=devolver_elemento_tb_pivote(cadena, pivote);
                        agregar_a_tabla_obj(cadena2, primo.getValor(), tep.getValor(), arbol.getTexto());
                    }
                }
            }else{
                boolean valor1 = exite_entabladesimbolo(arbol.getValor()); //verificando que no exita en tabla de simbolo.
                if(valor1==false){
                  tablasimbolos tb = expresiones.pila.peek();
                  int numero = tb.getAmbito();
//                  imprimir_nodo(arbol, "paquete");
                  tablasimbolos tb1 = new tablasimbolos(arbol.getValor(), arbol.getTipo(), arbol.getNombre());
                  tb1.setAmbito(numero);
                  tb.addHijo(tb1);
                }else{
                    System.out.println("Error este ya exite en tabla de simbolos");
                }
            }
        }
    }

    public static void llenar_tabla(Nodo paquete){
        boolean valor = false;
        for(Nodo arbol : paquete.getHijos()){
        boolean valor1 = exite_entabladesimbolo(arbol.getValor());
            if(valor1==true){
                valor=true;
            }
        }
        if(valor==false){
             for(Nodo arbol : paquete.getHijos()){
                  tablasimbolos tb = expresiones.pila.peek();
                  int numero = tb.getAmbito();
//                  imprimir_nodo(arbol, "paquete");
                  tablasimbolos tb1 = new tablasimbolos(arbol.getValor(), arbol.getTipo(), arbol.getNombre());
                  tb1.setAmbito(numero);
                  tb.addHijo(tb1);
             }
       
        }
         
    }
    
    public static boolean verificar_parametros_tipos(Nodo paquete, Nodo proce){
        int con=0;
        crearPaquete.pq_ejecucion("Verificando si los parametros son del mismo tipo");
        if(paquete.getHijos().size()==proce.getHijos().size()){
             for(Nodo arbol : proce.getHijos()){
                Nodo para=paquete.getHijos().get(con);
                para.setValor(arbol.getNombre());
                if(arbol.getValor().equals("int")){
                    if(para.getTipo().equalsIgnoreCase("int")==false){
                        return false;
                    }
                }else if(arbol.getTipo().equalsIgnoreCase("obj")){
                    if(arbol.getValor().equalsIgnoreCase(para.getTexto())==false){
                        System.out.println("tipos de parametros incorrectos");
                        return false;
                    }
                }else{
//                    imprimir_nodo(arbol, "arbol");
//                    imprimir_nodo(para, "para");para.getTexto()
                    if(arbol.getValor().equalsIgnoreCase(para.getTipo())==false){
                        System.out.println("tipos de parametros incorrectos");
                        return false;
                    }
                }
                ++con;
            }
        }else{
            System.out.println("parametros incorrectos");
            return false;
        }
           
        return true;
    }
    
    public static String devolever_tipo_tabla(Nodo tb, String variable){
        crearPaquete.pq_ejecucion("Buscando el tipo de la tabla");
        for(Nodo hijos : tb.getHijos()){
            if(hijos.getNombre().equalsIgnoreCase(variable)){
                return hijos.getValor();
            }
        }
        return "";
    }
    
    public static void realizar_ope_para(Nodo paquete){
        int con=0;
        for (Nodo hijo : paquete.getHijos()){
             Nodo nodo2 = expresiones.expresiones(hijo); //paquete
             paquete.getHijos().set(con, nodo2);
             ++con;
             
        }
    }
    
   public static boolean exite_entabladesimbolo(String texto){
        tablasimbolos tb = expresiones.pila.peek();
        for(tablasimbolos hijo: tb.getSiguiente()){
            if(hijo.getNombre().equalsIgnoreCase(texto)){
                return true;
            }
        }
        return false;
   }
   
   public static boolean exite_entabla_pivote(String texto, tablasimbolos tb){
        for(tablasimbolos hijo: tb.getSiguiente()){
            if(hijo.getNombre().equalsIgnoreCase(texto)){
                return true;
            }
        }
        return false;
   }
    
   public static void imprimir_tabla_simbolos(){
       if (!expresiones.pila.empty()){
           tablasimbolos tb = expresiones.pila.peek();
           for(tablasimbolos hijos : tb.getSiguiente()){
               System.out.println("Nombre:"+hijos.getNombre()+
                       " Tipo:"+hijos.getTipo()+" obj: "+
                            hijos.getObj()+" Valor:"+hijos.getValor()+" Ambito:"+Integer.toString(hijos.getAmbito()));
           }
       } 
   }
   
   public static void sentencia_declarar(Nodo usuarios,Nodo master, Nodo paquete){
       boolean valor=false;
       Nodo variables = paquete.getHijos().get(0);//hijo izquierdo
       Nodo expr = paquete.getHijos().get(1);//hijo derecho
       crearPaquete.pq_ejecucion("Declarando un objeto usql");
       if(expr.getHijos().size()>0){
           Nodo nodo1 = expr.getHijos().get(0);
           Nodo nodo2 = expresiones.expresiones(nodo1);
           if(nodo2.getNombre().equalsIgnoreCase("error")){
               System.out.println("Se encontro un error al hacer la expresion");
           }else if(nodo2.getNombre().equalsIgnoreCase("obj")){
               System.out.println("No se puede asignar un tipo objeto");
           }else{
                if(variables.getTipo().equalsIgnoreCase("int")){ //verificacion de tipos
                    if(nodo2.getTipo().equalsIgnoreCase("int")){
                        valor=true;
                    }
                }else if(variables.getTipo().equalsIgnoreCase(nodo2.getTipo())){
                    valor=true; }
                if(valor==true){
                     for(Nodo arbol : variables.getHijos()){
                         boolean valor1 = exite_entabladesimbolo(arbol.getNombre());
                        if(valor1==false){
                        agregar_a_tabla(arbol.getNombre(), nodo2.getTipo(), nodo2.getNombre());
                        }
                    }
                }   
           }
        }else{ //si no hay exp
           if(variables.getValor().equalsIgnoreCase("obj")){
               Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
               Nodo obj=nodo_buscar_objeto(db,variables.getTipo()); //variables.getTipo es el objeto
               if(obj!=null){
                    for(Nodo arbol : variables.getHijos()){
                        boolean valor2 = exite_entabladesimbolo(arbol.getNombre());
                        if(valor2==false){
                            String tamano = Integer.toString(obj.getHijos().size());
                            agregar_a_tabla_obj(arbol.getNombre(), "obj",tamano,variables.getTipo());
                            for(Nodo primo : obj.getHijos()){
                                String cadena = arbol.getNombre()+"."+primo.getNombre();//@obj + .val
                                boolean val = exite_entabladesimbolo(cadena);
                                if(val==false){
                                    if(primo.getValor().equalsIgnoreCase("int")){ 
                                       agregar_a_tabla_obj(cadena, "int","",variables.getTipo());
                                    }else{
                                       agregar_a_tabla_obj(cadena, primo.getValor(),"",variables.getTipo());
                                 }
                             }else{
                                 System.out.println("Este objeto ya exite en tabla de simbolos ");  }
                            }
                            //imprimir_tabla_simbolos();
                        }else {
                            System.out.println("Este objeto ya exite en tabla de simbolos "+arbol.getNombre()); }
                    }
               }else{
                   System.out.println("Error el objeto no exite" ); }
           }else{ //no es tipo objeto
               for(Nodo arbol : variables.getHijos()){
                    boolean val = exite_entabladesimbolo(arbol.getNombre());
                    if(val==false){
                        agregar_a_tabla(arbol.getNombre(), variables.getTipo(), "");
                    }else{
                        System.out.println("Esta varible ya exite"+arbol.getNombre());
                    }
               }
               //imprimir_tabla_simbolos();
           }

       }
   }
    
   public static void imprimir_nodo(Nodo imp, String texto){
       System.out.println(texto+" Nombre:"+imp.getNombre()+" Valor:"+imp.getValor()+" tipo:"+imp.getTipo());
   }
   
   public static void agregar_a_tabla(String nombre, String tipo, String valor){
       tablasimbolos tb = expresiones.pila.peek();
       int numero = tb.getAmbito();
       tablasimbolos tb1 = new tablasimbolos(nombre, tipo, valor);
       tb1.setAmbito(numero);
       tb.addHijo(tb1);
   }
   
   public static void agregar_a_tabla_obj(String nombre, String tipo, String valor, String obj){
       tablasimbolos tb = expresiones.pila.peek();
       int numero = tb.getAmbito();
       tablasimbolos tb1 = new tablasimbolos(nombre, tipo, valor);
       tb1.setObj(obj);
       tb1.setAmbito(numero);
       tb.addHijo(tb1);
   }
   
   public static void sentencia_asignar(Nodo usuarios,Nodo master, Nodo paquete){
         if(paquete.getTipo().equalsIgnoreCase("variable") || paquete.getTipo().equalsIgnoreCase("variable1p")){
             boolean valor = exite_entabladesimbolo(paquete.getValor());
             crearPaquete.pq_ejecucion("Verificando si el objeto "+ paquete.getValor()+" ya fue declarado");
             if(valor==true){
                 Nodo nodo1 = paquete.getHijos().get(0);
                 Nodo nodo2 = expresiones.expresiones(nodo1);
                 if(nodo2.getValor().equalsIgnoreCase("error")){
                     System.out.println("Error en expresion");
                 }else if(nodo2.getValor().equalsIgnoreCase("obj")){
                     if(!expresiones.pivote.empty()){
                         tablasimbolos tb1 = devolver_elemento_tb(paquete.getValor());//simbolo lado izquierdo
                         tablasimbolos tb2 = expresiones.pivote.pop();//saco la tabla de simbolos pasadas
                         Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db); //busco el objeto en la tabla de simbolos
                         Nodo obj=nodo_buscar_objeto(db,tb1.getObj());//me devuelve el objeto
                         if(tb1.getObj().equalsIgnoreCase(nodo2.getTexto())){
                             for(Nodo primo : obj.getHijos()){
                                    String cadena1 = paquete.getValor()+"."+primo.getNombre();//@obj + .val
                                    String cadena2 = nodo2.getNombre()+"."+primo.getNombre();//@objdevuelto + .val
                                    tablasimbolos devo=devolver_elemento_tb_pivote(cadena2,tb2);
                                    cambiar_valor_tb(cadena1, devo.getValor());
    //                                boolean val = exite_entabladesimbolo(cadena);
                             }
                         }else{ 
                             System.out.println("retotno de tipos de objtos incorrecto ");
                         }
                     }else{
                         tablasimbolos tb1 = devolver_elemento_tb(paquete.getValor());
                         Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
                         Nodo obj=nodo_buscar_objeto(db,tb1.getObj());
                         if(tb1.getObj().equalsIgnoreCase(nodo2.getTexto())){
                             for(Nodo primo : obj.getHijos()){
                                    String cadena1 = paquete.getValor()+"."+primo.getNombre();//@obj + .val
                                    String cadena2 = nodo2.getNombre()+"."+primo.getNombre();//@objdevuelto + .val
                                    System.out.println("cadena1: "+cadena1);
                                    System.out.println("cadena2: "+cadena2);
                                    tablasimbolos devo=devolver_elemento_tb(cadena2);
                                    cambiar_valor_tb(cadena1, devo.getValor());
    //                                boolean val = exite_entabladesimbolo(cadena);
                             }
                             System.out.println("son del mismo tipo");
                         }else{
                             System.out.println("Son de diferente tipo");
                         }
                     }
                    ///////// 
                 }else{
                   String tipo = devolver_tipo_tb(paquete.getValor());
                   if(nodo2.getTipo().equalsIgnoreCase(tipo)){
                     cambiar_valor_tb(paquete.getValor(), nodo2.getNombre());
                    }else{
                       System.out.println("No son de mismo tipo asignacion");
                    }
                 }
             }else{
                 System.out.println("Este objeto no exite");
             }
         }           
   }
   
   public static void cambiar_valor_tb(String variable,String valor){
       tablasimbolos tb = expresiones.pila.peek();
       for(tablasimbolos hijos : tb.getSiguiente()){
           if(hijos.getNombre().equalsIgnoreCase(variable)){
               hijos.setValor(valor);
           }
       }
   }
   
   public static String devolver_tipo_tb(String variable){
       tablasimbolos tb = expresiones.pila.peek();
       for(tablasimbolos hijos : tb.getSiguiente()){
           if(hijos.getNombre().equalsIgnoreCase(variable)){
              return hijos.getTipo();
           }
       }
       return "ninguno";
   }
   
   public static String devolver_valor_tb(String variable){
       tablasimbolos tb = expresiones.pila.peek();
       for(tablasimbolos hijos : tb.getSiguiente()){
           if(hijos.getNombre().equalsIgnoreCase(variable)){
              return hijos.getValor();
           }
       }
       return "ninguno";
   }
   
    public static tablasimbolos devolver_elemento_tb(String variable){
       tablasimbolos tb = expresiones.pila.peek();
       for(tablasimbolos hijos : tb.getSiguiente()){
           if(hijos.getNombre().equalsIgnoreCase(variable)){
              return hijos;
           }
       }
       return null;
   }
    
    public static tablasimbolos devolver_elemento_tb_pivote(String variable,tablasimbolos tb){
       for(tablasimbolos hijos : tb.getSiguiente()){
           if(hijos.getNombre().equalsIgnoreCase(variable)){
              return hijos;
           }
       }
       return null;
   }
    
    public static Nodo sentencia_Retorno(Nodo usuarios,Nodo master, Nodo paquete){
         Nodo nodo1 = paquete.getHijos().get(0);
         Nodo nodo2 = expresiones.expresiones(nodo1);
         if(nodo2.getValor().equalsIgnoreCase("error")){
             Nodo nodo3=crear_nodo("error","","error");
             return nodo3;
         }else if(nodo2.getValor().equalsIgnoreCase("obj")){
             
         }else{
             imprimir_nodo(nodo2, "paquete");
             return nodo2;
         }
         return nodo2;
    }
}
