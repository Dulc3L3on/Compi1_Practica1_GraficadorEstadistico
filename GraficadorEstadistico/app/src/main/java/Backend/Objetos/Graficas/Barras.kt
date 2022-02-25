package Backend.Objetos.Graficas

class Barras(titulo: String, unir: Array<DoubleArray>, ejeX:Array<String>, ejeY:DoubleArray) : Grafica(titulo, unir) {
    var ejeX:Array<String> = ejeX//los dejé como array pensando en que son equivalentes a los arreglos en java [ya que convertiré las listas creadas en el parser a un array...
    var ejeY:DoubleArray = ejeY

    //cree los getter pensando en el hecho de que son variables private, pero aquí los getter y setter son como atribs de las variables, entonces si la var es privada, estos tb lo serán, por eso es que Kotlin los crea por defecto, porque a ellos se accede a travez de la var/atrib...
}