
package Acciones;


import java.util.Stack;
import pr1compilarodores2.Nodo;
public class manejodetablas extends accpaquete{
    
    public static void sentencia_insertar_en_tabla(Nodo usuarios,Nodo master, Nodo paquete){
        String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
        Nodo tabla=nodo_buscar_tabla(db, paquete.getValor()); //devuelve la tabla
        boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , paquete.getValor());
        if(tabla != null && valor){
            Nodo campos = tabla.getHijos().get(1); //Nodo campos
            Nodo datos = tabla.getHijos().get(0);
            if(campos.getHijos().size()==paquete.getHijos().size()){
                realizar_ope_para(paquete); //Realizo las operaciones en valor
                valor = verificar_parametros_tipos(paquete, campos);//Verifico que los tipos del paquete y campos sean iguales
                if(valor){
                    if(!expresiones.pila.empty()){ //si la pila esta vacia
                         tablasimbolos pivote = expresiones.pila.peek(); //guardar la pila anterior
                         tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                         tb.setAmbito(0); //ambito cero
                         expresiones.pila.push(tb);
                         accpaquete.llenar_tabla_concontenido(paquete, pivote);
                     }else{
                        tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                        tb.setAmbito(0); //ambito cero
                        expresiones.pila.push(tb); //ingreso a la pila un tabla de simbolos
                        llenar_tabla(paquete);
                     }
                    imprimir_tabla_simbolos();
                     boolean verificar = true; //Aqui voy hacer las verificacion <pk> <fk> etc
                    for(Nodo arbol : campos.getHijos()){//verificacion
                        String valores = devolver_valor_tb(arbol.getNombre());
                        for(Nodo complemento: arbol.getHijos()){
                            if(complemento.getNombre().equalsIgnoreCase("<pk>")){
                                valor = metodo_pk(datos, arbol.getNombre(), valores);
                                if(valor==false){ verificar=false; }
                            }else if(complemento.getNombre().equalsIgnoreCase("<auto>")){
                                
                            }else if(complemento.getNombre().equalsIgnoreCase("<unico>")){
                                valor = metodo_unico(datos, arbol.getNombre(), valores);
                                if(valor==false){ verificar=false; }
                            }else if(complemento.getNombre().equalsIgnoreCase("<NNulo>")){
                                valor = metodo_nnulo(valores);
                                if(valor==false){ verificar=false; }
                            }else if(complemento.getNombre().equalsIgnoreCase("<fk>")){
                                valor = metodo_buscar_fk(complemento.getValor(),complemento.getTipo(),valores);
                                if(valor==false){ verificar=false; }
                            }
                        }
                    }
                    if(verificar==true){
                        boolean verificacion=true;
                        Nodo nodo1 = crearnodo("row", "");
                        for(Nodo arbol : campos.getHijos()){//verificacion
                            tablasimbolos pivote = devolver_elemento_tb(arbol.getNombre());
                            if(pivote != null){
                                if(pivote.getObj().equalsIgnoreCase("")){
                                    String valores = devolver_valor_tb(arbol.getNombre());
                                    Nodo nodo2 = crearnodo(arbol.getNombre(), valores);
                                    nodo1.addHijo(nodo2);
                                }else{
                                    Nodo objeto = nodo_buscar_objeto(db, pivote.getObj()); //me devuelve el objeto verifico permisos
                                    boolean valor5 = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , pivote.getObj());
                                    if(objeto != null && valor5 == true){
                                        Nodo nodo2=crearnodo("obj", arbol.getNombre());
                                        for( Nodo reco : objeto.getHijos()){ //recoriendo el objeto
                                            String cadena = arbol.getNombre()+"."+reco.getNombre();//nombre.codigo
                                            String valores =devolver_valor_tb(cadena);
                                            if(valores.equalsIgnoreCase("")){
                                                verificacion=false;
                                                System.out.println("Este atributo no a sido instanciado");
                                            }else{
//                                                 if(reco.getValor().equalsIgnoreCase("text")){
//                                                     valores = "\""+valores+"\"";
//                                                 }
                                                 Nodo nodo3 = crearnodo(reco.getNombre(), valores);
                                                 nodo2.addHijo(nodo3);
                                            }
                                        }
                                        nodo1.addHijo(nodo2); //agregando obj
                                    }else{
                                        System.out.println("No tiene permisos");
                                        verificacion = false;
                                    }
                                    System.out.println("objeto");
                                }
                            }else{
                                //aqui se van a hacer las verificaciones de valores
                            } 
                        }
                        if(verificacion==true){
                            datos.addHijo(nodo1);
                            System.out.println("Insercion correcta");
                            Crearmaster.master();
                        }else{
                            System.out.println("Error de inserccion");
                        }
                    }else{
                        System.out.println("No se puede nuevo row");
                    }
//                    imprimir_tabla_simbolos();
                    expresiones.pila.pop();
                }else{
                    System.out.println("Parametros incorrectos");
                }
            }else{
                System.out.println("Se ingreso diferentes cantidad de parametros");
            }
        }else{
            System.out.println("La tabla no exite o no tiene permiso");
        }
    }
    
    public static boolean metodo_pk(Nodo datos,String nombre, String valor){
        for(Nodo raiz: datos.getHijos()){
            for(Nodo row: raiz.getHijos()){
                if(row.getNombre().equalsIgnoreCase(nombre)){
                    if(valor.equalsIgnoreCase("")){
                        return false;
                    }else if(valor.equalsIgnoreCase(row.getValor())){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean metodo_nnulo(String valor){
        if(valor.equals("")){
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean metodo_unico(Nodo datos,String nombre, String valor){
        for(Nodo raiz: datos.getHijos()){
            for(Nodo row: raiz.getHijos()){
                if(row.getNombre().equalsIgnoreCase(nombre)){
                    if(valor.equalsIgnoreCase(row.getValor())){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean metodo_buscar_fk(String Ntabla,String nombre, String valor){
        String base = pr1compilarodores2.principal2.db;  //la base de datos que se esta usando
        Nodo usuarios = pr1compilarodores2.principal2.usuarios; //usuarios
        Nodo master = pr1compilarodores2.principal2.master; //Nodo master
        Nodo db=nodo_buscar_bd(master, base); //Nodo db
        Nodo tabla=nodo_buscar_tabla(db, Ntabla); //Nodo de la tabla
        boolean veri = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , Ntabla);
        if(tabla != null && veri){ 
            Nodo datos = tabla.getHijos().get(0);//datos
            for(Nodo raiz: datos.getHijos()){
                for(Nodo row: raiz.getHijos()){
                    if(row.getNombre().equalsIgnoreCase(nombre)){
                        if(valor.equalsIgnoreCase("")){
                            return true;
                        }else if(valor.equalsIgnoreCase(row.getValor())){
                            return true;
                        }
                    }
                }
        }
        }else{
            System.err.println("No tiene permisos en llave foranea");
        }
        
        return false;
    }
    
    public static void sentencia_insertar_especial_tabla(Nodo usuarios,Nodo master, Nodo paquete){
        String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
        Nodo tabla=nodo_buscar_tabla(db, paquete.getValor()); //devuelve la tabla
        boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , paquete.getValor());
        if(tabla != null && valor){
            Nodo campos = tabla.getHijos().get(1); //Nodo campos
            Nodo datos = tabla.getHijos().get(0);
            realizar_ope_para(paquete.getHijos().get(1)); //Realizo las operaciones en valor
            Nodo insertar = paquete.getHijos().get(0);
            Nodo valores = paquete.getHijos().get(1);
            valor = verificar_especial_valores(insertar, valores,paquete.getValor());//Verifico que los tipos del paquete y campos sean iguales
            if(valor==true){ //los parametros esta correctos
                if(!expresiones.pila.empty()){ //si la pila esta vacia
                         tablasimbolos pivote = expresiones.pila.peek(); //guardar la pila anterior
                         tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                         tb.setAmbito(0); //ambito cero
                         expresiones.pila.push(tb);
                         llenar_especial_valores(insertar, valores, paquete.getValor(), pivote);
                }else{
                        tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                        tb.setAmbito(0); //ambito cero
                        expresiones.pila.push(tb); //ingreso a la pila un tabla de simbolos
                         llenar_especial_normal(insertar, valores, paquete.getValor());
                }
                imprimir_tabla_simbolos();
                
                Nodo llenado = insertar_campo(insertar, tabla);
                if(llenado != null){
                    datos.addHijo(llenado);
                    Crearmaster.master();
                    System.out.println("***Correcto**");
                }else{
                    System.out.println("*****Error*****");
                }
                expresiones.pila.pop();
                
            }else{
                System.out.println("hay error en los campos");
            }
        }else{
            System.out.println("No existe tabla o valores");
        }
    }
    
     public static boolean verificar_especial_valores(Nodo insertar, Nodo valores, String tb){
        //verificar que la tabla exita
        Nodo usuarios = pr1compilarodores2.principal2.usuarios; //usuarios
        Nodo master = pr1compilarodores2.principal2.master; //Nodo master
        String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db); //base de datos
        Nodo tabla=nodo_buscar_tabla(db, tb); //devuelve la tabla
        boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , tb);
        int con=0;
        if(tabla != null && valor){
             Nodo campos = tabla.getHijos().get(1);
            if(insertar.getHijos().size()==valores.getHijos().size()){ //verificar que insertar y valores tengan la misma cantidad
                for(Nodo hiInsert : insertar.getHijos()){ //recorro insertar
                    String tipo = devolever_tipo_tabla(campos, hiInsert.getNombre()); //el envio el nombre
                    if(tipo.equalsIgnoreCase("")==false){//verificando que exita el campo
                        Nodo elvalor = valores.getHijos().get(con);
                        if(elvalor.getValor().equalsIgnoreCase("obj")){
                            if(elvalor.getTexto().equalsIgnoreCase(tipo)==false){ //verificando que los tipos sean iguales
                                System.out.println("tipos de parametros incorrectos");
                                return false;
                            }
                        }else{
                            if(elvalor.getTipo().equalsIgnoreCase(tipo)==false){
                                System.out.println("tipos de parametros incorrectos");
                                return false;
                            }
                        }
                         ++con;
                    }else{
                        System.out.println("No exite este ");
                        return false;
                    }
                }
            }else{
                System.out.println("Usted ingreso diferente cantidad de parametros");
                return false;
            }
        }else{
            System.out.println("No tiene permisos o el objeto no existe");
        }
        return true;
    }
    
     public static void llenar_especial_valores(Nodo insertar, Nodo valores, String tb,tablasimbolos pivote){
        //verificar que la tabla exita
        tablasimbolos actual = expresiones.pila.peek();
        Nodo usuarios = pr1compilarodores2.principal2.usuarios; //usuarios
        Nodo master = pr1compilarodores2.principal2.master; //Nodo master
        String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db); //base de datos
        Nodo tabla=nodo_buscar_tabla(db, tb); //devuelve la tabla
        boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , tb);
        int con=0;
        if(tabla != null && valor){
             Nodo campos = tabla.getHijos().get(1);
            if(insertar.getHijos().size()==valores.getHijos().size()){ //verificar que insertar y valores tengan la misma cantidad
                for(Nodo hiInsert : insertar.getHijos()){ //recorro insertar
                    String tipo = devolever_tipo_tabla(campos, hiInsert.getNombre()); //el envio el nombre
                    if(tipo.equalsIgnoreCase("")==false){//verificando que exita el campo
                        Nodo elvalor = valores.getHijos().get(con);
                        if(elvalor.getValor().equalsIgnoreCase("obj")){
                            if(elvalor.getTexto().equalsIgnoreCase(tipo)){ //verificando que los tipos sean iguales
                                //Nombre @emp2 elval                    
                              Nodo obj=nodo_buscar_objeto(db,elvalor.getTexto());
                              valor=exite_entabla_pivote(elvalor.getNombre(),pivote);
                              if(obj!=null && valor==true ){//Si exite en la tabla pivote
                                    String tamano = Integer.toString(obj.getHijos().size()); //agrego el objeto;
                                    //imprimir_nodo(hiInsert, "insert");
                                    agregar_a_tabla_obj(hiInsert.getNombre(), "obj", tamano, elvalor.getTexto());
                                      for(Nodo primo : obj.getHijos()){ //recorriendo arbol master de objeto
                                        String cadena = elvalor.getNombre()+"."+primo.getNombre();//@emp + .val
                                        String cadena2 = hiInsert.getNombre()+"."+primo.getNombre();//trabajo+val
                                        tablasimbolos tep=devolver_elemento_tb_pivote(cadena, pivote);
                                        agregar_a_tabla_obj(cadena2, primo.getValor(), tep.getValor(), elvalor.getTexto());
                                    }
                               }

//               
                            }
                        }else{
                            if(elvalor.getTipo().equalsIgnoreCase(tipo)==true){
                                tablasimbolos tb2 = expresiones.pila.peek();
                                int numero = tb2.getAmbito();
                                imprimir_nodo(elvalor, "paquete");
                                imprimir_nodo(hiInsert, "paquete2");
                                tablasimbolos tb1 = new tablasimbolos(hiInsert.getNombre(), elvalor.getTipo(), elvalor.getNombre());
                                tb2.setAmbito(numero);
                                tb2.addHijo(tb1);
                            }
                        }
                         ++con;
                    }else{
                        System.out.println("No exite este ");
//                        return false;
                    }
                }
            }else{
                System.out.println("Usted ingreso diferente cantidad de parametros");
//                return false;
            }
        }else{
            System.out.println("No tiene permisos o el objeto no existe");
        }
//        return true;
    }
    
     public static void llenar_especial_normal(Nodo insertar, Nodo valores, String tb){
        //verificar que la tabla exita
        Nodo usuarios = pr1compilarodores2.principal2.usuarios; //usuarios
        Nodo master = pr1compilarodores2.principal2.master; //Nodo master
        String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
        Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db); //base de datos
        Nodo tabla=nodo_buscar_tabla(db, tb); //devuelve la tabla
        boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , tb);
        int con=0;
        if(tabla != null && valor){
             Nodo campos = tabla.getHijos().get(1);
            if(insertar.getHijos().size()==valores.getHijos().size()){ //verificar que insertar y valores tengan la misma cantidad
                for(Nodo hiInsert : insertar.getHijos()){ //recorro insertar
                    String tipo = devolever_tipo_tabla(campos, hiInsert.getNombre()); //el envio el nombre
                    if(tipo.equalsIgnoreCase("")==false){//verificando que exita el campo
                        Nodo elvalor = valores.getHijos().get(con);
                        if(elvalor.getValor().equalsIgnoreCase("obj")){
                            System.out.println("Error este es un objeto");
                        }else{
                            if(elvalor.getTipo().equalsIgnoreCase(tipo)==true){
                                tablasimbolos tb2 = expresiones.pila.peek();
                                int numero = tb2.getAmbito();
                                imprimir_nodo(elvalor, "paquete");
                                imprimir_nodo(hiInsert, "paquete2");
                                tablasimbolos tb1 = new tablasimbolos(hiInsert.getNombre(), elvalor.getTipo(), elvalor.getNombre());
                                tb2.setAmbito(numero);
                                tb2.addHijo(tb1);
                            }
                        }
                         ++con;
                    }else{
                        System.out.println("No exite este ");
//                        return false;
                    }
                }
            }else{
                System.out.println("Usted ingreso diferente cantidad de parametros");
//                return false;
            }
        }else{
            System.out.println("No tiene permisos o el objeto no existe");
        }
//        return true;
    }
     
     public static Nodo insertar_campo(Nodo insertar, Nodo tabla){
         tablasimbolos actual = expresiones.pila.peek();
         String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
         Nodo db=nodo_buscar_bd(pr1compilarodores2.principal2.master, pr1compilarodores2.principal2.db);

         Nodo campos = tabla.getHijos().get(1); //Nodo campos
         Nodo datos = tabla.getHijos().get(0);
         boolean valor=true;
         String cad;
         Nodo nodo1 = crearnodo("row", "");
         for(Nodo camp : campos.getHijos()){
             if(camp.getTipo().equalsIgnoreCase("obj")){
                tablasimbolos registro =devolver_elemento_tb_pivote(camp.getNombre(), actual);
                if(registro!=null){
                     Nodo objeto = nodo_buscar_objeto(db, camp.getValor());//Nombre del objeto   
                      if(objeto != null){
                        Nodo nodo2=crearnodo("obj", objeto.getNombre());
                        for( Nodo reco : objeto.getHijos()){ //recoriendo el objeto
                            String cadena = camp.getNombre()+"."+reco.getNombre();//nombre.codigo
//                            System.out.println("888 "+cadena);
                            String valores =devolver_valor_tb(cadena);
//                            System.out.println("Acciones.manejodetablas."+valores);
                            if(valores.equalsIgnoreCase("")){
                                   return null;
                            }else{
                                Nodo nodo3 = crearnodo(reco.getNombre(), valores);
                                nodo2.addHijo(nodo3);
                            }
                        }
                        nodo1.addHijo(nodo2);
                      }else{ System.out.println("El objeto no exite"); return null; }
                }else{
                    if(existe_complemento(camp, "<NNulo>")){
                        return null;
                    }
                }
             }else{
                 tablasimbolos registro =devolver_elemento_tb_pivote(camp.getNombre(), actual);
                 if(registro==null){
                     if(existe_complemento(camp, "<pk>")){
                         if(existe_complemento(camp, "<auto>")){
                             cad = complemento_auto(datos, camp.getNombre());
                             Nodo nodo2 = crearnodo(camp.getNombre(), cad);
                             nodo1.addHijo(nodo2);
                             imprimir_nodo(nodo2, "nodo2");
                         }else{
                             return null;
                         }
                     }else if(existe_complemento(camp, "<fk>")){
                         if(existe_complemento(camp, "<NNulo>")){
                             return null;
                         }
                     }else if(existe_complemento(camp, "<auto>")){
                         cad = complemento_auto(datos, camp.getNombre());
                         Nodo nodo2 = crearnodo(camp.getNombre(), cad);
                         nodo1.addHijo(nodo2);
                     }
                 }else{
                    if(existe_complemento(camp, "<pk>")){
                        if(metodo_pk(datos, camp.getNombre(), registro.getValor())){
                            Nodo nodo2 = crearnodo(camp.getNombre(), registro.getValor());
                            nodo1.addHijo(nodo2);
                        }else{
                            return null;
                        }
                    }else if(existe_complemento(camp, "<fk>")){
                        if(existe_complemento(camp, "<unico>")){
                            if(metodo_unico(datos, camp.getNombre(), registro.getValor())){
                                Nodo fk = retornar_existe_complemento(camp, "<fk>");
                                if(metodo_buscar_fk(fk.getValor(),fk.getTipo(),registro.getValor())){
                                    Nodo nodo2 = crearnodo(camp.getNombre(), registro.getValor());
                                    nodo1.addHijo(nodo2);
                                }else{
                                    return null;
                                }
                            }else{
                                return null;
                            }
                        }else{
                             Nodo fk = retornar_existe_complemento(camp, "<fk>");
                            if(metodo_buscar_fk(fk.getValor(),fk.getTipo(),registro.getValor())){
                                Nodo nodo2 = crearnodo(camp.getNombre(), registro.getValor());
                                nodo1.addHijo(nodo2);
                            }else{
                                return null;
                            }
                        }
                    }else if(existe_complemento(camp, "<unico>")){
                          if(metodo_unico(datos, camp.getNombre(), registro.getValor())){
                              Nodo nodo2 = crearnodo(camp.getNombre(), registro.getValor());
                              imprimir_nodo(nodo2, "entro");
                              nodo1.addHijo(nodo2);
                          }else{
                              return null;
                          }
                    }

                 }
             }
             
             
         }
         return nodo1;
         
     }
     
    public static boolean existe_complemento(Nodo campo, String comple){
        for(Nodo complemento : campo.getHijos()){
           if(complemento.getNombre().equalsIgnoreCase(comple)){
               return true;
            }
        }
        return false;
    }
    
    public static Nodo retornar_existe_complemento(Nodo campo, String comple){
        for(Nodo complemento : campo.getHijos()){
           if(complemento.getNombre().equalsIgnoreCase(comple)){
               return complemento;
            }
        }
        return null;
    }
    
    public static String complemento_auto(Nodo datos, String id){
        int n = datos.getHijos().size()-1;
        String valor;
        if(n>=0){
            valor = devolver_valor_row(datos.getHijos().get(n), id);
            int numero = Integer.parseInt(valor)+1;
            valor = Integer.toString(numero);
            return valor;
        }
        return "2";
        
    }
    
    public static String devolver_valor_row(Nodo row, String campo){
        for(Nodo primo : row.getHijos()){
            if(primo.getNombre().equalsIgnoreCase(campo)){
                return primo.getValor();
            }
        }
        return "error";
    }
    
}

