package Backend.Manejadores

import Backend.Analizadores.AnalizadorSemantico
import Backend.Objetos.Auxiliares.Atributos.Atributo
import Backend.Objetos.Auxiliares.Atributos.Contenido.*
import Backend.Objetos.Auxiliares.Simbolo
import Backend.Objetos.Graficas.Barras
import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Graficas.Pie

class ManejadorGraficacion(manejadorErroresExtra:ManejadorErroresExtra) {
    private var manejadorErroresExtra:ManejadorErroresExtra = manejadorErroresExtra //para hacer la revisión de si se debe graficar o no... [Entonces solo a este se invocará en el parser...]
    private var analizadorSemantico:AnalizadorSemantico = this.manejadorErroresExtra.getAnalizadorSemantico()

    private var graficasDefinidas:ArrayList<Grafica> = ArrayList()
    private var listaDeEjecucion:ArrayList<Grafica> = ArrayList()

    //esto se exe dentro del parser [en las axn de las RP más generales de la definición de una gráfica B o P]
    fun analizarGraficaDefinida(tipoGrafica: Simbolo){
        this.manejadorErroresExtra.getAnalizadorSemantico().setTipoGrafica(tipoGrafica)
        this.manejadorErroresExtra.verificarConsistenciaDeAtributos()

        if(!this.manejadorErroresExtra.hubieronErrores()){
            addGrafica(tipoGrafica.value.toString())
        }
        //se limpia la tabla de símbolos, para que al llegar la otra estructura, all esté preparado para ella...
        this.analizadorSemantico.clearTemp()
    }

    private fun addGrafica(tipo:String){
        val atributosGraficoBarras = this.analizadorSemantico.getAtributosGraficoBarras()
        val atributosGraficoPie = this.analizadorSemantico.getAtributosGraficoPie()

        if(tipo == "Barras"){
            this.graficasDefinidas.add(Barras(
                (this.analizadorSemantico.getContenidoDeAtributo("titulo") as ContenidoCadena).cadena,
                (this.analizadorSemantico.getContenidoDeAtributo("unir") as ContenidoTuplas).tuplas,
                (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoBarras[0]) as ContenidoListaCadenas).listaCadenas,
                (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoBarras[1]) as ContenidoListaNumeros).listaNumeros))
        }else{
            val tipoTotal = (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoPie[3]) as ContenidoCadena).cadena

            this.graficasDefinidas.add(Pie(
                (this.analizadorSemantico.getContenidoDeAtributo("titulo") as ContenidoCadena).cadena,
                (this.analizadorSemantico.getContenidoDeAtributo("unir") as ContenidoTuplas).tuplas,
                (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoPie[0]) as ContenidoListaCadenas).listaCadenas,
                (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoPie[1]) as ContenidoListaNumeros).listaNumeros,
                tipoTotal,
                (if(tipoTotal == "Cantidad") (this.analizadorSemantico.getContenidoDeAtributo("total") as ContenidoNumero).numero else 360.0),
                ((this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoPie[4]) as ContenidoCadena).cadena)
            ))
        }
    }

    //para uso interno en analizarExe
    private fun getGrafica(tituloGrafica:String):Grafica?{
        for(grafica in this.graficasDefinidas){
            if(grafica.titulo == tituloGrafica){
                return grafica
            }
        }
        return null//aunque nunca se llegará aquí porque siempre que se exe este método será porque existe una gráfica con el título en cuestión
    }

    //se utilizará en la axn de la RP "exe" del parser
    fun analizarEjecucion(atributoTitulo:Atributo){//Por qué da null Pointer???? no se supone que ya lo habíamo s arreglado :v
        if(this.manejadorErroresExtra.verificarSeccionEjecucion(atributoTitulo)){
            this.listaDeEjecucion.add(getGrafica((atributoTitulo.contenido as ContenidoCadena).cadena)!!)//Es un hecho que no será null por la verif que exe el if xD
        }
    }

    //esto se emplea en el exterior, luego de haber terminado all the analisis, esto permitirá
    //saber qué se debe bundlear y qué pantallas se deben limpiar dep de si está o no vacía xD...
    fun getListaEjecucion():ArrayList<Grafica>{
        return this.listaDeEjecucion
    }

    fun getGraficasDefinidas():ArrayList<Grafica>{
        return this.graficasDefinidas
    }
    //ya solo falta colocar estos métodos y los de ME_ext, MRep [erroresPosiblesHallarGram y repOps] donde corresponda en el parser uwu y probar xD y las axn aux para concat las listas de # y cadenas p.ej y tb las tuplas xD


}