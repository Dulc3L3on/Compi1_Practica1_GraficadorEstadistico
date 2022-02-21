package Backend.Objetos.Auxiliares

class TablaDeSimbolos {
    private val palabrasReservadas = arrayOf("Def", "def", "Barras", "Pie", "titulo", "ejex", "ejey",
        "unir", "etiquetas", "valores", "tipo", "Cantidad", "total", "Porcentaje", "extra",
        "Ejecutar")
    private val atributos_Pie = arrayOf("titulo", "etiquetas", "valores", "unir", "tipo", "Cantidad",
        "total", "Porcentaje", "extra")

    private var tipoGrafica:String = ""//si no existe este campo en la defi, se colocará el ""
    private var titulosRegistrados:ArrayList<String> = ArrayList<String>() //el mensaje sería "el titulo %s está asignado en la definición de grafica # [según la posición en que se encuentre almacenado el valor
                                                                   //pero harbría manera de registrar la línea del error??? SI, porque se revisará de una vez y ahí se tiene el acceso a los datos de la tabla, que serán de tipo Sym y por lo tanto se tendrá acceso a esos valores xD

    //var la tabla, mira si hash o mejor que sea una lista y aquí como sabes que almacenarás ya sea obj Atrib o Sym [Dependiendo de si se pueded o no, entonces sabes qué método invocar para tener el nombre xD o convertirlo a su rep string :0 cabal que tb puedes acceder a sym_form_ID desde aquí tn xD


    //la tabla almacenará objetos sym ya no "Atrib"


//    @JvmName("getPalabrasReservadas1")
    fun setAtributo(){

    }

    fun getAtributo(){

    }

    fun getTipoGrafica(){

    }

    fun getPalabrasReservadas():Array<String>{
        return this.palabrasReservadas
    }

    fun getAtributosPie():Array<String>{
       return this.atributos_Pie
    }

    fun getTitulosRegistrados():ArrayList<String>{
        return this.titulosRegistrados
    }
}