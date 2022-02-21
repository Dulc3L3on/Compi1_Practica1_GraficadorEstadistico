package Backend.Manejadores

import Backend.Objetos.Graficas.Grafica

class ManejadorGraficacion(manejardorErroresExtra:ManejadorErroresExtra) {
    private var manejadorErroresExtra:ManejadorErroresExtra = manejardorErroresExtra //para hacer la revisión de si se debe graficar o no... [Entonces solo a este se invocará en el parser...]
    private var graficas:ArrayList<Grafica> = ArrayList<Grafica>()

    fun analizarParametrosGrafica(tipo:String){

    }
}