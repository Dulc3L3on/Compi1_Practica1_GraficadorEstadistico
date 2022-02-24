package Backend.Manejadores

import Backend.Analizadores.AnalizadorSemantico
import Backend.Objetos.Auxiliares.Simbolo
import Backend.Objetos.Reportes.ReporteError
import java.lang.Math.ceil

class ManejadorErroresExtra(manejadorReportes: ManejadorReportes){
    private val manejadorReportes = manejadorReportes
    private var analizadorSemantico:AnalizadorSemantico = AnalizadorSemantico() //para revisar los erroers de los que se encarga [solo llama las funciones aquí]

    fun detectarReservadadMalFormada(palabraIrreconocida: Simbolo){
        var irreconocida:Boolean = true

        if(palabraIrreconocida.value.toString().length>2){//puesto que no hay palabra reservada alguna < a este tamaño
            for (item in  this.analizadorSemantico.getPalabrasReservadas()){
                if(item.length*0.7 <= palabraIrreconocida.value.toString().length){
                    if(palabraIrreconocida.value.toString().contains(item.substring(0,((if (item.length==3) 3
                        else ceil(item.length*0.69)) as Int)),false)){

                        irreconocida = false
                    }
                }
            }
        }
        this.manejadorReportes.reportarError(ReporteError(palabraIrreconocida.value.toString(),
            palabraIrreconocida.left, palabraIrreconocida.right, "Lexico",
            (if (irreconocida) ReporteError.LEXER_INVALID_WORD else ReporteError.LEXER_MAYBE_YOU_MEANT + "item")))
    }

    fun verificarConsistenciaDeAtributos(){
        this.analizadorSemantico.verificarAtributos()
    }

    fun hubieronErrores():Boolean{
        return this.analizadorSemantico.hubieronErrores()
    }

    fun getAnalizadorSemantico():AnalizadorSemantico{
        analizadorSemantico.setManejadorReportes(this.manejadorReportes)//ya no es necesario hacerlo afuerita [es decir donde se invoca a los analizadores]
        return this.analizadorSemantico
    }
}