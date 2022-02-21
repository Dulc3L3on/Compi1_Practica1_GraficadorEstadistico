package Backend.Objetos.Graficas

class Barras(titulo: String, unir: Array<IntArray>, ejeX:Array<String>, ejeY:IntArray) : Grafica(titulo, unir) {
    private var ejeX:Array<String> = ejeX//los dejé como array pensando en que son equivalentes a los arreglos en java [ya que convertiré las listas creadas en el parser a un array...
        get(){
            return field
        }
    private var ejeY:IntArray = ejeY
        get(){
            return field;
        }

    //cree los getter pensando en el hecho de que son variables private
}