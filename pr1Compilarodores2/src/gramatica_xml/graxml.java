/* graxml.java */
/* Generated By:JavaCC: Do not edit this line. graxml.java */
package gramatica_xml;
import pr1compilarodores2.Nodo;
import java.io.*;
import java.util.*;

public class graxml implements graxmlConstants {
    public static int contador = 1;
    public static void main( String[] args )throws ParseException
        {
                System.out.println("analisis correcto");
        }

//******************************************************************************************************Inicio
  final public 
Nodo inicio(Nodo raiz) throws ParseException {
    raiz = interno(raiz);
{if ("" != null) return raiz;}
    jj_consume_token(0);
    throw new Error("Missing return statement in function");
  }

  final public Nodo interno(Nodo raiz) throws ParseException {Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tgmi:{
      raiz = tabmaestro(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Registroi:{
      raiz = registro(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Artablai:{
      raiz = archivotabla(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Arobji:{
      raiz = archivoobj(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Arusui:{
      raiz = arusu(raiz);
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//******************************************************************************************ARCHIVO MAESTRO
  final public 
Nodo tabmaestro(Nodo raiz) throws ParseException {
    jj_consume_token(tgmi);
Nodo tmp = new Nodo("Maestro");
                tmp.setNumNodo(graxml.contador++);
                raiz = tmp;
    raiz = maestro(raiz);
    jj_consume_token(tgmf);
{if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

//<Dbi> Manombre() <Dbf> Masig()
  final public Nodo maestro(Nodo raiz) throws ParseException {Nodo nodo1;
    jj_consume_token(Dbi);
    raiz = Manombre(raiz);
    jj_consume_token(Dbf);
    raiz = Masig(raiz);
{if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo Manombre(Nodo raiz) throws ParseException {Token t1;
    Nodo nodo1;
    jj_consume_token(Nombrei);
    t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                        tmp.setNumNodo(graxml.contador++);
                        tmp.setColumna(t1.beginColumn);
                        tmp.setFila(t1.beginLine);
                        tmp.setValor("0");
                        nodo1 = tmp;
    jj_consume_token(Nombref);
    nodo1 = Maruta(nodo1);
nodo1 = pr1compilarodores2.CrearArbol.Analisisxml(nodo1, nodo1.getValor());
                                    raiz.addHijo(nodo1);
                                    {if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo Maruta(Nodo hermano) throws ParseException {Token t1;
    jj_consume_token(Pathi);
    t1 = jj_consume_token(Ruta);
hermano.setValor(t1.image);
    jj_consume_token(Pathf);
{if ("" != null) return hermano;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo Masig(Nodo raiz) throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Dbi:{
      raiz = maestro(raiz);
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
{if ("" != null) return raiz;}
    }
    throw new Error("Missing return statement in function");
  }

//************************************************************************************Archivo registro
  final public 
Nodo registro(Nodo raiz) throws ParseException {
    jj_consume_token(Registroi);
    raiz = recontenido(raiz);
    jj_consume_token(Registrof);
{if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

/*
  <Procedurei> nodo1=Maruta(null) <Proceduref> recontenido()
| <Objecti> Maruta()<Objectf> recontenido()
| <Tablai> tabla() <Tablaf>  recontenido()
| { return; }
 */
  final public 

Nodo recontenido(Nodo raiz) throws ParseException {Nodo nodo1;
    Nodo nodo2;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Procedurei:{
      jj_consume_token(Procedurei);
Nodo tmp = new Nodo("Procedure");
                     tmp.setNumNodo(graxml.contador++);
                     tmp.setValor("0");
                     nodo1 = tmp;
      nodo1 = Maruta(nodo1);
      jj_consume_token(Proceduref);
raiz.addHijo(nodo1);
      raiz = recontenido(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Objecti:{
      jj_consume_token(Objecti);
Nodo tmp = new Nodo("Objeto");
                  tmp.setNumNodo(graxml.contador++);
                  tmp.setValor("0");
                  nodo1 = tmp;
      nodo1 = Maruta(nodo1);
nodo1 = pr1compilarodores2.CrearArbol.Analisisxml(nodo1, nodo1.getValor());
      jj_consume_token(Objectf);
raiz.addHijo(nodo1);
      raiz = recontenido(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Tablai:{
      jj_consume_token(Tablai);
Nodo tmp = new Nodo("Tabla");
                 tmp.setNumNodo(graxml.contador++);
                 tmp.setValor("0");
                 nodo1 = tmp;
      nodo1 = tabla(nodo1);
      jj_consume_token(Tablaf);
raiz.addHijo(nodo1);
      raiz = recontenido(raiz);
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[2] = jj_gen;
{if ("" != null) return raiz;}
    }
    throw new Error("Missing return statement in function");
  }

//<Nombrei> <Id> <Nombref> <Pathi> <Ruta> <Pathf> <Rowsi> tbcont() <Rowsf>
  final public 
Nodo tabla(Nodo raiz) throws ParseException {Token t1;
    Nodo nodo1;
    jj_consume_token(Nombrei);
    t1 = jj_consume_token(Id);
raiz.setNombre(t1.image);
    jj_consume_token(Nombref);
    jj_consume_token(Pathi);
    t1 = jj_consume_token(Ruta);
raiz.setValor(t1.image);
                                  Nodo tmp1 = new Nodo("datos");
                                  tmp1.setNumNodo(graxml.contador++);
                                  tmp1.setValor(t1.image);
                                  nodo1 = tmp1;
                        nodo1 = pr1compilarodores2.CrearArbol.Analisisxml(nodo1, nodo1.getValor());
                        raiz.addHijo(nodo1);
    jj_consume_token(Pathf);
    jj_consume_token(Rowsi);
Nodo tmp = new Nodo("campos");
                      tmp.setNumNodo(graxml.contador++);
                      tmp.setValor("0");
                      nodo1 = tmp;
    nodo1 = tbcont(nodo1);
    jj_consume_token(Rowsf);
raiz.addHijo(nodo1); {if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

/*
 <Inti> <Id> <Intf> complemento() tbcont()
|<Texti> <Id> <Textf> complemento() tbcont()
|<Doublei> <Id> <Doublef> complemento() tbcont()
|<Booli> <Id> <Boolf> complemento() tbcont()
|<Datei> <Id> <Datef> complemento() tbcont()
|<Dateti> <Id> <Datetf> complemento()  tbcont()
|<Obji> <Id> <Objf>
| {return;}
*/
  final public 
Nodo tbcont(Nodo raiz) throws ParseException {Nodo nodo1;
    Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Inti:{
      jj_consume_token(Inti);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                     tmp.setNumNodo(graxml.contador++);
                     tmp.setColumna(t1.beginColumn);
                     tmp.setFila(t1.beginLine);
                     tmp.setValor("int");
                     nodo1 = tmp;
      jj_consume_token(Intf);
      nodo1 = complemento(nodo1);
raiz.addHijo(nodo1);
      raiz = tbcont(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Texti:{
      jj_consume_token(Texti);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                     tmp.setNumNodo(graxml.contador++);
                     tmp.setColumna(t1.beginColumn);
                     tmp.setFila(t1.beginLine);
                     tmp.setValor("texto");
                     nodo1 = tmp;
      jj_consume_token(Textf);
      nodo1 = complemento(nodo1);
raiz.addHijo(nodo1);
      raiz = tbcont(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Doublei:{
      jj_consume_token(Doublei);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                        tmp.setNumNodo(graxml.contador++);
                        tmp.setColumna(t1.beginColumn);
                        tmp.setFila(t1.beginLine);
                        tmp.setValor("double");
                        nodo1 = tmp;
      jj_consume_token(Doublef);
      nodo1 = complemento(nodo1);
raiz.addHijo(nodo1);
      raiz = tbcont(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Booli:{
      jj_consume_token(Booli);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                     tmp.setNumNodo(graxml.contador++);
                     tmp.setColumna(t1.beginColumn);
                     tmp.setFila(t1.beginLine);
                     tmp.setValor("Bool");
                     nodo1 = tmp;
      jj_consume_token(Boolf);
      nodo1 = complemento(nodo1);
raiz.addHijo(nodo1);
      raiz = tbcont(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Datei:{
      jj_consume_token(Datei);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                      tmp.setNumNodo(graxml.contador++);
                      tmp.setColumna(t1.beginColumn);
                      tmp.setFila(t1.beginLine);
                      tmp.setValor("date");
                      nodo1 = tmp;
      jj_consume_token(Datef);
      nodo1 = complemento(nodo1);
raiz.addHijo(nodo1);
      raiz = tbcont(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Dateti:{
      jj_consume_token(Dateti);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                      tmp.setNumNodo(graxml.contador++);
                     tmp.setColumna(t1.beginColumn);
                     tmp.setFila(t1.beginLine);
                     tmp.setValor("datetime");
                     nodo1 = tmp;
      jj_consume_token(Datetf);
      nodo1 = complemento(nodo1);
raiz.addHijo(nodo1);
      raiz = tbcont(raiz);
{if ("" != null) return raiz;}
      break;
      }
    case Obji:{
      jj_consume_token(Obji);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                     tmp.setNumNodo(graxml.contador++);
                     tmp.setColumna(t1.beginColumn);
                     tmp.setFila(t1.beginLine);
                     tmp.setValor("obj");
                     nodo1 = tmp;
      jj_consume_token(Objf);
      nodo1 = complemento(nodo1);
raiz.addHijo(nodo1);
      raiz = tbcont(raiz);
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
{if ("" != null) return raiz;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo complemento(Nodo hijo) throws ParseException {Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Pk:
    case Nulo:
    case Auto:
    case Unico:
    case Nnulo:{
      t1 = tipocomplemento();
Nodo tmp = new Nodo(t1.image);
                            tmp.setNumNodo(graxml.contador++);
                            tmp.setColumna(t1.beginColumn);
                            tmp.setFila(t1.beginLine);
                            tmp.setValor("");
                            hijo.addHijo(tmp);
      hijo = complemento(hijo);
{if ("" != null) return hijo;}
      break;
      }
    case Fki:{
      jj_consume_token(Fki);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                            tmp.setNumNodo(graxml.contador++);
                            tmp.setColumna(t1.beginColumn);
                            tmp.setFila(t1.beginLine);
                            tmp.setValor("");
                            hijo.addHijo(tmp);
      jj_consume_token(Fkf);
      hijo = complemento(hijo);
{if ("" != null) return hijo;}
      break;
      }
    default:
      jj_la1[4] = jj_gen;
{if ("" != null) return hijo;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Token tipocomplemento() throws ParseException {Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Pk:{
      t1 = jj_consume_token(Pk);
{if ("" != null) return t1;}
      break;
      }
    case Nulo:{
      t1 = jj_consume_token(Nulo);
{if ("" != null) return t1;}
      break;
      }
    case Nnulo:{
      t1 = jj_consume_token(Nnulo);
{if ("" != null) return t1;}
      break;
      }
    case Auto:{
      t1 = jj_consume_token(Auto);
{if ("" != null) return t1;}
      break;
      }
    case Unico:{
      t1 = jj_consume_token(Unico);
{if ("" != null) return t1;}
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//**************************************************************************************** ARCHIVO TABLA
  final public 
Nodo archivotabla(Nodo raiz) throws ParseException {
    jj_consume_token(Artablai);
    raiz = conttabla(raiz);
    jj_consume_token(Artablaf);
{if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

//<Rowi> contcolumna() <Rowf> conttabla()
  final public Nodo conttabla(Nodo raiz) throws ParseException {Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Rowi:{
      jj_consume_token(Rowi);
Nodo tmp = new Nodo("Row");
             tmp.setNumNodo(graxml.contador++);
             tmp.setValor("");
             nodo1 = tmp;
      nodo1 = contcolumna(nodo1);
      jj_consume_token(Rowf);
raiz.addHijo(nodo1);
      raiz = conttabla(raiz);
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[6] = jj_gen;
{if ("" != null) return raiz;}
    }
    throw new Error("Missing return statement in function");
  }

/*
<Ai> <Id> <Af> tipo() <Ai> <D> <Id> <Af> contcolumna()
| <Obji> <Id> contcolumna() <Objf> contcolumna()
| {return;}
*/
  final public 
Nodo contcolumna(Nodo hijo) throws ParseException {Nodo nodo1;
    Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Ai:{
      jj_consume_token(Ai);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                   tmp.setNumNodo(graxml.contador++);
                   tmp.setColumna(t1.beginColumn);
                   tmp.setFila(t1.beginLine);
                   nodo1 = tmp;
      jj_consume_token(Af);
      nodo1 = tipo(nodo1);
      jj_consume_token(Ai);
      jj_consume_token(D);
      jj_consume_token(Id);
      jj_consume_token(Af);
hijo.addHijo(nodo1);
      hijo = contcolumna(hijo);
{if ("" != null) return hijo;}
      break;
      }
    case Obji:{
      jj_consume_token(Obji);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo("obj");
                      tmp.setNumNodo(graxml.contador++);
                      tmp.setColumna(t1.beginColumn);
                      tmp.setFila(t1.beginLine);
                      tmp.setValor(t1.image);
                      nodo1 = tmp;
      nodo1 = contcolumna(nodo1);
      jj_consume_token(Objf);
hijo.addHijo(nodo1);
      hijo = contcolumna(hijo);
{if ("" != null) return hijo;}
      break;
      }
    default:
      jj_la1[7] = jj_gen;
{if ("" != null) return hijo;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo tipo(Nodo nodo1) throws ParseException {Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Id:{
      t1 = jj_consume_token(Id);
nodo1.setTipo("id");
               nodo1.setValor(t1.image);
               {if ("" != null) return nodo1;}
      break;
      }
    case Bool:{
      t1 = jj_consume_token(Bool);
nodo1.setTipo("bool");
               nodo1.setValor(t1.image);
               {if ("" != null) return nodo1;}
      break;
      }
    case Num:{
      t1 = jj_consume_token(Num);
nodo1.setTipo("num");
               nodo1.setValor(t1.image);
               {if ("" != null) return nodo1;}
      break;
      }
    case Texto:{
      t1 = jj_consume_token(Texto);
nodo1.setTipo("texto");
                 nodo1.setValor(t1.image);
                 {if ("" != null) return nodo1;}
      break;
      }
    case Date:{
      t1 = jj_consume_token(Date);
nodo1.setTipo("date");
                 nodo1.setValor(t1.image);
                 {if ("" != null) return nodo1;}
      break;
      }
    case Datetime:{
      t1 = jj_consume_token(Datetime);
nodo1.setTipo("datetime");
                     nodo1.setValor(t1.image);
                     {if ("" != null) return nodo1;}
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//***************************************************************************************ARCHIVO OBJETO
  final public 
Nodo archivoobj(Nodo raiz) throws ParseException {
    jj_consume_token(Arobji);
    raiz = obrows(raiz);
    jj_consume_token(Arobjf);
{if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

//<Obji> objcont() <Objf> obrows()
  final public Nodo obrows(Nodo raiz) throws ParseException {Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Obji:{
      jj_consume_token(Obji);
Nodo tmp = new Nodo("obj");
             tmp.setNumNodo(graxml.contador++);
             tmp.setValor("0");
             nodo1 = tmp;
      nodo1 = objcont(nodo1);
      jj_consume_token(Objf);
raiz.addHijo(nodo1);
      raiz = obrows(raiz);
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[9] = jj_gen;
{if ("" != null) return raiz;}
    }
    throw new Error("Missing return statement in function");
  }

//<Nombrei> <Id> <Nombref> <Attri> nodo1=tbcont(null) <Attrf> objcont()
  final public Nodo objcont(Nodo hijo) throws ParseException {Token t1;
    Nodo nodo1;
    jj_consume_token(Nombrei);
    t1 = jj_consume_token(Id);
hijo.setNombre(t1.image);
    jj_consume_token(Nombref);
    jj_consume_token(Attri);
    nodo1 = tbcont(hijo);
    jj_consume_token(Attrf);
{if ("" != null) return hijo;}
    throw new Error("Missing return statement in function");
  }

//*************************************************************************************** Archivo Usuarios
  final public 
Nodo arusu(Nodo raiz) throws ParseException {Nodo nodo1;
    jj_consume_token(Arusui);
Nodo tmp = new Nodo("Archivo");
                tmp.setNumNodo(graxml.contador++);
                raiz = tmp;
    raiz = usucon(raiz);
    jj_consume_token(Arusuf);
{if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo usucon(Nodo raiz) throws ParseException {Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Usui:{
      jj_consume_token(Usui);
Nodo tmp = new Nodo("Usuario");
                tmp.setNumNodo(graxml.contador++);
                nodo1 = tmp;
      nodo1 = datousu(nodo1);
      jj_consume_token(Usuf);
raiz.addHijo(nodo1);
      raiz = usucon(raiz);
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[10] = jj_gen;
{if ("" != null) return raiz;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo datousu(Nodo hijo) throws ParseException {Nodo nodo1;
    Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Nombrei:{
      jj_consume_token(Nombrei);
      t1 = jj_consume_token(Id);
      jj_consume_token(Nombref);
hijo.setNombre(t1.image);
      hijo = datousu(hijo);
{if ("" != null) return hijo;}
      break;
      }
    case Contai:{
      jj_consume_token(Contai);
      t1 = jj_consume_token(Id);
      jj_consume_token(Contaf);
hijo.setValor(t1.image);
      hijo = datousu(hijo);
{if ("" != null) return hijo;}
      break;
      }
    case Noperi:{
      jj_consume_token(Noperi);
Nodo tmp = new Nodo("Nopermiso");
                 tmp.setNumNodo(graxml.contador++);
                 tmp.setValor("0");
                 nodo1 = tmp;
      nodo1 = noper(nodo1);
      jj_consume_token(Noperf);
hijo.addHijo(nodo1);
      hijo = datousu(hijo);
{if ("" != null) return hijo;}
      break;
      }
    case Permisoi:{
      jj_consume_token(Permisoi);
Nodo tmp = new Nodo("Permiso");
                   tmp.setNumNodo(graxml.contador++);
                   tmp.setValor("0");
                   nodo1 = tmp;
      nodo1 = per(nodo1);
      jj_consume_token(Permisof);
hijo.addHijo(nodo1);
      hijo = datousu(hijo);
{if ("" != null) return hijo;}
      break;
      }
    default:
      jj_la1[11] = jj_gen;
{if ("" != null) return hijo;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo noper(Nodo hijo2) throws ParseException {Token t1;
    Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Dbi:{
      jj_consume_token(Dbi);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                    tmp.setNumNodo(graxml.contador++);
                    tmp.setValor("0");
                    nodo1 = tmp;
      jj_consume_token(Dbf);
      nodo1 = connoper(nodo1);
hijo2.addHijo(nodo1);
                                    {if ("" != null) return hijo2;}
      break;
      }
    default:
      jj_la1[12] = jj_gen;
{if ("" != null) return hijo2;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo connoper(Nodo hijo3) throws ParseException {Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Obusui:{
      jj_consume_token(Obusui);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                       tmp.setNumNodo(graxml.contador++);
                       tmp.setValor("0");
                       hijo3.addHijo(tmp);
      jj_consume_token(Obusuf);
      hijo3 = connoper(hijo3);
{if ("" != null) return hijo3;}
      break;
      }
    default:
      jj_la1[13] = jj_gen;
{if ("" != null) return hijo3;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo per(Nodo hijo2) throws ParseException {Token t1;
    Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Dbi:{
      jj_consume_token(Dbi);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                     tmp.setNumNodo(graxml.contador++);
                     tmp.setValor("0");
                     nodo1 = tmp;
      jj_consume_token(Dbf);
      nodo1 = conper(nodo1);
hijo2.addHijo(nodo1);
                                {if ("" != null) return hijo2;}
      break;
      }
    default:
      jj_la1[14] = jj_gen;
{if ("" != null) return hijo2;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo conper(Nodo hijo3) throws ParseException {Token t1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Obusui:{
      jj_consume_token(Obusui);
      t1 = jj_consume_token(Id);
Nodo tmp = new Nodo(t1.image);
                       tmp.setNumNodo(graxml.contador++);
                       tmp.setValor("0");
                       hijo3.addHijo(tmp);
      jj_consume_token(Obusuf);
      hijo3 = per(hijo3);
{if ("" != null) return hijo3;}
      break;
      }
    case Todos:{
      jj_consume_token(Todos);
Nodo tmp = new Nodo("Todos");
                 tmp.setNumNodo(graxml.contador++);
                 tmp.setValor("0");
                 hijo3.addHijo(tmp);
                 {if ("" != null) return hijo3;}
      break;
      }
    default:
      jj_la1[15] = jj_gen;
{if ("" != null) return hijo3;}
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public graxmlTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x4100,0x40,0x1500000,0x54000000,0x0,0x0,0x40000,0x0,0x0,0x0,0x0,0x400,0x40,0x0,0x40,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x150000,0x0,0x0,0x115,0x0,0x0,0x0,0x100,0x0,0x100,0x400000,0x15000000,0x0,0x40000000,0x0,0x40000000,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0xf6,0xf2,0x0,0x4000,0x23d00,0x0,0x0,0x0,0x0,0x0,0x0,0x1,};
   }

  /** Constructor with InputStream. */
  public graxml(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public graxml(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new graxmlTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public graxml(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new graxmlTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public graxml(graxmlTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(graxmlTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[82];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 16; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 82; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
