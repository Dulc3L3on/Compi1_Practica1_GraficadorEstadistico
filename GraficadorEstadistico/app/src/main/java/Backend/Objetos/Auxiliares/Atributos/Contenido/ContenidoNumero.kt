package Backend.Objetos.Auxiliares.Atributos.Contenido

class ContenidoNumero(linea: Int, columna: Int) :Contenido(linea, columna) {
    var numero:Double =0.0

    constructor(linea: Int, columna:Int, numero:Double)
            : this(linea, columna) {

      this.numero = numero
    }
}