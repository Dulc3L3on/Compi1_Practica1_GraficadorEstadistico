package Backend.Objetos.Graficas

open class Grafica(titulo:String, unir: Array<IntArray>) {
    val titulo:String = titulo
    val unir:Array<IntArray> = unir

    constructor():this("", Array(0){IntArray(0)}){}//si está difícil acceder a los elementos del arreglo interno, entonces crea un objetoAUxiliar Tupla :v... aunque esparía que fuera muy similar a la manera en que trabaja Java
}

//Nota en Java se habíá add implements Serializable para que no hubieran pérdida de datos, pero como es de una clase a otra del mismo proyecto
//no es como que si se vaya a guarda (en memoria, o como un archivo o en una DB) como un stream o se vaya a usar en una petición http,
// entonces no se si sea nec, si se puede imple en kotlin hazlo
//sino si te da problemas, esperemos que no, puede que sea eso...