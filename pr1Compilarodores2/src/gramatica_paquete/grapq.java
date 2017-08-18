/* grapq.java */
/* Generated By:JavaCC: Do not edit this line. grapq.java */
package gramatica_paquete;
import java.io.*;
import java.util.*;

public class grapq implements grapqConstants {
    public static void main( String[] args )throws ParseException
        {
                try
                {
                        grapq analizador = new grapq( System.in ) ;
                        analizador.inicio();
                        System.out.println("analisis correcto");
                }
                catch(ParseException e)
                {
                        System.out.println(e.getMessage());
                        System.out.println("Analizador: Se han encontrado errores en el analisis.");
                }
        }

  final public void inicio() throws ParseException {
    jj_consume_token(Coi);
    jj_consume_token(Validar);
    jj_consume_token(Pp);
    jj_consume_token(Num);
    jj_consume_token(Cm);
    paquete();
    jj_consume_token(Cof);
  }

  final public void paquete() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Login:{
      jj_consume_token(Login);
      jj_consume_token(Pp);
      jj_consume_token(Coi);
      jj_consume_token(Comando);
      jj_consume_token(Flecha);
      login();
      jj_consume_token(Cof);
      break;
      }
    case Paquete:{
      jj_consume_token(Paquete);
      jj_consume_token(Pp);
      tipopaquete();
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void login() throws ParseException {
    jj_consume_token(Comilla);
    jj_consume_token(Usuario);
    jj_consume_token(Comi);
    jj_consume_token(Id);
    jj_consume_token(Comi);
    jj_consume_token(And);
    jj_consume_token(Pass);
    jj_consume_token(Flecha);
    jj_consume_token(Comi);
    jj_consume_token(Id);
    jj_consume_token(Comi);
    jj_consume_token(Comilla);
  }

  final public void tipopaquete() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Fin:{
      jj_consume_token(Fin);
      break;
      }
    case Error:{
      jj_consume_token(Error);
      jj_consume_token(Cm);
      error();
      break;
      }
    case Usql:{
      jj_consume_token(Usql);
      jj_consume_token(Cm);
      usql();
      break;
      }
    case Reporte:{
      jj_consume_token(Reporte);
      jj_consume_token(Cm);
      reporte();
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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

  final public void usql() throws ParseException {
    jj_consume_token(Instruccion);
    jj_consume_token(Pp);
    jj_consume_token(Sql);
    jj_consume_token(Cm);
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