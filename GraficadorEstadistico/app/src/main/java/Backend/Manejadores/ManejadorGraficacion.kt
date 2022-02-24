package Backend.Manejadores

import Backend.Analizadores.AnalizadorSemantico
import Backend.Objetos.Auxiliares.Atributos.Contenido.*
import Backend.Objetos.Auxiliares.Simbolo
import Backend.Objetos.Graficas.Barras
import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Graficas.Pie

class ManejadorGraficacion(manejadorErroresExtra:ManejadorErroresExtra) {
    private var manejadorErroresExtra:ManejadorErroresExtra = manejadorErroresExtra //para hacer la revisión de si se debe graficar o no... [Entonces solo a este se invocará en el parser...]
    private var graficas:ArrayList<Grafica> = ArrayList()
    private var analizadorSemantico:AnalizadorSemantico = this.manejadorErroresExtra.getAnalizadorSemantico()

    //esto se exe dentro del parser [en las axn de las RP más generales de la definición de una gráfica B o P]
    fun analizarParametrosGrafica(tipoGrafica: Simbolo){
        this.manejadorErroresExtra.getAnalizadorSemantico().setTipoGrafica(tipoGrafica)

        this.manejadorErroresExtra.verificarConsistenciaDeAtributos()
        if(!this.manejadorErroresExtra.hubieronErrores()){
            addGrafica(tipoGrafica.value.toString())
        }
    }

    private fun addGrafica(tipo:String){
        val atributosGraficoBarras = this.analizadorSemantico.getAtributosGraficoBarras()
        val atributosGraficoPie = this.analizadorSemantico.getAtributosGraficoPie()

        if(tipo.equals("Barras")){
            this.graficas.add(Barras(
                (this.analizadorSemantico.getContenidoDeAtributo("titulo") as ContenidoCadena).cadena,
                (this.analizadorSemantico.getContenidoDeAtributo("unir") as ContenidoTuplas).tuplas,
                (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoBarras[0]) as ContenidoListaCadenas).listaCadenas,
                (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoBarras[1]) as ContenidoListaNumeros).listaNumeros))
        }else{
            val tipoTotal = (this.analizadorSemantico.getContenidoDeAtributo(atributosGraficoPie[3]) as ContenidoCadena).cadena

            this.graficas.add(Pie(
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

    //esto se emplea en el exterior, luego de haber terminado all the analisis, esto permitirá
    //saber qué se debe bundlear y qué pantallas se deben limpiar...
    fun getGraficas():ArrayList<Grafica>?{
        if(!this.manejadorErroresExtra.hubieronErrores()){
            return this.graficas
        }
        return null
    }

    //ya solo falta colocar estos métodos y los de ME_ext, MRep [erroresPosiblesHallarGram y repOps] donde corresponda en el parser uwu y probar xD y las axn aux para concat las listas de # y cadenas p.ej y tb las tuplas xD
}