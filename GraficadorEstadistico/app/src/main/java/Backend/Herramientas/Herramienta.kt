package Backend.Herramientas

class Herramienta {

    fun converToIntArray(listaEnteros:ArrayList<Integer>):IntArray{
        var arregloEnteros:IntArray = IntArray(listaEnteros.size)

        for(elemento in listaEnteros.indices){
            arregloEnteros[elemento] = listaEnteros[elemento].toInt()
        }
        return arregloEnteros
    }

    fun converToDoubleArray(listaDoubles:ArrayList<Double>):DoubleArray{
        var arregloDoubles:DoubleArray = DoubleArray(listaDoubles.size)

        for(elemento in listaDoubles.indices){
            arregloDoubles[elemento] = listaDoubles[elemento].toDouble()
        }
        return arregloDoubles
    }

    fun converToArrayListOfDoubleArray(listaDoubleArrays:ArrayList<DoubleArray>):Array<DoubleArray>{
        var arregloDoubleArrays:Array<DoubleArray> = Array(listaDoubleArrays.size){DoubleArray(2)}

        for(elemento in listaDoubleArrays.indices){
            arregloDoubleArrays[elemento] = listaDoubleArrays[elemento]//se obtiene el arreglo de doubles de tamaño 2
        }
        return arregloDoubleArrays
    }
}