package Backend.Manejadores

import Backend.Objetos.Graficas.Barras
import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Graficas.Pie
import Backend.Objetos.Reportes.Reporte
import Backend.Objetos.Reportes.ReporteError

class ManejadorReportes {
    private var listaReportesOperaciones = ArrayList<Reporte>()
    private var listadoErrores = ArrayList<ReporteError>()
    private var cantidadGraficas:IntArray = IntArray(2){ i -> 0}//0 -> Barras, 1 -> Pie

    fun reportarOperacion(reporteOperacion:Reporte){
        listaReportesOperaciones.add(reporteOperacion)
    }

    fun reportarError(reporteError:ReporteError){
        listadoErrores.add(reporteError)
    }

    fun reportarCantidadGraficas(graficas:ArrayList<Grafica>){
        for (item in graficas){
            when(item){
                is Barras -> cantidadGraficas[0]++
                is Pie -> cantidadGraficas[1]++
            }
        }
    }

    fun hubieronErrores():Boolean{
        return (this.listadoErrores.size>0)
    }

    fun getListaReporteOperaciones():ArrayList<Reporte>{
        return listaReportesOperaciones
    }

    fun getListaErrores():ArrayList<ReporteError>{
        return listadoErrores
    }

    fun getCantidadGraficas():IntArray{//0-> barras, 1-> pie... solo le colocas los corchetes donde lo vayas a invocar y listo xD no tendrás que crear vars extra xD
        return cantidadGraficas
    }
}