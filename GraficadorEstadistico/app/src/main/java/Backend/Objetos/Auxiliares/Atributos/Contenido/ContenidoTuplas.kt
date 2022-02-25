package Backend.Objetos.Auxiliares.Atributos.Contenido

class ContenidoTuplas(linea: Int, columna: Int) :Contenido(linea, columna) {
    lateinit var lineaTuplas:IntArray
    lateinit var columnaTuplas:IntArray
    lateinit var tuplas:Array<DoubleArray>//se supone que debería crear un arreglo de arreglos de double... pero si la conversión que se hará desde CUP, para setear el contenido no devuelve lo que espero, entonces que se quede como ArrayList xD
    //se quedará como double puesto que ese es el dato que tiene el T NUMERO, bien podría hacer una conversión a todos ellos a un int, pero al ser así no podría dar el msje "no se admite número decimales como posicion", o tb podría efectura la evialuación allá en la gramática, pero mejor que se quede en la verificación de errores extra xD

    constructor(lineaTuplas: IntArray, columnaTuplas:IntArray, tuplas:Array<DoubleArray>)
            : this(-1, -1) {//le mando -1, porque esas no son las que vamos a utilizar, sino las del listado...

        this.tuplas = tuplas
        //estas se puede obtener en la RP de TUPLAS que mo tiene la recursi [la que tiene el formato para una sola tupla...
        this.lineaTuplas = lineaTuplas
        this.columnaTuplas = columnaTuplas
    }
}