package Backend.Analizadores

import Backend.Objetos.Auxiliares.Simbolo
import Backend.Objetos.Auxiliares.TablaDeSimbolos

class AnalizadorSemantico {
    private var tablaDeSimbolos:TablaDeSimbolos = TablaDeSimbolos()

    fun setAtributo(){
        this.tablaDeSimbolos.setAtributo()
    }

    fun getPalabrasReservadas():Array<String>{
        return this.tablaDeSimbolos.getPalabrasReservadas()
    }

    //all esto depende del tipo e gráfica, si está vacía entonces no se deben exe estos análisis...
    fun verificarExistenciaAtributos(){//si lo de total es sintác, entonce este método debe estar directamente en el cuerpo de manejadorErroresExtra
        if(this.tablaDeSimbolos.getTipoGrafica().equals("Barras")){
            verficarExistenciaAtributosBarra()
        }else{
            verificarExistenciaAtributosPie()
        }
    }

    fun verficarExistenciaAtributosBarra(){
        val atributosPie = this.tablaDeSimbolos.getPalabrasReservadas().slice(4..7)



    }

    fun verificarExistenciaAtributosPie(){
        var atributosPie = this.tablaDeSimbolos.getAtributosPie()



    }

    fun verficarUnicidadTitulos(){

    }

    fun verificarExistenciaCatalogos(){

    }

    fun buscarNegativos_AtribsPie(){

    }
}