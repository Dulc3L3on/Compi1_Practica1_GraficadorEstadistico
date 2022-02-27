package Backend.Manejadores

import Backend.Analizadores.AnalizadorSemantico
import Backend.Objetos.Auxiliares.Atributos.Atributo
import Backend.Objetos.Auxiliares.Atributos.Contenido.ContenidoCadena
import Backend.Objetos.Auxiliares.Simbolo
import Backend.Objetos.Reportes.ReporteError
import java.lang.Math.ceil

class ManejadorErroresExtra(manejadorReportes: ManejadorReportes){
    private val manejadorReportes = manejadorReportes
    private var analizadorSemantico:AnalizadorSemantico = AnalizadorSemantico(manejadorReportes) //para revisar los erroers de los que se encarga [solo llama las funciones aquí]
    private var yaSeRegistroInexistenciaSeccion1 = false

    //empleado directamente en el Lexer
    fun detectarReservadadMalFormada(palabraIrreconocida: Simbolo){
        var irreconocida:Boolean = true

        if(palabraIrreconocida.value.toString().length>2){//puesto que no hay palabra reservada alguna < a este tamaño
            for (item in  this.analizadorSemantico.getPalabrasReservadas()){
                if(item.length*0.7 <= palabraIrreconocida.value.toString().length){
                    if(palabraIrreconocida.value.toString().contains(item.substring(0,((if (item.length==3) 3
                        else ceil(item.length*0.69)).toInt())),false)){

                        irreconocida = false
                    }
                }
            }
        }
        this.manejadorReportes.reportarError(ReporteError(palabraIrreconocida.value.toString(),
            palabraIrreconocida.left, palabraIrreconocida.right, "Lexico",
            (if (irreconocida) ReporteError.LEXER_INVALID_WORD else ReporteError.LEXER_MAYBE_YOU_MEANT + "item")))
    }

    //empleado por el ManejadorGraficacion
    fun verificarConsistenciaDeAtributos(){
        this.analizadorSemantico.verificarAtributos()
    }

    //empleado por el ManejadorGraficacion
    fun hubieronErrores():Boolean{
        return this.manejadorReportes.hubieronErrores()
    }

    //empleado en el ManejadorGraficacion xD
    fun verificarSeccionEjecucion(tituloGrafica: Atributo):Boolean{
        if(this.analizadorSemantico.getTitulosRegistrados().isEmpty() && !this.yaSeRegistroInexistenciaSeccion1){//así solo se mostrará un único error xD
            this.manejadorReportes.reportarError(ReporteError("Sección Ejecución",
                -1,-1, "Semántico", ReporteError.SEMANTIC_NO_SECTION_GRAPH_DEFINED))
            this.yaSeRegistroInexistenciaSeccion1 = true
        }else if(this.analizadorSemantico.getTitulosRegistrados().isNotEmpty()){
            return verificarExistenciaGrafica(tituloGrafica)
        }
        return false
    }

    private fun verificarExistenciaGrafica(atributoTitulo:Atributo):Boolean{
        val titulo:ContenidoCadena = atributoTitulo.contenido as ContenidoCadena

        for (tituloRegistrado in this.analizadorSemantico.getTitulosRegistrados()){
            if(titulo.cadena == tituloRegistrado.cadena){
                return true
            }
        }
        this.manejadorReportes.reportarError(ReporteError(titulo.cadena,
            titulo.linea,titulo.columna, "Semántico", ReporteError.SEMANTIC_NO_GRAPH_DEFINED))
        return false
    }

    fun getAnalizadorSemantico():AnalizadorSemantico{
        return this.analizadorSemantico
    }

    fun getManejadorReportes():ManejadorReportes{
        return this.manejadorReportes
    }
}