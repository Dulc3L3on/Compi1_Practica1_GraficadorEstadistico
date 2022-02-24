package Backend.Analizadores

import Backend.Manejadores.ManejadorReportes
import Backend.Objetos.Auxiliares.Atributos.Atributo
import Backend.Objetos.Auxiliares.Atributos.Contenido.*
import Backend.Objetos.Auxiliares.Simbolo
import Backend.Objetos.Auxiliares.TablaDeSimbolos
import Backend.Objetos.Reportes.ReporteError

class AnalizadorSemantico {
    private var tablaDeSimbolos:TablaDeSimbolos = TablaDeSimbolos()
    private lateinit var manejadorReportes:ManejadorReportes

    //esto se emplea en el método getAnalizadorSem de ManejadorErroresExtra [por lo cual no debe haber pena de un NullPointer xD]
    fun setManejadorReportes(manejadorReportes:ManejadorReportes){
        this.manejadorReportes = manejadorReportes
    }

    //se empleará en el parser
    fun setAtributo(atributo: Atributo){
        this.tablaDeSimbolos.setAtributo(atributo)
    }

    fun getContenidoDeAtributo(nombreAtributo:String):Contenido{
        return this.tablaDeSimbolos.getContenidoDeAtributo(nombreAtributo)
    }

    fun setTipoGrafica(tipoGrafica:Simbolo){
        this.tablaDeSimbolos.setTipoGrafica(tipoGrafica)
    }

    private fun verificarExistenciaAtributo(atributo:String):Int{
        var vecesHallado:Int = this.tablaDeSimbolos.existeAtributo(atributo)

        if(vecesHallado == 0){
            this.manejadorReportes.reportarError(ReporteError(atributo,
                -1,-1, "Semántico",
                (ReporteError.SEMANTIC_MISSING_ATTRIBUTE + this.tablaDeSimbolos.getLineaDefinicionGrafica())))
        }else if(vecesHallado > 1){
            var atributoHallado = this.tablaDeSimbolos.getAtributoHallado()

            this.manejadorReportes.reportarError(ReporteError(atributo,
                atributoHallado.simbolo.left, atributoHallado.simbolo.right,
                "Semántico", ReporteError.SEMANTIC_REPEATED_ATTRIBUTE ))
        }
        return vecesHallado
    }//Listo

    //1.
    private fun verificarTitulo(){
        if(verificarExistenciaAtributo("titulo") == 1){//solo verifica cuando existe 1 vez, porque si etá repetido se podría decir TODAS las ocurrencias, pero no tiene chiste, porque al final de ctas los titulos sobrantes los tendrá que borrar
            val tituloHallado:ContenidoCadena = this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoCadena
            verificarUnicidadTitulo(tituloHallado)

            this.tablaDeSimbolos.registrarTitulo(tituloHallado.cadena)
        }
    }//Listo

    private fun verificarUnicidadTitulo(titulo:ContenidoCadena){
        for (elemento in this.tablaDeSimbolos.getTitulosRegistrados()){//imagino que no se exe si no tiene elementos... sino le pones un if xD
            if(titulo.cadena.equals(elemento)){
                this.manejadorReportes.reportarError(ReporteError(titulo.cadena, titulo.linea,
                    titulo.columna, "Semántico", ReporteError.SEMANTIC_REPEATED_TITLE))
            }
        }
    }//Listo

    fun verificarAtributos(){//si lo de total es sintác, entonce este método debe estar directamente en el cuerpo de manejadorErroresExtra
        verificarTitulo()

        if((this.tablaDeSimbolos.getTipoGrafica() as Simbolo).value.equals("Barras")){
            verficarExistenciaAtributosBarra()
        }else{
            verificarExistenciaAtributosPie()
        }
    }//Terminado xD

    private fun verficarExistenciaAtributosBarra(){
        val atributosBarra:Array<String> = this.tablaDeSimbolos.getAtributosBarras()
        var elementosEjeX:Array<String>? = null
        var elementosEjeY:DoubleArray? = null

        for (atributo in atributosBarra){
            if(verificarExistenciaAtributo(atributo)==1){//recuerda que acordamos que TODAS estas verificaciones extra se efectuarán si existe 1 vez
                when(atributo){
                    //ejex y etiquetas ya no se revisa, porque si existe quiere decir que está bien... xD
                    "ejex" -> elementosEjeX = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaCadenas).listaCadenas
                    //ejey se verificará, si es que no se puede graficar barras con datos negativos [pero hasta donde sé si se puede xD]
                    "ejey" -> elementosEjeY = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaNumeros).listaNumeros
                    "unir" -> verificarElementosUnir(elementosEjeX, elementosEjeY,
                              (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoTuplas))//siempre será != null porque se entra cuando existe una sola vez
                }
            }
        }
    }//Listo

    private fun verificarElementosUnir(elementosX:Array<String>?, elementosY:DoubleArray?, datosAUnir:ContenidoTuplas){//quité lo de eje, como tb se puede utilizar para los eqq de la gráfica de Pie xD
        var tuplas:Array<IntArray> = datosAUnir.tuplas

        for(tupla in tuplas.indices){
            if(elementosX!= null){
                verificarTupla((tupla+1), tuplas[tupla], 0, elementosX.size,
                    datosAUnir.lineaTuplas[tupla], datosAUnir.columnaTuplas[tupla])
            }
            if(elementosY != null) {
                verificarTupla((tupla+1), tuplas[tupla], 1, elementosY.size,
                    datosAUnir.lineaTuplas[tupla], datosAUnir.columnaTuplas[tupla])
            }
        }
    }//Listo

    private fun verificarTupla(numeroTupla:Int, tupla:IntArray, tipoElementos:Int,
                numeroElementos:Int, linea:Int, columna:Int){//esta es la línea y columna para la tuppla en sí, no para cada elemento de ella xD

        if((tupla[tipoElementos] < 0) || (tupla[tipoElementos] >= numeroElementos)){
            this.manejadorReportes.reportarError(ReporteError((tupla[tipoElementos]).toString(),//ya no miro nec enviar el izq y der para que sepa a cuál de los elemntos me refiero, porque envío la línea y col de la tupla y porque el string que muestre podría no ser exactamnete igual al que tiene xD, como se ignoran los espacios y porque como aquí se envía lo que tiene el result, no la operación que escribió el user necesariamente... xD
                linea, columna, "Semántico", (ReporteError.SEMANTIC_BAD_INDEX + numeroTupla)))
        }
    }//Listo

    private fun verificarExistenciaAtributosPie(){
        val atributosPie = this.tablaDeSimbolos.getAtributosPie()
        var etiquetas:Array<String>? = null
        var valores:DoubleArray? = null
        var tipo:String = ""//si este no se definió, el vacío hará que no se verifique lo de total, lo cual debe ser así xD

        for (atributo in atributosPie){
            if(verificarExistenciaAtributo(atributo)==1){
                when(atributo){
                    "etiquetas" -> etiquetas = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaCadenas).listaCadenas
                    "valores" -> valores = buscarNegativos_AtribsPie((this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaNumeros))
                    "unir" -> verificarElementosUnir(etiquetas, valores,  (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoTuplas))
                    //la verificación extra de tipo se realiza en la gramática [por la RP exclusiva xD] [pero me servirá su contenido, para no pedirlo otra vez eso haré aquí xD,
                    //extra se revisa en "verificarExistenciaAtributo" lo que está en el if de aquí arribita xD
                    "tipo" -> tipo = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoCadena).cadena
                }
            }
            verifcarCorrespondenciaTotal(tipo)
        }
    }//Listo

    private fun buscarNegativos_AtribsPie(elementos:ContenidoListaNumeros):DoubleArray{//tb aplicacría para ejeY si no se admiten vals (-) para formar barras xD
        var valoresEncontrados:DoubleArray = elementos.listaNumeros

        for (elemento in valoresEncontrados.indices){
            if(valoresEncontrados[elemento] < 0){
                this.manejadorReportes.reportarError(ReporteError((valoresEncontrados[elemento]).toString(),
                    elementos.lineasElementos[elemento], elementos.columnasElementos[elemento]
                    , "Semántico", (ReporteError.SEMANTIC_NEGATIVE_VALUE)))
            }
        }
        return valoresEncontrados //:v xD, solo porque no se puede colocar +1 axn en un caso del when xD
    }//Listo

    private fun verifcarCorrespondenciaTotal(tipo:String){
        if(tipo.equals("Porcentaje") && (this.tablaDeSimbolos.existeAtributo("total")==1)){
            this.manejadorReportes.reportarError(ReporteError("total",
                (this.tablaDeSimbolos.getAtributoHallado().simbolo.left),
                (this.tablaDeSimbolos.getAtributoHallado().simbolo.right),//puesto que en sí es la def del atrib y puesto que "total" rep a all él, entonces la línea y col deben ser las de él xD
                "Semántico", (ReporteError.SEMANTIC_UNNECESSARY_TOTAL)))
        }
    }//Listo

    //este es para uso externo, lo usa el manajadorErrroresExtra [por las recomendaciones]
    fun getPalabrasReservadas():Array<String>{
        return this.tablaDeSimbolos.getPalabrasReservadas()
    }

    fun getAtributosGraficoBarras():Array<String>{
        return this.tablaDeSimbolos.getAtributosBarras()
    }

    fun getAtributosGraficoPie():Array<String>{
        return this.tablaDeSimbolos.getAtributosBarras()
    }

    //Este lo usa el Manejador de Graficación para saber si debe o no add la gráfica xD,tb será utilizado para saber que tipos de pantallas debo llenar y cuáles limpiar xD
    fun hubieronErrores():Boolean{
        return (this.manejadorReportes.getListaErrores().size>0)
    }

    fun limpiarRegistrosTemporalesTablaSimbolos(){
        this.tablaDeSimbolos.clearTemp()
    }

}