package Backend.Objetos.Reportes

class ReporteError(lexema: String, linea: Int, columna: Int, tipo:String, descripcion: String) :
    Reporte(lexema, linea, columna, descripcion) {

    private val tipo:String = tipo
        get(){
            return field
        }

    companion object {
        public const val LEXER_INVALID_WORD = "combinacion inválida de caracteres"
        public const val LEXER_MAYBE_YOU_MEANT = "quizá quisiste decir "
        public const val PARSER_EXPECTED = "se esperaba "
        public const val SEMANTIC_MISSING_ATTRIBUTE = "faltó especificar "//tipo, ejeX, ejeY, etiquetas... [se establece al haber inexistencias] lo de TOTAL irá aquí?... sino entonces este no es error SEMANTIC sino PARSER
        public const val SEMANTIC_REPEATED_TITLE = "el título ya existe en definición # "//# de struct en la que se encontró la primer repitencia en el Array de títulos [ordenados asc, según orden de aparición]
        public const val SEMANTIC_NEGATIVE_VALUE = "gráficas de Pie no admiten #negativos"
    }

}