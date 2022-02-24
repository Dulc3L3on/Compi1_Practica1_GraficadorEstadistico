package Backend.Objetos.Auxiliares.Atributos.Contenido

class ContenidoCadena(linea: Int, columna: Int) :Contenido(linea, columna) {
    lateinit var cadena:String

    constructor(linea: Int, columna:Int, cadena:String)
            : this(linea, columna) {

        this.cadena = cadena
    }
}