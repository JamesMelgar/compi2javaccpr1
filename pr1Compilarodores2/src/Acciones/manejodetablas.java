
package Acciones;


import java.util.Stack;
import java.util.Collections;
import pr1compilarodores2.Nodo;
import pr1compilarodores2.Recorrer;
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
                                        Nodo nodo2=crearnodo("obj", pivote.getObj());
                                        nodo2.setTipo(arbol.getNombre());
                                        imprimir_nodo(nodo2, "***base***");
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
                    nodo2.setTipo(camp.getNombre());
                    for( Nodo reco : objeto.getHijos()){ //recoriendo el objeto
                        String cadena = camp.getNombre()+"."+reco.getNombre();//nombre.codigo
                        String valores =devolver_valor_tb(cadena);
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
    for(Nodo complemento : campo.getHijos()){ //para fk
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

public static Nodo devolver_Nodo_row(Nodo row, String campo){
    for(Nodo primo : row.getHijos()){
        if(primo.getNombre().equalsIgnoreCase(campo)){
            return primo;
        }
    }
    return null;
}

public static Nodo devolver_Nodo_row_obj(Nodo row, String campo){
    for(Nodo primo : row.getHijos()){
        if(primo.getTipo().equalsIgnoreCase(campo)){
            return primo;
        }
    }
    return null;
}

public static void Sentencia_Actualizar(Nodo usuarios,Nodo master, Nodo paquete){
    String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
    Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
    Nodo tabla=nodo_buscar_tabla(db, paquete.getValor()); //devuelve la tabla
    boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , paquete.getValor());
    if(tabla != null && valor){
        Nodo campos = paquete.getHijos().get(0);
        Nodo valores = paquete.getHijos().get(1);
        realizar_ope_para(valores);
        valor = verificar_especial_valores(campos, valores, paquete.getValor()); //verifico que los campos sean iguales 
        if(campos.getHijos().size()==valores.getHijos().size() && valor){
            if(!expresiones.pila.empty()){ //si la pila esta vacia
                     tablasimbolos pivote = expresiones.pila.peek(); //guardar la pila anterior
                     tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                     tb.setAmbito(0); //ambito cero
                     expresiones.pila.push(tb);
                     llenar_especial_valores(campos, valores, paquete.getValor(), pivote);
            }else{
                    tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                    tb.setAmbito(0); //ambito cero
                    expresiones.pila.push(tb); //ingreso a la pila un tabla de simbolos
                     llenar_especial_normal(campos, valores, paquete.getValor());
            }
            //imprimir_tabla_simbolos();
            Nodo row = tabla.getHijos().get(0);
            for(Nodo cambios : row.getHijos()){
                imprimir_nodo(cambios, "base");
                actualizar_campos(tabla, cambios);
                Crearmaster.master();
            }
            expresiones.pila.pop();
        }else{
            System.out.println("Cantidad de valores y parametros distintos");
        }
    }else{
        System.out.println("Usted No tiene permiso o la tabla exite");
    }
}

public static void actualizar_campos(Nodo tabla, Nodo row){
     tablasimbolos actual = expresiones.pila.peek();
     String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
     Nodo db=nodo_buscar_bd(pr1compilarodores2.principal2.master, pr1compilarodores2.principal2.db);
     Nodo campos = tabla.getHijos().get(1); //Nodo campos
     Nodo datos = tabla.getHijos().get(0);
     boolean valor=true;
     int contador = 0;
     for(tablasimbolos tb: actual.getSiguiente()){
         if(contador==0){
             if(tb.getObj().equalsIgnoreCase("")){
                 Nodo camp = devolver_campo(campos, tb.getNombre());//busca en archivo db
                 if(existe_complemento(camp, "<pk>")){
                     if(metodo_pk(datos, camp.getNombre(), tb.getValor())){
                        Nodo actualizar = devolver_Nodo_row(row, tb.getNombre());
                        if(actualizar != null){
                            actualizar.setValor(tb.getValor());
                        }else{
                            Nodo nodo2 = crearnodo(tb.getNombre(), tb.getValor());
                            row.addHijo(nodo2);
                        }
                     }else{
                         System.out.println("No se pudo agregar por error de llave primaria");
                     }
                 }else if(existe_complemento(camp, "<fk>")){
                        if(existe_complemento(camp, "<unico>")){
                            if(metodo_unico(datos, camp.getNombre(), tb.getValor())){
                                Nodo fk = retornar_existe_complemento(camp, "<fk>");
                                if(metodo_buscar_fk(fk.getValor(),fk.getTipo(),tb.getValor())){
                                    Nodo actualizar = devolver_Nodo_row(row, tb.getNombre());
                                    if(actualizar != null){
                                        actualizar.setValor(tb.getValor());
                                    }else{
                                        Nodo nodo2 = crearnodo(tb.getNombre(), tb.getValor());
                                        row.addHijo(nodo2);
                                    }
                                }else{
                                    System.out.println("Error esta llave foranea no existe");
                                }
                            }else{
                                System.out.println("Error esta llave foranea debe de ser unica");
                            }
                        }else{
                            Nodo fk = retornar_existe_complemento(camp, "<fk>");
                            if(metodo_buscar_fk(fk.getValor(),fk.getTipo(),tb.getValor())){
                                Nodo actualizar = devolver_Nodo_row(row, tb.getNombre());
                                if(actualizar != null){
                                    actualizar.setValor(tb.getValor());
                                }else{
                                    Nodo nodo2 = crearnodo(tb.getNombre(), tb.getValor());
                                    row.addHijo(nodo2);
                                }
                            }else{
                                System.out.println("Error esta llave foranea no existe");
                            }
                        }
                    }else if(existe_complemento(camp, "<unico>")){
                        if(metodo_unico(datos, camp.getNombre(), tb.getValor())){
                            Nodo actualizar = devolver_Nodo_row_obj(row, tb.getNombre());
                            if(actualizar != null){
                                    actualizar.setValor(tb.getValor());
                            }else{
                                Nodo nodo2 = crearnodo(tb.getNombre(), tb.getValor());
                                row.addHijo(nodo2);
                            }
                        }else{
                            System.out.println("Existe complemento unico");
                        }
                    }else{
                        Nodo actualizar = devolver_Nodo_row(row, tb.getNombre());
                        if(actualizar != null){
                            actualizar.setValor(tb.getValor());
                        }else{
                            Nodo nodo2 = crearnodo(tb.getNombre(), tb.getValor());
                            row.addHijo(nodo2);
                        }
                    }
             }else{
                 Nodo nodo2=crearnodo("obj", tb.getObj());
                 nodo2.setTipo(tb.getNombre());
                 Nodo obj = nodo_buscar_objeto(db, tb.getObj());
                 contador=obj.getHijos().size();
                 boolean b1=true;
                 if(obj != null){
                     for(Nodo recorrer : obj.getHijos()){//recorro el obj para buscar el nombre en la tabla de simbolos
                        String c1 = tb.getNombre() + "." +recorrer.getNombre();
                        String v1 = devolver_valor_tb(c1); //valor en tabla de simbolos
                        if(v1.equalsIgnoreCase("")==false){
                            Nodo nodo1=crearnodo(recorrer.getNombre(), v1);
                            nodo2.addHijo(nodo1);
                        }else{
                            b1=false;
                        }
                     }
                     if(b1=true){
                         Nodo actualizar = devolver_Nodo_row_obj(row, tb.getNombre());
                         imprimir_nodo(actualizar, "actualizar");
                         if(actualizar!=null){
                             System.out.println("entro pero hay error");
                             actualizar.setHijos(nodo2.getHijos());
                             imprimir_nodo(nodo2, "nodo2");
                         }else{
                            row.addHijo(nodo2);
                         }
                     }else{
                         System.out.println("No todos los obj fueron instanciados");
                     }
                 }else{
                     System.out.println("Este. objeto no exite");
                 }
             }
         }else{
             --contador;
         }
     }
 }

public static Nodo devolver_campo(Nodo campo, String nombre){
       for( Nodo camp : campo.getHijos()){
           if(camp.getNombre().equalsIgnoreCase(nombre)){
               return camp;
           }
       }
       return null;
 }

public static void Sentencia_Actualizar_cond(Nodo usuarios,Nodo master, Nodo paquete){
    String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
    Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
    Nodo tabla=nodo_buscar_tabla(db, paquete.getValor()); //devuelve la tabla
    boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , paquete.getValor());
    if(tabla != null && valor){
        Nodo campos = paquete.getHijos().get(0);
        Nodo valores = paquete.getHijos().get(1);
        realizar_ope_para(valores);
        valor = verificar_especial_valores(campos, valores, paquete.getValor()); //verifico que los campos sean iguales 
        if(campos.getHijos().size()==valores.getHijos().size() && valor){
            if(!expresiones.pila.empty()){ //si la pila esta vacia
                     tablasimbolos pivote = expresiones.pila.peek(); //guardar la pila anterior
                     tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                     tb.setAmbito(0); //ambito cero
                     expresiones.pila.push(tb);
                     llenar_especial_valores(campos, valores, paquete.getValor(), pivote);
            }else{
                    tablasimbolos tb = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                    tb.setAmbito(0); //ambito cero
                    expresiones.pila.push(tb); //ingreso a la pila un tabla de simbolos
                     llenar_especial_normal(campos, valores, paquete.getValor());
            }
            //imprimir_tabla_simbolos();
            Nodo row = tabla.getHijos().get(0);
            Nodo condicion = paquete.getHijos().get(2);
            for(Nodo cambios : row.getHijos()){
                tablasimbolos tb3 = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
                tb3.setAmbito(0); //ambito cero
                expresiones.pila.push(tb3); //ingreso a la pila un tabla de simbolos 
                tabla_de_simbolos_row(cambios, paquete.getValor() , "");
                Nodo nodo8 = expresiones.expresiones(condicion.getHijos().get(0));
                if(nodo8.getNombre().equalsIgnoreCase("1")){
                    expresiones.pila.pop();
                    actualizar_campos(tabla, cambios);
                    Crearmaster.master();
                }else{
                    expresiones.pila.pop();
                }
                imprimir_nodo(nodo8, "nodo8");
                //imprimir_tabla_simbolos();

            }
            expresiones.pila.pop();
        }else{
            System.out.println("Cantidad de valores y parametros distintos");
        }
    }else{
        System.out.println("Usted No tiene permiso o la tabla exite");
    }
}

public static void tabla_de_simbolos_row(Nodo row, String Ntabla,String texto){
    String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
    Nodo master = pr1compilarodores2.principal2.master;
    Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
    Nodo tabla=nodo_buscar_tabla(db, Ntabla); //devuelve la tabla
    tablasimbolos tb = expresiones.pila.peek();
    if(db != null && tabla != null){
        Nodo campos = tabla.getHijos().get(1);
        for(Nodo hirow : row.getHijos()){
            if(hirow.getNombre().equalsIgnoreCase("obj")){
                for(Nodo campobj : hirow.getHijos()){
                   String tipo = buscar_tipo_campo_obj(campos, campobj.getNombre(), hirow.getValor());
                   String cadena = texto + hirow.getTipo() + row.getNombre();
                   agregar_a_tabla_obj(texto,tipo, row.getValor(), ""); 
                }
            }else{
                //imprimir_nodo(hirow, "texto");
                String cadena = texto + hirow.getNombre();
                agregar_a_tabla_obj(cadena,hirow.getTipo(), hirow.getValor(), "");
            }
       }
    }
}

public static String buscar_tipo_campo(Nodo campo, String buscar){
    for(Nodo cp : campo.getHijos()){
        if(cp.getNombre().equalsIgnoreCase(buscar)){
            return cp.getValor();
        }
    }
    return "error";
}

public static String buscar_tipo_campo_obj(Nodo campo, String buscar, String obj){
    for(Nodo cp : campo.getHijos()){
        if(cp.getTipo().equalsIgnoreCase(obj)){
            for(Nodo ob : cp.getHijos()){
               if(cp.getNombre().equalsIgnoreCase(buscar)){
                   return cp.getValor();
               } 
            }             
        }
    }
    return "error";
}

public static void Sentencia_seleccionar(Nodo usuarios,Nodo master, Nodo paquete){
     String base = pr1compilarodores2.principal2.db;  //la base de datos que esta utilizada
     Nodo db=nodo_buscar_bd(master, pr1compilarodores2.principal2.db);
     Nodo listaTabla = paquete.getHijos().get(1); 
     if(listaTabla.getHijos().size()==1){//Solo tiene un hijo.
         String nombre_tabla = listaTabla.getHijos().get(0).getNombre();
         Nodo tabla = nodo_buscar_tabla(db,nombre_tabla);
         boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , nombre_tabla);
         if(tabla != null && valor){
             Nodo campos=new Nodo(""); 
             campos=clonar(campos, tabla.getHijos().get(1));
             Nodo datos = tabla.getHijos().get(0);
             Nodo tb1 = new Nodo("tb1");
             Nodo data = new Nodo("data");
             data=eliminar_complementos(db,campos,data,nombre_tabla,""); //eliminar complementos para crear el arbol 
             
             for(Nodo row: datos.getHijos()){
                 Nodo registro = new Nodo(" ");
                 registro = clonar(registro,data);
                 registro=llenar_data(registro, row);               
                 tb1.addHijo(registro);  //Ya tengo los registros
             }
             instrucciones_seleccion(paquete, tb1);
         }else{
             System.out.println("No tiene Permiso o la tabla no exite");
         }
     }else{//Tiene dos hijos
         String nombre_tabla = listaTabla.getHijos().get(0).getNombre();
         Nodo tabla = nodo_buscar_tabla(db,nombre_tabla);
         boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , nombre_tabla);
         if(tabla != null && valor){
             Nodo campos=new Nodo(""); 
             campos=clonar(campos, tabla.getHijos().get(1));
             Nodo datos = tabla.getHijos().get(0);
             Nodo tb1 = new Nodo("tb1");
             Nodo data = new Nodo("data");
             String Ntabla=nombre_tabla+".";
             data=eliminar_complementos(db,campos,data,nombre_tabla,Ntabla); //eliminar complementos para crear el arbol 
             for(Nodo row: datos.getHijos()){
                 Nodo registro = new Nodo(" ");
                 registro = clonar(registro,data);
                 registro=llenar_data(registro, row);               
                 tb1.addHijo(registro);  //Ya tengo los registros de tabla 1
             }
             int v=0; boolean veri=true;
             for(Nodo ntablas : listaTabla.getHijos()){
                 if(v>0){
                     nombre_tabla = listaTabla.getHijos().get(v).getNombre();
                     tabla = nodo_buscar_tabla(db,nombre_tabla);
                     valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , nombre_tabla);
                     if(tabla != null && valor){ //Llenando la tabla 2
                         Nodo campos2=new Nodo(""); 
                         campos2=clonar(campos2, tabla.getHijos().get(1));
                         Nodo datos2 = tabla.getHijos().get(0);
                         Nodo tb2 = new Nodo("tb2");
                         Nodo data2 = new Nodo("data");
                         Ntabla=nombre_tabla+".";
                         data2=eliminar_complementos(db,campos2,data2,nombre_tabla,Ntabla);
                         for(Nodo row: datos2.getHijos()){
                             Nodo registro = new Nodo(" ");
                             registro = clonar(registro,data2);
                             registro=llenar_data(registro, row);               
                             tb2.addHijo(registro);  //Ya tengo los registros de tabla 1
                         }
                        tb1 = unir_tb1_tb2(tb1, tb2);
                     }else{
                         veri=false;
                     }
                 }
                 ++v;
             }
             if(veri==true){
                 instrucciones_seleccion(paquete, tb1);
             }else{
                 System.out.println("Hubo algun error, tabla no exite o no tiene permiso");
             }
             
             
         }else{
             System.out.println("No tiene permiso 0 no exite la tabla");
         }
     }
}

public static void instrucciones_seleccion(Nodo paquete, Nodo tb1){
    switch (paquete.getHijos().size()) {
        case 2:
            imprimir_seleccion(tb1);
            break;
        case 3:
            {
                Nodo hijo=paquete.getHijos().get(2);
                if(hijo.getNombre().equalsIgnoreCase("ordenar")){
                    Nodo orden = hijo.getHijos().get(0);
                    tb1 = ordenar(tb1,orden);
                    if(tb1!=null){//verificando que no haya ningun error
                        if(hijo.getValor().equalsIgnoreCase("asc")){
                            tb1 = Ordenar_asc(tb1);
                            listaid_Seleccion(paquete, tb1);
                        }else{//desc
                            tb1 = Ordenar_desc(tb1);
                            listaid_Seleccion(paquete, tb1);
                        }
                    }else{
                        System.out.println("No exite el id de ordenamiento");
                    }
                }else{ //condicion
                    tb1=condicionwhere(paquete, tb1);
                    listaid_Seleccion(paquete, tb1);
                }         break;
            }
        case 4:
            {
                tb1=condicionwhere(paquete, tb1); //ordenamos si es el caso
                Nodo hijo=paquete.getHijos().get(3);
                Nodo orden = hijo.getHijos().get(0);
                tb1 = ordenar(tb1,orden);
                if(tb1!=null){//verificando que no haya ningun error
                    if(hijo.getValor().equalsIgnoreCase("asc")){
                        tb1 = Ordenar_asc(tb1);
                        listaid_Seleccion(paquete, tb1);
                    }else{//desc
                        tb1 = Ordenar_desc(tb1);
                        listaid_Seleccion(paquete, tb1);
                    }
                }else{
                    System.out.println("No exite el id de ordenamiento");
                }     break;
            }
        default:
            break;
    }
}

public static void listaid_Seleccion(Nodo paquete, Nodo tb1){
    Nodo listaid = paquete.getHijos().get(0);
    if(listaid.getNombre().equalsIgnoreCase("*")){
        imprimir_seleccion(tb1);
    }else{
        Nodo new_tb1 = new Nodo("tb1");
        for(Nodo rec: tb1.getHijos()){
            Nodo new_data = new Nodo("data");
            for(Nodo data: rec.getHijos()){
                for(Nodo id : listaid.getHijos()){
                    if(id.getNombre().equalsIgnoreCase(data.getNombre())){
                        new_data.addHijo(data);
                    }
                }
            }
            new_tb1.addHijo(new_data);
        }
        imprimir_seleccion(new_tb1);
    }
}

public static void imprimir_seleccion(Nodo tb1){
    for(Nodo rec: tb1.getHijos()){
        System.out.println("data");
        for(Nodo data: rec.getHijos()){
            imprimir_nodo(data, "Seleccion");
        }
    }
}

public static Nodo condicionwhere(Nodo paquete, Nodo tb1){
    Nodo hijo=paquete.getHijos().get(2);
    Nodo new_tb1 = new Nodo("tb1");
    for(Nodo rec: tb1.getHijos()){
           tablasimbolos simbolos = new tablasimbolos("nombre", "tipo", "valor"); //creo la tabla de simbolos
           Nodo exp = hijo.getHijos().get(0);
           expresiones.pila.push(simbolos);
           agregar_seleccionar_tabla_desimbolos(rec);
           Nodo nodo8 = expresiones.expresiones(exp);
           if(nodo8.getNombre().equalsIgnoreCase("1")){
               Nodo copia = new Nodo("");
               copia=clonar(copia, rec);
               new_tb1.addHijo(copia);
          }else{
               System.out.println("condicion ");
          }
       }         
    expresiones.pila.pop();
    return new_tb1;
}

public static Nodo ordenar(Nodo tb1, Nodo orden){

    boolean var = false;
    for(Nodo data : tb1.getHijos()){ //los hijos data
        for(Nodo campos : data.getHijos()){ //vamos a obtene el valor del campo y subirlo
            if(campos.getNombre().equalsIgnoreCase(orden.getNombre())){ //comparamos si el nombre es igual
              data.setValor(campos.getValor());
              data.setTipo(orden.getNombre());
              var=true;
            }
        }
    }
    if(var==true){
        return tb1;
    }else{
        return null;
    }
    
}

public static Nodo Ordenar_asc(Nodo tb1){
    int numero = tb1.getHijos().size(); 
    for (int i = 1; i < numero; i++) {
        for (int j = 0; j < numero-1; j++) {
            String c1 = tb1.getHijos().get(j).getValor();
            String c2 = tb1.getHijos().get(j+1).getValor();
            if(c1.compareTo(c2)>0){
                System.out.println("entro");
                Nodo h1 = tb1.getHijos().get(j);
                Nodo h2 = tb1.getHijos().get(j+1);
                Nodo temp=new Nodo("");
                temp = clonar(temp, h1);
                h1.getHijos().clear();
                h1 = clonar(h1, h2);
                h2.getHijos().clear();
                h2 = clonar(h2, temp);
            }
        }
    }
    return tb1;
}

public static Nodo Ordenar_desc(Nodo tb1){
   int numero = tb1.getHijos().size(); 
    for (int i = 1; i < numero; i++) {
        for (int j = 0; j < numero-1; j++) {
            String c1 = tb1.getHijos().get(j).getValor();
            String c2 = tb1.getHijos().get(j+1).getValor();
            if(c1.compareTo(c2)<0){
                Nodo h1 = tb1.getHijos().get(j);
                Nodo h2 = tb1.getHijos().get(j+1);
                Nodo temp=new Nodo("");
                temp = clonar(temp, h1);
                h1.getHijos().clear();
                h1 = clonar(h1, h2);
                h2.getHijos().clear();
                h2 = clonar(h2, temp);
            }
        }
    }
    return tb1;
}

public static Nodo igualarNodo(Nodo n1 ,Nodo n2){
    n1.setValor(n2.getValor());
    n1.setNumNodo(n2.getNumNodo());
    n1.setFila(n2.getFila());
    n1.setColumna(n2.getColumna());
    n1.setTipo(n2.getTipo());
    n1.setTexto(n2.getTexto());
    n1.setTabla(n2.getTabla());
    n1.setCampo(n2.getCampo());
    n1.setObj(n2.getObj());
    n1.setNombre(n2.getNombre());
    n1.setHijos(n2.getHijos());
    return n1;
}

public static void agregar_seleccionar_tabla_desimbolos(Nodo reco){
    tablasimbolos simbolos = expresiones.pila.peek();
    for(Nodo hijos : reco.getHijos()){
        agregar_a_tabla(hijos.getNombre(),hijos.getTipo(),hijos.getValor());
    }
}

public static Nodo llenar_data(Nodo registro,Nodo row){
    //boolean valor = accpaquete.tiene_permiso(usuarios, master, base, pr1compilarodores2.principal2.usua , paquete.getValor());
    for(Nodo Recorrer : registro.getHijos()){
        String valor;
        if(Recorrer.getObj().equals("")==false){
            valor = buscar_valor_data(row, Recorrer.getCampo(), Recorrer.getObj(), true);
        }else{        
            valor = buscar_valor_data(row, Recorrer.getCampo(),"",false);
        }
        Recorrer.setValor(valor);
    }
    return registro;
}

public static String buscar_valor_data(Nodo row, String No_campo, String No_obj, boolean es_obj){
    //true es objeto, false no es objeto
    for(Nodo recorrer : row.getHijos()){
        if(recorrer.getNombre().equalsIgnoreCase("obj")){
            if(es_obj==true){
                if(recorrer.getTipo().equalsIgnoreCase(No_campo)){
                     for(Nodo hijo: recorrer.getHijos()){
                        if(hijo.getNombre().equalsIgnoreCase(No_obj)){
                            String cadena;
                            cadena = hijo.getValor();
                            return cadena;
                        }
                    }
                }
            }
        }else if(recorrer.getNombre().equalsIgnoreCase(No_campo)){
            String cadena;
            cadena = recorrer.getValor();
            return cadena;
        }
    }
    return "";
}

public static Nodo eliminar_complementos(Nodo db,Nodo campo,Nodo data,String tabla, String Ntabla){
    String nombre;
    for(Nodo recorrer : campo.getHijos()){
        recorrer.getHijos().clear();
        if(recorrer.getTipo().equalsIgnoreCase("obj")){
            Nodo obj = nodo_buscar_objeto(db, recorrer.getValor());
            if(obj != null){
                for(Nodo recorrerobj : obj.getHijos()){
                    nombre = Ntabla + recorrer.getNombre() +"."+ recorrerobj.getNombre();
                    Nodo nuevo = new Nodo("");
                    nuevo = agregar_datos_data(nuevo, nombre, tabla, recorrer.getNombre(), recorrerobj.getNombre(),recorrerobj.getValor());
                    data.addHijo(nuevo);
                }
            }else{
                return null;
            }
        }else{
            //nTabla ya tiene que venir con el .tabla
            nombre = Ntabla + recorrer.getNombre();
            recorrer = agregar_datos_data(recorrer, nombre, tabla, recorrer.getNombre(), "",recorrer.getValor());
            data.addHijo(recorrer);
        }
    }
    return data;
}

public static Nodo agregar_datos_data(Nodo data, String nombre, String tabla, String campo, String obj, String tipo){
        data.setNombre(nombre);
        data.setTabla(tabla);
        data.setCampo(campo);
        data.setObj(obj);
        data.setTipo(tipo);
        data.setValor("");
        return data;
}

public static void imprimir_data(Nodo obj){
    String cadena = "no:"+obj.getNombre()+" va:"+obj.getValor()+
            " tb:"+obj.getTabla()+" cp:"+obj.getCampo()+" obj:"+obj.getObj()+" tp:"+obj.getTipo();
    System.out.println("Data: "+cadena);
}
public static Nodo clonar(Nodo n1 ,Nodo n2){
    n1.setValor(n2.getValor());
    n1.setNumNodo(n2.getNumNodo());
    n1.setFila(n2.getFila());
    n1.setColumna(n2.getColumna());
    n1.setTipo(n2.getTipo());
    n1.setTexto(n2.getTexto());
    n1.setTabla(n2.getTabla());
    n1.setCampo(n2.getCampo());
    n1.setObj(n2.getObj());
    n1.setNombre(n2.getNombre());
    for(Nodo rec: n2.getHijos()){
        Nodo nuevo = new Nodo("");
        nuevo = clonar(nuevo,rec);
        n1.addHijo(nuevo);
    }
    return n1;
}

public static Nodo unir_tb1_tb2(Nodo tb1, Nodo tb2){
    Nodo tb = new Nodo("tb1");
    for(Nodo arbol1 : tb1.getHijos()){
        for(Nodo arbol2 : tb2.getHijos()){
            Nodo data = new Nodo("data");
            for(Nodo rec1 : arbol1.getHijos()){
                Nodo n1 = new Nodo("");
                n1 = clonar(n1, rec1);
                data.addHijo(n1);
            }
            for(Nodo rec2 : arbol2.getHijos()){
                Nodo n2 = new Nodo("");
                n2 = clonar(n2, rec2);
                data.addHijo(rec2);
            }
            tb.addHijo(data);
        }
    }
    return tb;
}
}

