//Configuración
package Backend.Analizadores;
import java_cup.runtime.*;
import Backend.Objetos.Auxiliares.Simbolo;
import static Backend.Analizadores.ParserSym.*;
import Backend.Manejadores.ManejadorReportes;
import Backend.Manejadores.ManejadorErroresExtra;
import Backend.Objetos.Reportes.ReporteError;//yo supongo que si se debe importar para usar el eqq de ctes static, aunque sea kotlin... solo era para probar que si jala cosas de kotlin en Java xD

%%
//Código de usuario
%class Lexer
%unicode
%line
%column
%public
%cupsym ParserSym
%cup //recuerda reemplazarlo por %cup, al hacer eso tampoco se hace nec add el %int...

//macros [símbolos aceptados]
simbolosAceptados = ":"|","|"{"|"}"|";"|"["|"]"
operadores = "+"|"-"|"*"|"/"|"("|")"

//macros número
digito = [0-9]
numero = {digito}+("."{digito}+)?  

//macros auxiliares
finDeLinea = \r|\n|\r\n
tabulacion = [ \t\f]
espacioEnBlanco = {finDeLinea} | {tabulacion}

//macros para comentario
cuerpoComentario = [^\r\n]//no debería ser ^\r ^\n y ^\r\n??, también podría ser útil para definir cadena... solo que harbía que incluir ^\"
comentario = "#" {cuerpoComentario}* {finDeLinea}?//puesto que puede venir al final del archivo (lo cual no es un LT!!), pero no se supone que al encontrar uno de esos debería parar :v es decir si es expre reg solo debería terner declaradoel cuerpo aceptado, no el no aceptado, si fuera estado ahí si; digo yo...

%{
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
%}

%state STRING ERROR

%%
//Reglas léxicas

<YYINITIAL> "Def"               {return acccionReservada(DEF);}
<YYINITIAL> "def"               {return acccionReservada(DEF);}
<YYINITIAL> "Barras"            {return acccionReservada(BARRAS);}
<YYINITIAL> "Pie"               {return acccionReservada(PIE);}
<YYINITIAL> "titulo"            {return acccionReservada(TITULO);}
<YYINITIAL> "ejex"              {return acccionReservada(EJEX);}
<YYINITIAL> "ejey"              {return acccionReservada(EJEY);}
<YYINITIAL> "etiquetas"         {return acccionReservada(ETIQUETAS);}
<YYINITIAL> "valores"           {return acccionReservada(VALORES);}
<YYINITIAL> "unir"              {return acccionReservada(UNIR);}
<YYINITIAL> "tipo"              {return acccionReservada(TIPO);}
<YYINITIAL> "Cantidad"          {return acccionReservada(CANTIDAD);}
<YYINITIAL> "total"             {return acccionReservada(TOTAL);}
<YYINITIAL> "Porcentaje"        {return acccionReservada(PORCENTAJE);}
<YYINITIAL> "extra"             {return acccionReservada(EXTRA);}
<YYINITIAL> "Ejecutar"          {return acccionReservada(EJECUTAR);}

<YYINITIAL, ERROR> {simbolosAceptados}    {return accionSimbolosAceptados();}

<YYINITIAL>{
    {comentario}           {/*se ignora*/}//No debería hacer match con ninguna palabra reservada porque están declaras antes y de todos modos si apareciera comentada, siempre tendría un #, lo cual haría que entre aquí xD

    {numero}               {System.out.println("[L] numero ->"+ yytext());return symbol(NUMERO, new Double(yytext()), false);}//si hay problemas al enviar un DOuble, cuando el número sea un Int, entonces envía un String y allá loconvertirás a DOuble, no creo que de problema si no tiene ese String un punto decimal...

    {operadores}           {System.out.println("[L] simbolo-> "+ yytext());return symbol(((yytext().equals("+"))?SUMA:((yytext().equals("-"))?establecerMenosOResta():((yytext().equals("*"))?MULTI:((yytext().equals("/"))?DIV:((yytext().equals("("))?PARENTESIS_A:PARENTESIS_C))))), yytext(), true);}

    \"                     {string.setLength(0); yybegin(STRING);}//tengo que hacer que la entrada se convierta a un tipo específico, sino podría detectar como errónea la codificación de un caracter equivalente en otro "sistema" o modo de codificación, como sucedió con las comillas            

    {espacioEnBlanco}      {/*se ignora*/}
}

 <STRING> {
      \"                       { yybegin(YYINITIAL);System.out.println("cadena ->"+ string.toString() + " T: "+CADENA);return symbol(CADENA, new String(string) /*string.toString()*/, false);}//devulve el contenido dentro de las "" puesto que eso es lo que interesa xD
      [^\n\r\"\\]+             { string.append( yytext()); }
      //esto es por si caso colocaron literalmente esos símbolos, con las comillas se da más a notar lo que intento decir xD creo que es por eso :v xD
      \\t                      { string.append('\t'); }
      \\n                      { string.append('\n'); }
      \\r                      { string.append('\r'); }
      \\\"                     { string.append('\"'); }
      \\                       { string.append('\\'); }      
}                       

<ERROR>{    
    {espacioEnBlanco}           {accionParadaParaError();}//aquí se invoca a la función que se encarga de recisar lo de substring de reservadas xD
}

[^]                            {accionProcesarError();}        

//TERMINADO


//para esa expresión que necesito para concatenar los caracteres, se me ocurre que deje el [^] (el [^]+ me funcionó pero toma todo lo demás aunque el espacioEnBlanco aparezca antes)
//y que haga una variable como la de string [o use esa misma xD, si no da incongruencias] y que vaya concatenando lo del yytext() que será un caracter cada vez, por el hecho de que la expre reg solo hace match a un caracter por vez...
//y que en la expre reg de WS envíe toda esa concatenación a donde debe [en este caso que aún no imple CUP haga el sout] y luego haga el yypushBack con el tamaño del stringBuffer y luego el yyBegin con verificación...
//ya que si me funcionoó de manera individual y lo único que me faltaba era la concat, entocnes HAGAMOSLO!!! [si saldrá xD]

//cabe resaltar que ya probé [^]+, !WS, SNA = !SA y la expre original de incorrecto pero las 1ras dos toma todo a partir del ^ con el que se entró como error, la otro da problemas con JFLEX (crea un bug, había pensado en eso por lo que se explica que sucede con la expre original de incorrecto) y la expre orig da problemas cuando aparece un caracter que no se declaró en SNA [o que están en troa codif posiblemente [lo digo por esas comillas raras por las que me di cuenta]]

//ya está bien el agrupamiento de errores, pero estaba pensndo que para que reconociera SA entre todo el lexema de error hayado, para de alguna manera ayudar a que no se tenga que salir
//hasta la producción más externa para intentar recuperar el error por el hecho de no haber encontrado el token de recuperación, se saque de YYINI y se coloque en
//una agrupación de estados justo abajo de las PR que le pertenezca a <YYINI, ERROR> y addle un método para que exe lo que corresp cuando esté en error y otra cosa
//cuando no, o declara de nuevo esa expre reg pero en el cuerpo de ERROR y addle la axn de ERROR directamente
//pero no se que errores podría provocar... quizá por el momento dejémoslo así...


//ya tiene arreglado el problema con el Simbolo...
