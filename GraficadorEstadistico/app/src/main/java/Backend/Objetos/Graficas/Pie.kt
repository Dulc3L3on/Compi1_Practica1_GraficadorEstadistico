package Backend.Objetos.Graficas

class Pie(titulo: String, unir: Array<IntArray>, etiqueta:Array<String>, valores:IntArray,
          tipo:String, total:Double, extra:String) : Grafica(titulo, unir) {//simplemente cuando sea tipo %, se enviará un 0 a total xD

    private val etiqueta:Array<String> = etiqueta
        get(){
            return field
        }
    private val valores:IntArray = valores
        get(){
            return field
        }
    private val tipo:String = tipo
        get(){
            return field
        }
    private val total:Double = total
        get(){
            return field
        }
    private val extra:String = extra
        get(){
            return field
        }
}