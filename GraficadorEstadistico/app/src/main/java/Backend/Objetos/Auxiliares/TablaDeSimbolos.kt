package Backend.Objetos.Auxiliares

import Backend.Objetos.Auxiliares.Atributos.Atributo
import Backend.Objetos.Auxiliares.Atributos.Contenido.Contenido
import Backend.Objetos.Auxiliares.Atributos.Contenido.ContenidoCadena

class TablaDeSimbolos {
    private val palabrasReservadas = arrayOf("Def", "def", "Barras", "Pie", "titulo", "ejex", "ejey",
                                             "unir", "etiquetas", "valores", "tipo", "Cantidad",
                                             "total", "Porcentaje", "extra", "Ejecutar")
    private val atributos_Barras = arrayOf("ejex", "ejey", "unir")
    private val atributos_Pie = arrayOf("etiquetas", "valores", "unir", "tipo", "extra")//quité total de aquí porque ese debe existir SI tipo == Cantidad, lo de Cdad y % no debe estar aquí porque eso es contenido, no tipo de atributo, quien los rep aquí es tipo...

    private var tipoGrafica:Simbolo? = null//guardo un Simbolo para tener acceso a la línea y columna de la gráfica, para así hacer referencia a esa definición xD [más que all por la línea xD :v]
    private var titulosRegistrados:ArrayList<ContenidoCadena> = ArrayList() //a menos que MUESTRES el # de definición de gráfica en la interfaz, solo add cuando existenciasTitulo == 1, si logras lo de la interfaz, entonces add un elemento a esta lista [nulo] solo con el fin de que cada posición que ocupe un título si corresp al #defiGraf -1 xD

    //:0 cabal que tb puedes acceder a sym_form_ID desde aquí tn xD, pero de todas maneras requerías almacenar el Simbolo por la línea y columna del nombre del tipo de atrib
    private var atributos:ArrayList<Atributo> = ArrayList()//esta es la tabla en sí xD

    private var atributoHallado:Atributo? = null

//    @JvmName("getPalabrasReservadas1")
    fun setAtributo(atributo:Atributo){
        this.atributos.add(atributo)
    }

    //es para obtener los atributos de la TS!!
    fun getContenidoDeAtributo(nombreAtributo:String): Contenido {
        var atributoAuxiliar:Atributo? = null

        for (atributo in this.atributos){
            if(atributo.simbolo.value.toString() == nombreAtributo){
                atributoAuxiliar = atributo//solo la 1er aparición porque para qué voy a estar app las demás verificaciones si en primer lugar todos los datos extra tendrá que eliminarlos...
                this.atributos.remove(atributoAuxiliar)//así la búsqueda posterior se reduce xD
                break//paro porque se supone que si llegó aquí es porque no hay repitencias y tampoco ausencias xD
            }//si te vuelve a dar error en addGrafica y ese es de tipo ConcurrentModification, entonces puedes hacer dos cosas, usar el for normal [imagino que es con el que puedes acceder a los índices, en el caso de kotlin] o crear tra lista, asignarle a ella los valores y obtener los índices de la lista auxiliar para hacer la eli [stackOver dice que se recomienda usar remove xD] en la lista original, bueno esto en caso que tb el for que accede a los índices diera ese prob, pero no creo que con el break que le addste pueda surgir de nuevo esa exception xD
        }
        return atributoAuxiliar!!.contenido//nunca se devolverá un null puesto que cuando se utiliza este método es porque están all the atributes
    }

    fun setTipoGrafica(tipoGrafica:Simbolo){
        this.tipoGrafica = tipoGrafica
    }

    fun registrarTitulo(titulo:ContenidoCadena){//podría guardar un ContenidoCadena para tener acceso a la línea y columna de él, pero como no voy a hacer el análisis para los titulos sobrantes de una definición, entonces mejor no xD, porque si voy a ser precisa, tendría que app este proc a todos xD y no lo hago porque práticamente sería en vano, puesto que los otros los debe eliminar el usuario...
        this.titulosRegistrados.add(titulo)
    }

   fun existeAtributo(nombreAtributo:String):Int{
       atributoHallado = null
       var vecesHallado:Int = 0

        for (atributo in this.atributos){
            if(atributo.simbolo.value.toString() == nombreAtributo){
                vecesHallado++
                if(vecesHallado==1){
                    atributoHallado = atributo//solo la 1er aparición porque para qué voy a estar app las demás verificaciones si en primer lugar todos los datos extra tendrá que eliminarlos...
                }//pero no paro porque debo recopilar el # de apariciones, aunque creo que por la lógica que estas usando bastaría con llegar a dos...
            }
        }
        return vecesHallado
    }

    fun getAtributoHallado():Atributo{
        return atributoHallado!!
    }//puesto que cuando se utilice, está asegurado  [por la revisión del #apariciones] que el valor hallado no fue null xD

    //no es necesario colocar estos métodos, pero para que no olvides qué cosas deberías poder hacer con ellos xD
    fun getTipoGrafica():String{
        return this.tipoGrafica!!.value.toString()
    }//nunca será null, porque en caso de que no apareciera se iría a la prod de error de las RP de la defi de una graf

    fun getLineaDefinicionGrafica():Int{
        return this.tipoGrafica!!.left
    }//puede que no sea tan exacto, porque def podría estar líneas antes, pero al menos guía un poco xD

    fun getPalabrasReservadas():Array<String>{
        return this.palabrasReservadas
    }

    fun getAtributosBarras():Array<String>{
        return this.atributos_Barras
    }

    fun getAtributosPie():Array<String>{
       return this.atributos_Pie
    }

    fun getTitulosRegistrados():ArrayList<ContenidoCadena>{
        return this.titulosRegistrados
    }

    //se debe invocar cuando surga un error que impida llegar a la axn de la RP que contiene el tipo de gráfica para verificar si debe o no crearla
    fun clearTemp(){
        this.tipoGrafica = null
        this.atributos.clear()
    }

    //titulos no es necesario limpiarlo porque se necesitará que esté llena la lista de ellos hasta que se termine de
    //analizar todas las definiciones declaradas, y cuando eso suceda, al nec la TS para un nuevo análisis,
    //se estaría tratando con otro instancia xD
}