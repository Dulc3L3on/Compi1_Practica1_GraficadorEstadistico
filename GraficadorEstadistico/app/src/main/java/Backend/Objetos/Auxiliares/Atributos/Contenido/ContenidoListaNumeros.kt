package Backend.Objetos.Auxiliares.Atributos.Contenido

class ContenidoListaNumeros(linea: Int, columna: Int) :Contenido(linea, columna) {
    lateinit var listaNumeros:DoubleArray//si no eqq a un arr de double que será enviado desde CUP, lo cb a Array<Double> y si lo que convirtió Java no eqq a esto, entonces déjalo como un ArrayList<Double> xD
    lateinit var lineasElementos:IntArray
    lateinit var columnasElementos:IntArray

    constructor(lineasElementos: IntArray, columnasElementos:IntArray, listaNumeros:DoubleArray)
            : this(-1, -1) {//la misma explic que en ContenidoTupla xD

        this.listaNumeros = listaNumeros
        this.lineasElementos = lineasElementos
        this.columnasElementos = columnasElementos
    }
}