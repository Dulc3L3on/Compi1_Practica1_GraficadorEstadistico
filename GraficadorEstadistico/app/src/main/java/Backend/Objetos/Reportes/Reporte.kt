package Backend.Objetos.Reportes

open class Reporte (lexema:String, linea:Int, columna:Int, descripcion:String){
    val lexema:String = lexema
    val linea:Int = linea
    val columna:Int = columna
    val descripcion:String = descripcion //en el caso de reporte de operaciones será el ejemplo de ocurrencia

    //no agrego los get porque se supone que ya están xD
}