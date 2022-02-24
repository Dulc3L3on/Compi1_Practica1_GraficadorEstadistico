package Backend.Objetos.Reportes

open class Reporte (lexema:String, linea:Int, columna:Int, descripcion:String){//en el caso de las operaciones, el lexema estará conformado tb por los valores de los símbolos a su izq y der!
    val lexema:String = lexema
    val linea:Int = linea
    val columna:Int = columna
    val descripcion:String = descripcion //en el caso de reporte de operaciones será el ejemplo de ocurrencia

    //no agrego los get porque se supone que ya están xD
}