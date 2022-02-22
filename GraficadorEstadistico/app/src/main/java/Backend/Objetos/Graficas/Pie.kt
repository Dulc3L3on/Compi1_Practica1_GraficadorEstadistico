package Backend.Objetos.Graficas

class Pie(titulo: String, unir: Array<IntArray>, etiqueta:Array<String>, valores:IntArray,
          tipo:String, total:Double, extra:String) : Grafica(titulo, unir) {//simplemente cuando sea tipo %, se enviará un 0 a total xD

    val etiqueta:Array<String> = etiqueta
    val valores:IntArray = valores
    val tipo:String = tipo
    val total:Double = total
    val extra:String = extra

}