package com.example.graficadorestadistico.ui.Reportes

import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Reportes.Reporte
import Backend.Objetos.Reportes.ReporteError
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import com.example.graficadorestadistico.R

class ReportesFragment() : Fragment() {
    private lateinit var tableLayout:TableLayout

    private var hubieronErrores:Boolean = true
    private lateinit var reporteGraficasDefinidas:IntArray
    private lateinit var reporteOperaciones:ArrayList<Reporte>
    private lateinit var errores:ArrayList<ReporteError>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if(savedInstanceState != null){
            recibirInformacion()//puesto que por la forma de trabajo, siempre le enviaremos un bundle xD
//        }
    }
    //si creo una clase que herede de View [es decir creo un ienzo, tendré que enviar estos datos allá, simi a como lo harás con R// que ya tiene un lienzo xD

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view:View = inflater.inflate(R.layout.fragment_reportes, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.tableLayout = view.findViewById(R.id.table_reportes)
    }

    private fun recibirInformacion(){
        Log.i("Recepcion_reportes", "iniciada")
        parentFragmentManager.setFragmentResultListener("resultados_reportes", this, FragmentResultListener() { resultados, bundle ->
            hubieronErrores = bundle.getBoolean("errores")
            Log.i("Recepcion_resultados", "terminada")

            if(!hubieronErrores){
                this.reporteGraficasDefinidas = bundle.getSerializable("reporteGraficasDefinidas") as IntArray
                this.reporteOperaciones = bundle.getSerializable("reporteOperaciones") as ArrayList<Reporte>

                generarReporte_Resultados()
            }else{
                this.errores = bundle.getSerializable("reporteErrores") as ArrayList<ReporteError>

                generarReportes_Errores()
            }

            Log.i("Seteo_tipoReportes", "acabado")
        })
    }

    private fun addSeparador(tipoReporte:String){
        val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
        val tvw_tipoReporte:TextView = TextView(context)
        tvw_tipoReporte.text = tipoReporte

        fila.addView(tvw_tipoReporte)
        tableLayout.addView(fila)
    }

    private fun generarReporte_Resultados(){
        generarReporte_grafciasDefinidas()
        generarReporte_incidenciaDeOperaciones()
    }

    private fun generarReporte_grafciasDefinidas(){
        addSeparador("Gráficas Definidas")

        for (elemento in (this.reporteGraficasDefinidas.indices)+1){
            val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
            val numeroReporte:TextView = TextView(context)
            val tipoGrafica:TextView = TextView(context)
            val numeroDefiniciones:TextView = TextView(context)

            if(elemento > 0){
                numeroReporte.text = elemento.toString()
                tipoGrafica.text = (if(elemento == 0) "Barras" else "Pie")
                numeroDefiniciones.text = this.reporteGraficasDefinidas[elemento].toString()
            }else{//columnas
                numeroReporte.text = "No."
                tipoGrafica.text = "Tipo de gráfica"
                numeroDefiniciones.text = "Numero de definiciones"
            }

            fila.addView(numeroReporte)
            fila.addView(tipoGrafica)
            fila.addView(numeroDefiniciones)

            tableLayout.addView(fila)
        }
    }

    private fun generarReporte_incidenciaDeOperaciones(){
        addSeparador("Operaciones Ariteméticas")

        val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
        val tipoReporte:TextView = TextView(context)
        tipoReporte.text = "Errores"

        fila.addView(tipoReporte)
        tableLayout.addView(fila)

        for (elemento in (this.reporteOperaciones.indices) +1){
            val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
            val linea:TextView = TextView(context)
            val columna:TextView = TextView(context)
            val tipoOperacion:TextView = TextView(context)
            val contexto:TextView = TextView(context)

            if(elemento > 0){
                linea.text = this.reporteOperaciones.get(elemento).linea.toString()
                columna.text = this.reporteOperaciones.get(elemento).columna.toString()
                tipoOperacion.text = this.reporteOperaciones.get(elemento).lexema
                contexto.text = this.reporteOperaciones.get(elemento).descripcion
            }else{
                linea.text = "Línea"
                columna.text = "Columna"
                tipoOperacion.text = "Operación"
                contexto.text = "Ocurrencia"
            }

            fila.addView(linea)
            fila.addView(columna)
            fila.addView(tipoOperacion)
            fila.addView(contexto)

            tableLayout.addView(fila)
        }
    }

    private fun generarReportes_Errores(){
        addSeparador("Errores")

        for (elemento in (this.errores.indices) +1){
            val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
            val linea:TextView = TextView(context)
            val columna:TextView = TextView(context)
            val tipoError:TextView = TextView(context)
            val error:TextView = TextView(context)
            val descripcion:TextView = TextView(context)

            if(elemento > 0){
                linea.text = this.errores.get(elemento).linea.toString()
                columna.text = this.errores.get(elemento).columna.toString()
                tipoError.text = this.errores.get(elemento).tipo
                error.text = this.errores.get(elemento).lexema
                descripcion.text = this.errores.get(elemento).descripcion
            }else{
                linea.text = "Línea"
                columna.text = "Columna"
                tipoError.text = "Tipo"
                error.text = "Error"
                descripcion.text = "Descripción"
            }

            fila.addView(linea)
            fila.addView(columna)
            fila.addView(tipoError)
            fila.addView(error)
            fila.addView(descripcion)

            tableLayout.addView(fila)
        }
    }

}