
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Backend.Analizadores;

import java_cup.runtime.*;
import Backend.Manejadores.ManejadorGraficacion;
import Backend.Manejadores.ManejadorReportes;
import Backend.Analizadores.AnalizadorSemantico;
import Backend.Manejadores.ManejadorErroresExtra;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return ParserSym.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\051\000\002\002\004\000\002\002\004\000\002\003" +
    "\004\000\002\003\003\000\002\004\004\000\002\005\006" +
    "\000\002\005\006\000\002\006\006\000\002\007\004\000" +
    "\002\010\005\000\002\010\007\000\002\011\011\000\002" +
    "\011\007\000\002\012\003\000\002\012\007\000\002\012" +
    "\007\000\002\013\010\000\002\013\011\000\002\014\004" +
    "\000\002\015\003\000\002\015\007\000\002\015\007\000" +
    "\002\015\005\000\002\015\005\000\002\015\005\000\002" +
    "\016\003\000\002\016\003\000\002\017\005\000\002\017" +
    "\003\000\002\020\005\000\002\020\003\000\002\021\005" +
    "\000\002\021\005\000\002\021\005\000\002\021\005\000" +
    "\002\021\003\000\002\021\004\000\002\021\005\000\002" +
    "\022\004\000\002\022\003\000\002\023\007" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\157\000\004\024\005\001\002\000\006\024\ufffe\042" +
    "\ufffe\001\002\000\006\025\023\026\024\001\002\000\006" +
    "\024\005\042\012\001\002\000\004\002\010\001\002\000" +
    "\004\002\001\001\002\000\006\024\uffff\042\uffff\001\002" +
    "\000\004\020\016\001\002\000\006\002\000\042\012\001" +
    "\002\000\006\002\uffda\042\uffda\001\002\000\006\002\uffdb" +
    "\042\uffdb\001\002\000\004\005\017\001\002\000\004\021" +
    "\020\001\002\000\004\013\021\001\002\000\006\002\uffd9" +
    "\042\uffd9\001\002\000\006\024\ufffd\042\ufffd\001\002\000" +
    "\004\014\136\001\002\000\004\014\025\001\002\000\020" +
    "\027\027\032\037\033\034\034\036\035\035\036\033\041" +
    "\040\001\002\000\020\027\027\032\037\033\034\034\036" +
    "\035\035\036\033\041\040\001\002\000\004\016\126\001" +
    "\002\000\004\013\uffee\001\002\000\004\015\125\001\002" +
    "\000\004\013\124\001\002\000\004\016\122\001\002\000" +
    "\004\016\113\001\002\000\004\016\107\001\002\000\004" +
    "\016\052\001\002\000\004\016\043\001\002\000\004\016" +
    "\041\001\002\000\004\005\042\001\002\000\004\013\uffeb" +
    "\001\002\000\004\022\044\001\002\000\004\005\045\001" +
    "\002\000\006\017\uffe5\023\uffe5\001\002\000\006\017\047" +
    "\023\050\001\002\000\004\005\051\001\002\000\004\013" +
    "\uffed\001\002\000\006\017\uffe6\023\uffe6\001\002\000\004" +
    "\022\053\001\002\000\004\014\055\001\002\000\006\017" +
    "\100\023\101\001\002\000\010\004\057\007\056\020\061" +
    "\001\002\000\010\004\057\007\056\020\061\001\002\000" +
    "\024\006\uffde\007\uffde\010\uffde\011\uffde\013\uffde\015\uffde" +
    "\017\uffde\021\uffde\023\uffde\001\002\000\014\006\065\007" +
    "\063\010\067\011\066\017\074\001\002\000\010\004\057" +
    "\007\056\020\061\001\002\000\014\006\065\007\063\010" +
    "\067\011\066\021\064\001\002\000\010\004\057\007\056" +
    "\020\061\001\002\000\024\006\uffdc\007\uffdc\010\uffdc\011" +
    "\uffdc\013\uffdc\015\uffdc\017\uffdc\021\uffdc\023\uffdc\001\002" +
    "\000\010\004\057\007\056\020\061\001\002\000\010\004" +
    "\057\007\056\020\061\001\002\000\010\004\057\007\056" +
    "\020\061\001\002\000\024\006\uffe0\007\uffe0\010\uffe0\011" +
    "\uffe0\013\uffe0\015\uffe0\017\uffe0\021\uffe0\023\uffe0\001\002" +
    "\000\024\006\uffdf\007\uffdf\010\uffdf\011\uffdf\013\uffdf\015" +
    "\uffdf\017\uffdf\021\uffdf\023\uffdf\001\002\000\024\006\uffe2" +
    "\007\uffe2\010\067\011\066\013\uffe2\015\uffe2\017\uffe2\021" +
    "\uffe2\023\uffe2\001\002\000\024\006\uffe1\007\uffe1\010\067" +
    "\011\066\013\uffe1\015\uffe1\017\uffe1\021\uffe1\023\uffe1\001" +
    "\002\000\010\004\057\007\056\020\061\001\002\000\014" +
    "\006\065\007\063\010\067\011\066\015\076\001\002\000" +
    "\006\017\ufff5\023\ufff5\001\002\000\024\006\uffdd\007\uffdd" +
    "\010\uffdd\011\uffdd\013\uffdd\015\uffdd\017\uffdd\021\uffdd\023" +
    "\uffdd\001\002\000\004\014\102\001\002\000\004\013\ufff7" +
    "\001\002\000\010\004\057\007\056\020\061\001\002\000" +
    "\014\006\065\007\063\010\067\011\066\017\104\001\002" +
    "\000\010\004\057\007\056\020\061\001\002\000\014\006" +
    "\065\007\063\010\067\011\066\015\106\001\002\000\006" +
    "\017\ufff6\023\ufff6\001\002\000\006\037\111\040\112\001" +
    "\002\000\004\013\uffea\001\002\000\004\013\uffe8\001\002" +
    "\000\004\013\uffe7\001\002\000\004\022\114\001\002\000" +
    "\010\004\057\007\056\020\061\001\002\000\006\017\117" +
    "\023\120\001\002\000\016\006\065\007\063\010\067\011" +
    "\066\017\uffe3\023\uffe3\001\002\000\010\004\057\007\056" +
    "\020\061\001\002\000\004\013\uffec\001\002\000\016\006" +
    "\065\007\063\010\067\011\066\017\uffe4\023\uffe4\001\002" +
    "\000\010\004\057\007\056\020\061\001\002\000\014\006" +
    "\065\007\063\010\067\011\066\013\uffe9\001\002\000\022" +
    "\015\uffef\027\uffef\032\uffef\033\uffef\034\uffef\035\uffef\036" +
    "\uffef\041\uffef\001\002\000\006\024\ufffb\042\ufffb\001\002" +
    "\000\004\005\127\001\002\000\004\013\ufff8\001\002\000" +
    "\020\027\027\032\037\033\034\034\036\035\035\036\033" +
    "\041\040\001\002\000\020\027\027\032\037\033\034\034" +
    "\036\035\035\036\033\041\040\001\002\000\020\027\027" +
    "\032\037\033\034\034\036\035\035\036\033\041\040\001" +
    "\002\000\020\027\027\032\037\033\034\034\036\035\035" +
    "\036\033\041\040\001\002\000\022\015\ufff1\027\027\032" +
    "\037\033\034\034\036\035\035\036\033\041\040\001\002" +
    "\000\004\015\ufff0\001\002\000\012\027\027\030\144\031" +
    "\143\034\036\001\002\000\004\013\ufff4\001\002\000\004" +
    "\015\161\001\002\000\004\013\160\001\002\000\012\027" +
    "\027\030\144\031\143\034\036\001\002\000\004\016\151" +
    "\001\002\000\004\016\145\001\002\000\004\022\146\001" +
    "\002\000\004\005\045\001\002\000\006\017\047\023\150" +
    "\001\002\000\004\013\ufff3\001\002\000\004\022\152\001" +
    "\002\000\010\004\057\007\056\020\061\001\002\000\006" +
    "\017\117\023\154\001\002\000\004\013\ufff2\001\002\000" +
    "\012\027\027\030\144\031\143\034\036\001\002\000\012" +
    "\027\027\030\144\031\143\034\036\001\002\000\004\015" +
    "\ufffa\001\002\000\014\015\ufff9\027\ufff9\030\ufff9\031\ufff9" +
    "\034\ufff9\001\002\000\006\024\ufffc\042\ufffc\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\157\000\010\002\006\003\005\004\003\001\001\000" +
    "\002\001\001\000\004\005\021\001\001\000\010\004\010" +
    "\022\012\023\013\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\023\014" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\012\010\027\013\030\014\025\015\031\001\001\000\010" +
    "\010\027\014\127\015\031\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\017\045\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\011\053\001\001\000\002\001\001\000\004\021\057" +
    "\001\001\000\004\021\076\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\021\061\001\001\000\002\001\001" +
    "\000\004\021\072\001\001\000\002\001\001\000\004\021" +
    "\071\001\001\000\004\021\070\001\001\000\004\021\067" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\021\074\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\021\102\001\001\000\002" +
    "\001\001\000\004\021\104\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\016\107\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\020\114\021\115\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\021\120\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\021\122\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\010\010\027\014\130\015\031\001\001" +
    "\000\010\010\027\014\131\015\031\001\001\000\010\010" +
    "\027\014\132\015\031\001\001\000\010\010\027\014\133" +
    "\015\031\001\001\000\010\010\027\014\134\015\031\001" +
    "\001\000\002\001\001\000\012\006\137\007\141\010\136" +
    "\012\140\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\010\007\154\010\136\012\140\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\017\146\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\020\152\021\115\001\001\000" +
    "\002\001\001\000\002\001\001\000\010\007\155\010\136" +
    "\012\140\001\001\000\010\007\156\010\136\012\140\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


//código que modifica el comportamiento del parser [sobreescribiendo o creado métodos]
    

    public Parser(Lexer lexer){//nuevo constructor
        super(lexer);        
    }

    
    protected int error_sync_size(){
        return 1;
    }//par que establezca que con un token bien leido basta para recuperarse...


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {

//no se si al colocar aquí el método para inicializar y obtener los analizadores, podré acceder a ellos desde el proyecto... colócalo xD y si no, ya sabbes que en el parse code si lo permite xD
    ManejadorGraficacion manejadorGraficacion;
    ManejadorReportes manejadorReportes;
    AnalizadorSemantico analizadorSemantico;//En caso de que no permita, quizá si lo haga si colocas a estos manejadores en el parser code... como es la clase global xD, bueno auqnue de ser así entonces el getter tb tendría que estar allá xD
    
    public void inicializarManejadores(ManejadorErroresExtra manejadorErroresExtra, ManejadorReportes manejadorReportes){//Estos vienen del Lexer, por lo tanto podrías solo crear un getter si es que guardas estos en una var del proy previo a enviarla aquí o si no crea un getter para esas 2 aquí tb xD
        manejadorGraficacion = new ManejadorGraficacion(manejadorErroresExtra);
        this.manejadorReportes = manejadorReportes;
        analizadorSemantico = manejadorErroresExtra.getAnalizadorSemantico();
    }

    //en realidad no debe haber un getter para el analizadorSem porque lo puedes obtener a través de la clase ErroresExtra y a esa puedes acceder desde el proyecto xD


  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= inicio EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // inicio ::= primerseccion segundaSeccion 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("inicio",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // primerseccion ::= primerseccion definicion 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("primerseccion",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // primerseccion ::= definicion 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("primerseccion",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // definicion ::= DEF grafico 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("definicion",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // grafico ::= BARRAS LLAVE_A cuerpoBarra LLAVE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("grafico",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // grafico ::= PIE LLAVE_A cuerpoPie LLAVE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("grafico",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // cuerpoBarra ::= paramBarra paramBarra paramBarra paramBarra 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cuerpoBarra",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // paramBarra ::= atribBarra PUNTO_COMA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("paramBarra",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // atribGeneral ::= TITULO DOS_PUNTOS CADENA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribGeneral",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // atribGeneral ::= UNIR DOS_PUNTOS CORCHETE_A tupla CORCHETE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribGeneral",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // tupla ::= tupla COMA LLAVE_A valNumerico COMA valNumerico LLAVE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("tupla",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // tupla ::= LLAVE_A valNumerico COMA valNumerico LLAVE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("tupla",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // atribBarra ::= atribGeneral 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribBarra",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // atribBarra ::= EJEX DOS_PUNTOS CORCHETE_A listaCadenas CORCHETE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribBarra",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // atribBarra ::= EJEY DOS_PUNTOS CORCHETE_A listaNumeros CORCHETE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribBarra",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // cuerpoPie ::= paramPie paramPie paramPie paramPie paramPie paramPie 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cuerpoPie",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // cuerpoPie ::= paramPie paramPie paramPie paramPie paramPie paramPie paramPie 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cuerpoPie",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // paramPie ::= atribPie PUNTO_COMA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("paramPie",10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // atribPie ::= atribGeneral 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribPie",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // atribPie ::= ETIQUETAS DOS_PUNTOS CORCHETE_A listaCadenas CORCHETE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribPie",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // atribPie ::= VALORES DOS_PUNTOS CORCHETE_A listaNumeros CORCHETE_C 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribPie",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // atribPie ::= EXTRA DOS_PUNTOS CADENA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribPie",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // atribPie ::= TIPO DOS_PUNTOS elTipo 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribPie",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // atribPie ::= TOTAL DOS_PUNTOS valNumerico 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("atribPie",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // elTipo ::= CANTIDAD 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("elTipo",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // elTipo ::= PORCENTAJE 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("elTipo",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // listaCadenas ::= listaCadenas COMA CADENA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("listaCadenas",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // listaCadenas ::= CADENA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("listaCadenas",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // listaNumeros ::= listaNumeros COMA valNumerico 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("listaNumeros",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // listaNumeros ::= valNumerico 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("listaNumeros",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // valNumerico ::= valNumerico SUMA valNumerico 
            {
              Object RESULT =null;
		/*result; establecimientoReporteOperaciones*/
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("valNumerico",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // valNumerico ::= valNumerico RESTA valNumerico 
            {
              Object RESULT =null;
		/*result; establecimientoReporteOperaciones*/
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("valNumerico",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // valNumerico ::= valNumerico MULTI valNumerico 
            {
              Object RESULT =null;
		/*result; establecimientoReporteOperaciones*/
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("valNumerico",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // valNumerico ::= valNumerico DIV valNumerico 
            {
              Object RESULT =null;
		/*result; establecimientoReporteOperaciones*/
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("valNumerico",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // valNumerico ::= NUMERO 
            {
              Object RESULT =null;
		/*result*/
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("valNumerico",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // valNumerico ::= RESTA valNumerico 
            {
              Object RESULT =null;
		/*result; establecimientoReporteOperaciones*/
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("valNumerico",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // valNumerico ::= PARENTESIS_A valNumerico PARENTESIS_C 
            {
              Object RESULT =null;
		/*result*/
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("valNumerico",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // segundaSeccion ::= segundaSeccion exe 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("segundaSeccion",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // segundaSeccion ::= exe 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("segundaSeccion",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // exe ::= EJECUTAR PARENTESIS_A CADENA PARENTESIS_C PUNTO_COMA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exe",17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
