package Backend.Objetos.Auxiliares

import java_cup.runtime.Symbol
import java.io.Serializable

class Simbolo(id:Int, linea:Int, columna:Int, lexema:Any, anterior: Simbolo?): Serializable {//ya si requieres un número simplemente los parseas a un Double, o creas otro constructor para que reciba ese tipo [con las pruebas que hiciste en Java, ya te diste cuenta de que no hay problema si el dato es entero, puesto que se hace en Java xD, puede castear un entero sin problemas xD
    var sym:Int= id
    var left:Int = linea
    var right:Int = columna
    var anterior:Simbolo? = anterior//un Symbol, porque en caso de requerirlo, no necesitas más que el lexema... entonces bien podrías enviar el lexema nada más xD :p
    var siguiente:Simbolo? = null
    var value:Any = lexema//así puedes enviar un double o string, que son los únicos valores que empleas xD

    companion object{
        fun parseToken(objeto: Any?): Simbolo? {
            return objeto as Simbolo?
        }
    }


    /*constructor(id: Int, linea:Int, columna:Int, valor:Object?) : this(id, linea, columna, valor, null, null) {
    }*///yo creo que los constructores del padre tb son válidos para éste...
}