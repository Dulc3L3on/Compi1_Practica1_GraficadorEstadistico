package Backend.Objetos.Graficas

class Pie(titulo: String, unir: Array<IntArray>, etiquetas:Array<String>, valores:DoubleArray,
          tipo:String, total:Double, extra:String) : Grafica(titulo, unir) {//simplemente cuando sea tipo %, se enviará un 0 a total xD, mejor 360.0, porque al final de ctas total se usa para calcular el % a emplear... y no es que al no estar total no vaya a tener un total xD, sino que es para ahorrarle al user add un valor fijo xD

    val etiquetas:Array<String> = etiquetas
    val valores:DoubleArray = valores
    val tipo:String = tipo
    val total:Double = total
    val extra:String = extra

}