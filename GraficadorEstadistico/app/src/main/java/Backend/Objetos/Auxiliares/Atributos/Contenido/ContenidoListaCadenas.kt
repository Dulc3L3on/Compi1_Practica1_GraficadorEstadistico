package Backend.Objetos.Auxiliares.Atributos.Contenido

class ContenidoListaCadenas(linea: Int, columna: Int) :Contenido(linea, columna) {
    lateinit var listaCadenas:Array<String>//para no tener que estar usando Listas, puesto que no van a cambiar...

    constructor(linea: Int, columna:Int, listaCadenas:Array<String>)//porque no dejaste esto en el constructor original :v??? con cuplas y cListaNumeros lo entiendo porque no req enviar la línea y col, pero aquí por qué??? quizá por si acaso cb de opinion y terminas enviando el número de fila y col de cada cadena, aunque la verdad eso NO SERIVIRÍA para nada... solo sería trabajo en vano...
            : this(linea, columna) {

        this.listaCadenas = listaCadenas
    }
}