package Backend.Objetos.Auxiliares

import java_cup.runtime.Symbol

class Simbolo(id: Int, linea: Int, columna: Int, o: Any?, anterior:Symbol?) : Symbol(id, linea, columna, o) {
    val anterior:Symbol? = anterior
    var siguiente:Symbol? = null

    /*constructor(id: Int, linea:Int, columna:Int, valor:Object?) : this(id, linea, columna, valor, null, null) {
    }*///yo creo que los constructores del padre tb son válidos para éste...
}