package Backend.Analizadores

import Backend.Manejadores.ManejadorReportes
import Backend.Objetos.Auxiliares.Atributos.Atributo
import Backend.Objetos.Auxiliares.Atributos.Contenido.*
import Backend.Objetos.Auxiliares.Simbolo
import Backend.Objetos.Auxiliares.TablaDeSimbolos
import Backend.Objetos.Reportes.ReporteError
import android.util.Log
import kotlin.math.abs

class AnalizadorSemantico(manejadorReportes: ManejadorReportes) {
    private var tablaDeSimbolos:TablaDeSimbolos = TablaDeSimbolos()
    private var manejadorReportes:ManejadorReportes = manejadorReportes
    private var elementosCatalogoUsados:ArrayList<Double> = ArrayList()//esto es para verificar la unicidad de ubicaciones de elementos del ejeX y etiquetas

    fun setAtributo(atributo: Atributo){//se empleará en el parser
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
            this.manejadorReportes.reportarError(ReporteError(atributo,-1,-1, "Semántico",
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

            this.tablaDeSimbolos.registrarTitulo(tituloHallado)
        }
    }//Listo

    private fun verificarUnicidadTitulo(titulo:ContenidoCadena){
        for (elemento in this.tablaDeSimbolos.getTitulosRegistrados()){//imagino que no se exe si no tiene elementos... sino le pones un if xD
            if(titulo.cadena == elemento.cadena){
                this.manejadorReportes.reportarError(ReporteError(titulo.cadena, titulo.linea,
                    titulo.columna, "Semántico", ReporteError.SEMANTIC_REPEATED_TITLE +
                            elemento.linea + " y columna: "+ elemento.columna))
            }
        }
    }//Listo

    fun verificarAtributos(){//si lo de total es sintác, entonce este método debe estar directamente en el cuerpo de manejadorErroresExtra
        verificarTitulo()

        if(this.tablaDeSimbolos.getTipoGrafica() == "Barras"){
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
                    "ejex" -> elementosEjeX = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaCadenas).listaCadenas//ejex y etiquetas ya no se revisa, porque si existe quiere decir que está bien... xD
                    "ejey" -> elementosEjeY = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaNumeros).listaNumeros//ejey se verificará, si es que no se puede graficar barras con datos negativos [pero hasta donde sé si se puede xD]
                    "unir" -> verificarElementosUnir(elementosEjeX, elementosEjeY,this.tablaDeSimbolos.getAtributoHallado())//siempre será != null porque se entra cuando existe una sola vez
                }
            }
        }
    }//Listo

    private fun verificarElementosUnir(elementosX:Array<String>?, elementosY:DoubleArray?, atributoTuplas:Atributo):Atributo{//quité lo de eje, como tb se puede utilizar para los eqq de la gráfica de Pie xD
        var datosAUnir =  (atributoTuplas.contenido as ContenidoTuplas)
        var tuplas:Array<DoubleArray> = datosAUnir.tuplas
        elementosCatalogoUsados.clear()

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
        return atributoTuplas//:v solo porque no se puede hacer una asignación en el espacio donde se coloca el parám a enviar ;-; xD
    }//Listo

    private fun verificarTupla(numeroTupla:Int, tupla:DoubleArray, tipoElementos:Int,
                numeroElementos:Int, linea:Int, columna:Int){//esta es la línea y columna para la tuppla en sí, no para cada elemento de ella xD

        if((tupla[tipoElementos] < 0) || (tupla[tipoElementos] >= numeroElementos)){
            this.manejadorReportes.reportarError(ReporteError((tupla[tipoElementos]).toString(),//ya no miro nec enviar el izq y der para que sepa a cuál de los elemntos me refiero, porque envío la línea y col de la tupla y porque el string que muestre podría no ser exactamnete igual al que tiene xD, como se ignoran los espacios y porque como aquí se envía lo que tiene el result, no la operación que escribió el user necesariamente... xD
                linea, columna, "Semántico", (ReporteError.SEMANTIC_BAD_INDEX + numeroTupla)))
        }else if(tipoElementos == 0){
            verificarUnicidaDeApartados(tupla[0], numeroTupla, linea, columna)
        }//hago que lo revise aunque esté bien la posición??? no afecta en nada, quizá podría ahorrar más tiempo después puesto que va a arreglar más errores de una sola vez
        verificarTipoNumeroDeUbicacion(tupla[tipoElementos], linea, columna)
    }//Listo

    private fun verificarUnicidaDeApartados(ubicacionUsada:Double, numeroTupla:Int, lineaTupla:Int, columnaTupla:Int){//este método es para ver que no se repitan items de ejex/etiquetas
        if(elementosCatalogoUsados.isEmpty()){
            elementosCatalogoUsados.add(ubicacionUsada)
        }else{
            for(elementoUsado in elementosCatalogoUsados){
               if(ubicacionUsada == elementoUsado){
                   this.manejadorReportes.reportarError(ReporteError((ubicacionUsada).toString(),//ya no miro nec enviar el izq y der para que sepa a cuál de los elemntos me refiero, porque envío la línea y col de la tupla y porque el string que muestre podría no ser exactamnete igual al que tiene xD, como se ignoran los espacios y porque como aquí se envía lo que tiene el result, no la operación que escribió el user necesariamente... xD
                       lineaTupla, columnaTupla, "Warning",
                       (ReporteError.SEMANTIC_WARNING_REPEATED_REFERENCE + numeroTupla)))
               }
            }
            elementosCatalogoUsados.add(ubicacionUsada)//puesto que aún no se había utilizado
        }
    }

    private fun verificarTipoNumeroDeUbicacion(ubicacion:Double, lineaTupla:Int, columnaTupla:Int){
        if(abs(ubicacion % 1)>0){//ya que va a hacer la revisión aunque sea negativo, para dar de una vez el informe xD...
            this.manejadorReportes.reportarError(ReporteError((ubicacion).toString(),//ya no miro nec enviar el izq y der para que sepa a cuál de los elemntos me refiero, porque envío la línea y col de la tupla y porque el string que muestre podría no ser exactamnete igual al que tiene xD, como se ignoran los espacios y porque como aquí se envía lo que tiene el result, no la operación que escribió el user necesariamente... xD
                lineaTupla, columnaTupla, "Warning",
                (ReporteError.SEMANTIC_CANT_BE_FRACTION)))//debes si queda incluir numeroTupla o no...
        }
    }

    private fun verificarExistenciaAtributosPie(){
        val atributosPie = this.tablaDeSimbolos.getAtributosPie()
        var etiquetas:Array<String>? = null
        var valores:DoubleArray? = null
        var tuplas:Atributo? = null
        var tipo:String = ""//si este no se definió, el vacío hará que no se verifique lo de total, lo cual debe ser así xD

        for (atributo in atributosPie){
            if(verificarExistenciaAtributo(atributo)==1){
                when(atributo){
                    "etiquetas" -> etiquetas = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaCadenas).listaCadenas
                    "valores" -> valores = buscarNegativos_AtribsPie((this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoListaNumeros))
                    "unir" -> tuplas = verificarElementosUnir(etiquetas, valores, this.tablaDeSimbolos.getAtributoHallado())
                    //la verificación err_extra de tipo se realiza en la gramática [por la RP exclusiva xD] [pero me servirá su contenido, para no pedirlo otra vez eso haré aquí xD,
                    //extra se revisa en "verificarExistenciaAtributo" lo que está en el if de aquí arribita xD
                    "tipo" -> tipo = (this.tablaDeSimbolos.getAtributoHallado().contenido as ContenidoCadena).cadena
                }
            }
        }
        Log.i("contenido TIPO -> ", tipo)
        Log.i("contenido ETI -> ", etiquetas.toString())
        Log.i("contenido VAL -> ", valores.toString())//con que se imprima la dir, me informará que no es null...
        verificarExistenciaTotal(tipo, tuplas)//claramente debe ir aquí fuera, pues en este punto ya se han evaluado todos xD :v
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

    private fun verificarExistenciaTotal(tipo:String, tuplas:Atributo?){
        val valorExistencia = this.tablaDeSimbolos.existeAtributo("total")

        Log.i("existencia TOTAL -> ", valorExistencia.toString())
        if(tipo == "Porcentaje" && (valorExistencia > 0)){
            var atributoTotal:Atributo = this.tablaDeSimbolos.getAtributoHallado()

            this.manejadorReportes.reportarError(ReporteError("total",
                (atributoTotal.simbolo.left),
                (atributoTotal.simbolo.right),//puesto que en sí es la def del atrib y puesto que "total" rep a all él, entonces la línea y col deben ser las de él xD
                "Semántico", (ReporteError.SEMANTIC_UNNECESSARY_TOTAL)))
        }else if(tipo == "Cantidad" && (valorExistencia == 0)){
            this.manejadorReportes.reportarError(ReporteError("total",
                -1,-1, "Semántico",(ReporteError.SEMANTIC_NECESSARY_TOTAL +
                        this.tablaDeSimbolos.getLineaDefinicionGrafica())))
        }else{//si entra aquí quiere decir que si existe total es porque tipo = Cdad y si no es porque tipo = %
            verificarCorrespondenciaTotal(tipo, (if(valorExistencia == 0) 100.0
            else (this.tablaDeSimbolos.getAtributoHallado()!!.contenido as ContenidoNumero).numero), tuplas)//puedo asegurar que no será null por la revisión previa
        }
    }//Listo

    private fun verificarCorrespondenciaTotal(tipoTotal:String, total:Double, tuplas:Atributo?){
        if(tuplas != null){
            val datosAUnir:Array<DoubleArray> = (tuplas.contenido as ContenidoTuplas).tuplas
            var tupla:DoubleArray
            var sumatoria:Double = 0.0

            for (elemento in datosAUnir.indices){
                tupla = datosAUnir[elemento]//se obtiene el DoubleArray
                sumatoria += tupla[1]

            }
            if(sumatoria > total){
                this.manejadorReportes.reportarError(ReporteError(sumatoria.toString(),
                    (tuplas.simbolo.left), (tuplas.simbolo.right),//pongo la línea de la PR porque así logra representar all cojnto de tuplas xD lo que no se es debería enviar la columna o no...
                    "Semántico", (ReporteError.SEMANTIC_OVER_TOTAL + total +(if(tipoTotal == "Porcentaje") "%" else ""))))
            }
        }
    }//Listo digo yo xD, bien xD

    //este es para uso externo, lo usa el manajadorErrroresExtra [por las recomendaciones]
    fun getPalabrasReservadas():Array<String>{return this.tablaDeSimbolos.getPalabrasReservadas()}
    fun getAtributosGraficoBarras():Array<String>{return this.tablaDeSimbolos.getAtributosBarras()}
    fun getAtributosGraficoPie():Array<String>{return this.tablaDeSimbolos.getAtributosPie()}
    fun getTitulosRegistrados():ArrayList<ContenidoCadena>{return this.tablaDeSimbolos.getTitulosRegistrados()}
    fun clearTemp(){this.tablaDeSimbolos.clearTemp()}//tb habrá que llamarlo en alguna RP, por algún error que peuda existir, claro siempre y cuadno se sepa que se va a empezar a estudiar otra gráfica...
}