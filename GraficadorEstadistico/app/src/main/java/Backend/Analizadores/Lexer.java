// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: Lexer.jflex

//Configuración
package Backend.Analizadores;
import java_cup.runtime.*;
import Backend.Objetos.Auxiliares.Simbolo;
import static Backend.Analizadores.ParserSym.*;
import Backend.Manejadores.ManejadorReportes;
import Backend.Manejadores.ManejadorErroresExtra;
import Backend.Objetos.Reportes.ReporteError;//yo supongo que si se debe importar para usar el eqq de ctes static, aunque sea kotlin... solo era para probar que si jala cosas de kotlin en Java xD


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;
  public static final int ERROR = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2, 2
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\u10ff\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\0\1\1\1\3\22\0\1\1"+
    "\1\0\1\4\1\5\4\0\4\6\1\7\1\6\1\10"+
    "\1\6\12\11\2\7\6\0\1\12\1\13\1\14\1\15"+
    "\12\0\1\16\12\0\1\7\1\17\1\7\3\0\1\20"+
    "\1\0\1\21\1\22\1\23\1\24\2\0\1\25\1\26"+
    "\1\0\1\27\1\0\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\0\1\41\1\42\1\0"+
    "\1\7\1\0\1\7\u0182\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[512];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\2\2\1\3\1\2\1\4\1\5\1\6"+
    "\11\1\1\7\1\10\1\11\2\12\16\0\1\13\1\14"+
    "\1\15\1\16\1\6\2\0\1\17\1\0\1\20\15\0"+
    "\1\21\1\22\2\0\1\23\2\0\1\24\6\0\1\25"+
    "\1\0\1\26\1\0\1\27\4\0\1\30\5\0\1\31"+
    "\1\32\1\33\3\0\1\34\1\35";

  private static int [] zzUnpackAction() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\43\0\106\0\151\0\151\0\214\0\151\0\257"+
    "\0\151\0\151\0\322\0\365\0\u0118\0\u013b\0\u015e\0\u0181"+
    "\0\u01a4\0\u01c7\0\u01ea\0\u020d\0\u0230\0\151\0\u0253\0\151"+
    "\0\u0276\0\u0299\0\u02bc\0\u02df\0\u0302\0\u0325\0\u0348\0\u036b"+
    "\0\u038e\0\u03b1\0\u03d4\0\u03f7\0\u041a\0\u043d\0\u0460\0\151"+
    "\0\151\0\151\0\151\0\u0299\0\u0483\0\u04a6\0\151\0\u04c9"+
    "\0\151\0\u04ec\0\u050f\0\u0532\0\u0555\0\u0578\0\u059b\0\u05be"+
    "\0\u05e1\0\u0604\0\u0627\0\u064a\0\u066d\0\u0690\0\151\0\151"+
    "\0\u06b3\0\u06d6\0\151\0\u06f9\0\u071c\0\151\0\u073f\0\u0762"+
    "\0\u0785\0\u07a8\0\u07cb\0\u07ee\0\151\0\u0811\0\151\0\u0834"+
    "\0\151\0\u0857\0\u087a\0\u089d\0\u08c0\0\151\0\u08e3\0\u0906"+
    "\0\u0929\0\u094c\0\u096f\0\151\0\151\0\151\0\u0992\0\u09b5"+
    "\0\u09d8\0\151\0\151";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\2\5\1\6\1\7\1\10\1\11\1\12\1\4"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\3\4\1\16"+
    "\1\21\12\4\1\22\1\23\1\24\2\4\2\25\2\4"+
    "\1\26\12\25\1\27\23\25\1\4\2\30\1\31\3\4"+
    "\1\12\33\4\45\0\1\5\40\0\2\10\1\5\1\6"+
    "\37\10\10\0\1\32\1\13\51\0\1\33\42\0\1\34"+
    "\45\0\1\35\45\0\1\36\41\0\1\37\3\0\1\40"+
    "\37\0\1\41\7\0\1\42\2\0\1\43\26\0\1\44"+
    "\3\0\1\45\41\0\1\46\32\0\1\47\22\0\2\25"+
    "\3\0\12\25\1\0\23\25\4\0\1\50\23\0\1\51"+
    "\3\0\1\52\1\0\1\53\6\0\1\30\51\0\1\54"+
    "\65\0\1\55\36\0\1\56\36\0\1\57\41\0\1\60"+
    "\42\0\1\61\53\0\1\62\31\0\1\63\44\0\1\64"+
    "\53\0\1\65\36\0\1\66\3\0\1\67\42\0\1\70"+
    "\31\0\1\71\44\0\1\72\47\0\1\73\44\0\1\74"+
    "\25\0\1\75\42\0\1\76\62\0\1\77\1\100\33\0"+
    "\1\101\43\0\1\102\37\0\1\103\50\0\1\104\23\0"+
    "\1\105\56\0\1\106\37\0\1\107\31\0\1\110\47\0"+
    "\1\111\54\0\1\112\26\0\1\113\56\0\1\114\23\0"+
    "\1\115\51\0\1\116\42\0\1\117\47\0\1\120\43\0"+
    "\1\121\27\0\1\122\56\0\1\123\34\0\1\124\35\0"+
    "\1\125\50\0\1\126\34\0\1\127\37\0\1\130\42\0"+
    "\1\131\60\0\1\132\42\0\1\133\41\0\1\134\27\0"+
    "\1\135\54\0\1\136\26\0\1\137\42\0\1\140\50\0"+
    "\1\141\51\0\1\142\30\0\1\143\17\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2555];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\2\11\1\1\1\11\1\1\2\11\13\1\1\11"+
    "\1\1\1\11\1\1\16\0\4\11\1\1\2\0\1\11"+
    "\1\0\1\11\15\0\2\11\2\0\1\11\2\0\1\11"+
    "\6\0\1\11\1\0\1\11\1\0\1\11\4\0\1\11"+
    "\5\0\3\11\3\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    boolean requeriaCompania = false;
    Simbolo simboloAnterior = null;

    StringBuffer string = new StringBuffer();
    private ManejadorReportes manejadorReportes = new ManejadorReportes();
    private ManejadorErroresExtra manejadorErroresExtra = new ManejadorErroresExtra(manejadorReportes);

    private Symbol symbol(int tipo, Object valor, boolean conCompania){
        Simbolo simboloActual = new Simbolo (tipo, yyline+1, yycolumn+1, valor, (conCompania)?simboloAnterior:null);        

        if(simboloAnterior != null && requeriaCompania){
            simboloAnterior.setSiguiente(simboloActual);
        }

        simboloAnterior = simboloActual;
        requeriaCompania = conCompania;
        return new Symbol(tipo, yyline+1, yycolumn+1, simboloActual);//al no enviársele valor para left y right supongo [me parece razonable] que en el parser no se pueda usar ...left y ..right, puesto que no hay datos y aunque en todo caso se pudiera, no daría la fila y columna, sino quizá 0, 1 o error...
    }

    private Symbol acccionReservada(int tipo){//por el momneot es void xD 
        System.out.println("[L] reservada ->"+ yytext());       
        return symbol(tipo, yytext(), false);    
    }    

    private Symbol accionSimbolosAceptados(){        
        if(yystate() == ERROR){
            accionParadaParaError();
        }

        System.out.println("[L] símbolo ->"+ yytext() +" T: " +((yytext().equals(":"))?DOS_PUNTOS:((yytext().equals(","))?COMA:((yytext().equals("{"))?LLAVE_A:((yytext().equals("}"))?LLAVE_C:((yytext().equals(";"))?PUNTO_COMA:((yytext().equals("["))?CORCHETE_A:CORCHETE_C)))))));
        return symbol(((yytext().equals(":"))?DOS_PUNTOS:((yytext().equals(","))?COMA:((yytext().equals("{"))?LLAVE_A:((yytext().equals("}"))?LLAVE_C:((yytext().equals(";"))?PUNTO_COMA:((yytext().equals("["))?CORCHETE_A:CORCHETE_C)))))), yytext(), false);                 
    }//por si acaso miras que si te es posible add SA a ERROR sin generar problemas al formar los tokens aquí y analizar las RP en el parser

    private int establecerMenosOResta(){
        System.out.println("[L] simbolo ->"+ yytext());
        return (simboloAnterior != null && simboloAnterior.getSym() != NUMERO)?MENOS:RESTA;//porque si aparece un número antes, entonces será 
    }

    private void accionProcesarError(){
        if(yystate() != ERROR){//no coloco tb a CADENA, porque se supone que no debería hacer match con el [^] cuando esté dentro de ese estado léxico...
            string.setLength(0);//no provoca problemas el usar la variable string que tb usa STRING para concatenar, puesto que al estar en ese estado no se entrará aquí puesto que esta expre reg tiene la menor precedencia y las reglas de allá impiden que este caso suceda... puesto que se absorben todos los caracteres posibles hasta llegar a la otra "
            yypushback(yylength());//iba a colocar 1, en lugar de yylength pero no se si la unidad de medida varíe porque podría se que cada caracter tenga un tamaño diferente dependiendo de la cdad de bits que requiera para ser plasmado p.ej
            yybegin(ERROR);
        }else if(yystate() == ERROR){
            string.append(yytext());
        }
    }

    private void accionParadaParaError(){//aquí es donde se imprime todo lo concatenado que se clasificó como error...
    System.out.println("[L] error -> " + ReporteError.LEXER_INVALID_WORD +"\n");//si funcionó el llamado a la cte static de kotlin xD uwu
        //si la línea y columna que aprecen son irrazonables para los errores, ahí te acuerdas que esos valores los seteaste aquí...         
        manejadorErroresExtra.detectarReservadadMalFormada(new Simbolo(error, yyline+1, yycolumn+1, string.toString(), null));//si es que la lista de símbolos no me ayuda por completo [puesto que solo da los siguientes...], entonces lo cb a true xD        
        yybegin(YYINITIAL);
    }

    public ManejadorErroresExtra getManejadorErroresExtra(){
        return manejadorErroresExtra;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(ParserSym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { accionProcesarError();
            }
            // fall through
          case 30: break;
          case 2:
            { /*se ignora*/
            }
            // fall through
          case 31: break;
          case 3:
            { string.setLength(0); yybegin(STRING);
            }
            // fall through
          case 32: break;
          case 4:
            { System.out.println("[L] simbolo-> "+ yytext());return symbol(((yytext().equals("+"))?SUMA:((yytext().equals("-"))?establecerMenosOResta():((yytext().equals("*"))?MULTI:((yytext().equals("/"))?DIV:((yytext().equals("("))?PARENTESIS_A:PARENTESIS_C))))), yytext(), true);
            }
            // fall through
          case 33: break;
          case 5:
            { return accionSimbolosAceptados();
            }
            // fall through
          case 34: break;
          case 6:
            { System.out.println("[L] numero ->"+ yytext());return symbol(NUMERO, new Double(yytext()), false);
            }
            // fall through
          case 35: break;
          case 7:
            { string.append( yytext());
            }
            // fall through
          case 36: break;
          case 8:
            { yybegin(YYINITIAL);System.out.println("cadena ->"+ string.toString() + " T: "+CADENA);return symbol(CADENA, new String(string) /*string.toString()*/, false);
            }
            // fall through
          case 37: break;
          case 9:
            { string.append('\\');
            }
            // fall through
          case 38: break;
          case 10:
            { accionParadaParaError();
            }
            // fall through
          case 39: break;
          case 11:
            { string.append('\"');
            }
            // fall through
          case 40: break;
          case 12:
            { string.append('\n');
            }
            // fall through
          case 41: break;
          case 13:
            { string.append('\r');
            }
            // fall through
          case 42: break;
          case 14:
            { string.append('\t');
            }
            // fall through
          case 43: break;
          case 15:
            { return acccionReservada(DEF);
            }
            // fall through
          case 44: break;
          case 16:
            { return acccionReservada(PIE);
            }
            // fall through
          case 45: break;
          case 17:
            { return acccionReservada(EJEX);
            }
            // fall through
          case 46: break;
          case 18:
            { return acccionReservada(EJEY);
            }
            // fall through
          case 47: break;
          case 19:
            { return acccionReservada(TIPO);
            }
            // fall through
          case 48: break;
          case 20:
            { return acccionReservada(UNIR);
            }
            // fall through
          case 49: break;
          case 21:
            { return acccionReservada(EXTRA);
            }
            // fall through
          case 50: break;
          case 22:
            { return acccionReservada(TOTAL);
            }
            // fall through
          case 51: break;
          case 23:
            { return acccionReservada(BARRAS);
            }
            // fall through
          case 52: break;
          case 24:
            { return acccionReservada(TITULO);
            }
            // fall through
          case 53: break;
          case 25:
            { return acccionReservada(VALORES);
            }
            // fall through
          case 54: break;
          case 26:
            { return acccionReservada(CANTIDAD);
            }
            // fall through
          case 55: break;
          case 27:
            { return acccionReservada(EJECUTAR);
            }
            // fall through
          case 56: break;
          case 28:
            { return acccionReservada(ETIQUETAS);
            }
            // fall through
          case 57: break;
          case 29:
            { return acccionReservada(PORCENTAJE);
            }
            // fall through
          case 58: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
