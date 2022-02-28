package Backend.Objetos.Reportes

class ReporteError(lexema: String, linea: Int, columna: Int, tipo:String, descripcion: String) :
    Reporte(lexema, linea, columna, descripcion) {

    val tipo:String = tipo

    companion object {
        const val LEXER_INVALID_WORD = "combinacion inválida de caracteres"
        const val LEXER_MAYBE_YOU_MEANT = "quizá quisiste decir "

        //Errores revisados internamente
        //Generales
        const val SEMANTIC_FATAL_ERROR = "imposible continuar con el análisis"
        const val SEMANTIC_PARSER_EXPECTED = "se esperaba "
        const val SEMANTIC_SYNTAX_ERROR = "sintaxis incorrecta, se esperaba: "
        const val SEMANTIC_UNRECOVERED_SYNTAX_ERROR = "imposible de entender la estructura establecida"
        //Definición gráficas
        const val SEMANTIC_MISSING_ATTRIBUTE = "no se definió para la gráfica en #línea "// la incexistencia de TOTAL irá aquí?... sino entonces este no es error SEMANTIC sino PARSER
        //sería genial numerar las estructuras de definición de gráficas y tb las de exe, así sería más eficaz el msje que da el # de definición...
        //sino tb podrías decir "el título ya nombra una gráfica"/o "el título ya ha sido def en una gráfica/"este título ya ha sido utilizado" xD"
        const val SEMANTIC_REPEATED_TITLE = "ya nombra una gráfica en linea: "//linea y col donde se encuentra esa primera aparición
        const val SEMANTIC_REPEATED_ATTRIBUTE = "se define +1 vez en la gráfica actual"
        const val SEMANTIC_NEGATIVE_VALUE = "no se admiten #negativos en atributo \"valores\""
        //iba a poner un msje para cuando fuera > al tamaño y otro cuando fuera <, para en un decir "no hace referencia a ninguna posición de... [ejex p.ej] y el otro decir "las posiciones deben ser +" o "las posiciones deben ser un valor entre 0 y el tamaño del catalogo-1
        const val SEMANTIC_BAD_INDEX = "una posición es <0 ó >(elementos-1) en tupla #"//aquí tb se debe enviar el izq y der para que sepa a cual de los 2 elementos de la tupla estoy haciendo referencia xD
        const val SEMANTIC_CANT_BE_FRACTION = "una posición debe ser un número entero"
        const val SEMANTIC_UNNECESSARY_TOTAL = "atributo innecesario pues \"tipo\" = Porcentaje"
        const val SEMANTIC_NECESSARY_TOTAL = "atributo necesario [\"tipo\" = Cantidad] para gráfica en #línea "
        const val SEMANTIC_OVER_TOTAL = "la suma de los valores de ejey sobrepasa "//100% o el total
        const val SEMANTIC_WARNING_REPEATED_REFERENCE = "referencia de ubicación repetida en tupla#"//quería algo como-> ubicación repetida en X puede generarse apartado con el mismo nombre [tupla# ]
        //Ejecución
        const val SEMANTIC_NO_SECTION_GRAPH_DEFINED = "no hay gráficas definidas para ejecutar"
        const val SEMANTIC_NO_GRAPH_DEFINED = "no existe una gráfica con ese título"
        //Errores catheados por la gramática
        const val SEMANTIC_ERROR_SECTION_STRUCT = "estructura de sección incorrecta, se esperaba: "
  /*_*/ const val SEMANTIC_EXPECTED_DEF = "faltó instrucción \"Def\" o \"def\""
        const val SEMANTIC_BAD_DEFINITION_STRUCT = "estructura de definición inválida, se esperaba: "
        const val SEMANTIC_NUMBER_OF_PARAMS_B = "grácfico de Barras requiere 4 parámetros"
        const val SEMANTIC_NUMBER_OF_PARAMS_P = "gráfico de Pie requiere 6 (o 7) parámetros"
        const val SEMANTIC_EXPECTED_SEMICOLON = "faltó \";\""
        const val SEMANTIC_BAD_COUPLE = "para una tupla se espera: { # , # }"
        const val SEMANTIC_BAD_ATRIB_GRAPHIC_STRUCT = "atributo gráfica inválido, se esperaba: "
        const val SEMANTIC_BAD_ATRIB_BARRAS_STRUCT = "atributo Barras inválido, se esperaba: "
        const val SEMANTIC_BAD_ATRIB_PIE_STRUCT = "atributo Pie inválido, se esperaba: "
        const val SEMANTIC_INVALID_TOTAL_TYPE= "tipo de total inválido se esperaba CANTIDAD/PORCENTAJE "
        const val SEMANTIC_BAD_STRING_LIST_STRUCT = "lista de cadenas inválida, se esperaba: "
        const val SEMANTIC_BAD_NUMBER_EXPRESION = "expresión numérica inválida, se esperaba: "
  /*-*/ const val SEMANTIC_BAD_NUMBER_LIST_STRUCT = "lista numérica inválida, se esperaba: "
  /*-*/ const val SEMANTIC_BAD_OPERATION = "formato de operacion aritmética inválido"//o mejor pongo el "se esperaba"... y la lsta???
  /*_*/ const val SEMANTIC_BAD_EXE_STRUCT = "comando de ejecución inválido, se esperaba: "
        //si nos dice que ha conflictos debido a la add de errores, entonces se eliminará donde esté el conflicto y se dejará el msje general "se esperaba" en la prod de error del padre...
    }

}