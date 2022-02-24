package Backend.Objetos.Reportes

class ReporteError(lexema: String, linea: Int, columna: Int, tipo:String, descripcion: String) :
    Reporte(lexema, linea, columna, descripcion) {

    val tipo:String = tipo

    companion object {
        const val LEXER_INVALID_WORD = "combinacion inválida de caracteres"
        const val LEXER_MAYBE_YOU_MEANT = "quizá quisiste decir "
        const val PARSER_EXPECTED = "se esperaba "
        const val SEMANTIC_MISSING_ATTRIBUTE = "no se definió para la gráfica en línea "// la incexistencia de TOTAL irá aquí?... sino entonces este no es error SEMANTIC sino PARSER
        //sería genial numerar las estructuras de definición de gráficas y tb las de exe, así sería más eficaz el msje que da el # de definición...
        //sino tb podrías decir "el título ya nombra una gráfica"/o "el título ya ha sido def en una gráfica/"este título ya ha sido utilizado" xD"
        const val SEMANTIC_REPEATED_TITLE = "este titulo ya nombra una gráfica"//# de struct en la que se encontró la primer repitencia en el Array de títulos [ordenados asc, según orden de aparición]
        const val SEMANTIC_REPEATED_ATTRIBUTE = "se define +1 vez en la gráfica actual"
        const val SEMANTIC_NEGATIVE_VALUE = "no se admiten #negativos en atributo \"valores\""
        //iba a poner un msje para cuando fuera > al tamaño y otro cuando fuera <, para en un decir "no hace referencia a ninguna posición de... [ejex p.ej] y el otro decir "las posiciones deben ser +" o "las posiciones deben ser un valor entre 0 y el tamaño del catalogo-1
        const val SEMANTIC_BAD_INDEX = "una posición es <0 ó >(elementos-1) en tupla #"//aquí tb se debe enviar el izq y der para que sepa a cual de los 2 elementos de la tupla estoy haciendo referencia xD
        const val SEMANTIC_UNNECESSARY_TOTAL = "atributo innecesario [\"tipo\" = Cantidad]"
    }

}