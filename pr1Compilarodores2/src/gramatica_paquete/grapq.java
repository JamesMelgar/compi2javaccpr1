/* grapq.java */
/* Generated By:JavaCC: Do not edit this line. grapq.java */
package gramatica_paquete;
import pr1compilarodores2.Nodo;
import java.io.*;
import java.util.*;

public class grapq implements grapqConstants {
    public static int contador;
    public static void main( String[] args )throws ParseException
    {
            System.out.println("analisis correcto");
    }

//**********************************************************************************************Inicio

//<Coi> <Validar> <Pp> <Num> <Cm> paquete() <Cof>
  final public 
Nodo inicio(Nodo raiz) throws ParseException {Token t1;
    jj_consume_token(Coi);
Nodo tmp = new Nodo("paquete");
           tmp.setNumNodo(grapq.contador++);
           raiz = tmp;
    jj_consume_token(Validar);
    jj_consume_token(Pp);
    t1 = jj_consume_token(Num);
raiz.setValor(t1.image);
    jj_consume_token(Cm);
    raiz = paquete(raiz);
    jj_consume_token(Cof);
{if ("" != null) return raiz;}
    throw new Error("Missing return statement in function");
  }

//<Login> <Pp> <Coi> <Comando> <Flecha> login() <Cof> 
//<Paquete> <Pp> tipopaquete() { return raiz; }
  final public 
Nodo paquete(Nodo raiz) throws ParseException {Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Login:{
      jj_consume_token(Login);
      jj_consume_token(Pp);
      jj_consume_token(Coi);
      jj_consume_token(Comando);
Nodo tmp = new Nodo("login");
             tmp.setNumNodo(grapq.contador++);
             nodo1 = tmp;
      jj_consume_token(Flecha);
      nodo1 = login(nodo1);
      jj_consume_token(Cof);
raiz.addHijo(nodo1);
               {if ("" != null) return raiz;}
      break;
      }
    case Paquete:{
      jj_consume_token(Paquete);
      jj_consume_token(Pp);
      raiz = tipopaquete(raiz);
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

  final public Nodo login(Nodo hijo) throws ParseException {Token t1;
    Nodo nodo1;
    jj_consume_token(Comilla);
    jj_consume_token(Usuario);
Nodo tmp = new Nodo("usuario");
                              tmp.setNumNodo(grapq.contador++);
                              nodo1= tmp;
    jj_consume_token(Comi);
    t1 = jj_consume_token(Id);
nodo1.setNombre(t1.image);
    jj_consume_token(Comi);
    jj_consume_token(And);
    jj_consume_token(Pass);
    jj_consume_token(Flecha);
    jj_consume_token(Comi);
    t1 = jj_consume_token(Id);
nodo1.setValor(t1.image);
    jj_consume_token(Comi);
hijo.addHijo(nodo1);
    jj_consume_token(Comilla);
{if ("" != null) return hijo;}
    throw new Error("Missing return statement in function");
  }

//<Error> <Cm> error()
//<Usql> <Cm> usql() 
//<Reporte> <Cm> reporte()
  final public 
Nodo tipopaquete(Nodo raiz) throws ParseException {Nodo nodo1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Fin:{
      jj_consume_token(Fin);
Nodo tmp = new Nodo("fin");
              tmp.setNumNodo(grapq.contador++);
              nodo1 = tmp;
              raiz.addHijo(nodo1);
              {if ("" != null) return raiz;}
      break;
      }
    case Error:{
      jj_consume_token(Error);
      jj_consume_token(Cm);
      error();
{if ("" != null) return raiz;}
      break;
      }
    case Usql:{
      jj_consume_token(Usql);
Nodo tmp = new Nodo("usql");
               tmp.setNumNodo(grapq.contador++);
               nodo1 = tmp;
      jj_consume_token(Cm);
      nodo1 = usql(nodo1);
raiz.addHijo(nodo1);
                                      {if ("" != null) return raiz;}
      break;
      }
    case Reporte:{
      jj_consume_token(Reporte);
      jj_consume_token(Cm);
      reporte();
{if ("" != null) return raiz;}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public void error() throws ParseException {
    jj_consume_token(Tipo);
    jj_consume_token(Pp);
    tipoerror();
  }

  final public void tipoerror() throws ParseException {
    jj_consume_token(Lenguaje);
    jj_consume_token(Cm);
    jj_consume_token(Msg);
    jj_consume_token(Pp);
    jj_consume_token(Texto);
    jj_consume_token(Cm);
    jj_consume_token(Datos);
    jj_consume_token(Pp);
    jj_consume_token(Coi);
    datos();
    jj_consume_token(Cof);
  }

  final public void datos() throws ParseException {
    jj_consume_token(Error);
    jj_consume_token(Pp);
    gramatica();
    jj_consume_token(Cm);
    jj_consume_token(Archivo);
    jj_consume_token(Pp);
    jj_consume_token(Sql);
    jj_consume_token(Cm);
    jj_consume_token(Col);
    jj_consume_token(Pp);
    jj_consume_token(Num);
    jj_consume_token(Cm);
    jj_consume_token(Fila);
    jj_consume_token(Pp);
    jj_consume_token(Num);
  }

  final public void gramatica() throws ParseException {
    jj_consume_token(Lexico);
  }

  final public Nodo usql(Nodo hijo) throws ParseException {Token t1;
    Nodo nodo1;
    jj_consume_token(Instruccion);
    jj_consume_token(Pp);
    t1 = jj_consume_token(Sql);
nodo1 = hijo;
                      hijo = pr1compilarodores2.CrearArbol.Analisiusuql(nodo1, t1.image);
    jj_consume_token(Cm);
{if ("" != null) return hijo;}
    throw new Error("Missing return statement in function");
  }

  final public void reporte() throws ParseException {
    jj_consume_token(Instruccion);
    jj_consume_token(Pp);
    jj_consume_token(Sql);
    jj_consume_token(Cm);
  }

  /** Generated Token Manager. */
  public grapqTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x300,0x300c00,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public grapq(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public grapq(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new grapqTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public grapq(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new grapqTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public grapq(grapqTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(grapqTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[47];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 47; i++) {
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
