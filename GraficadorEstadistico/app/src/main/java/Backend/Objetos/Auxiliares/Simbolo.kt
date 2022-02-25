package Backend.Objetos.Auxiliares

import java_cup.runtime.Symbol

class Simbolo(id: Int, linea: Int, columna: Int, o: Any?) : Symbol(id, linea, columna, o) {
    var anterior:Symbol? = null
    var siguiente:Symbol? = null
    
    constructor(id: Int, linea: Int, columna: Int, o: Any?, anterior:Symbol?):this(id, linea, columna, o){
        this.anterior = anterior
    }

    companion object{
        fun parseToken(objeto: Any?): Simbolo? {
            return objeto as Simbolo?
        }
    }


    /*constructor(id: Int, linea:Int, columna:Int, valor:Object?) : this(id, linea, columna, valor, null, null) {
    }*///yo creo que los constructores del padre tb son válidos para éste...
}