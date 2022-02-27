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
        if(this.reporteOperaciones.isNotEmpty()){
            generarReporte_incidenciaDeOperaciones()
        }
    }

    private fun generarReporte_grafciasDefinidas(){//este siempre existirá, pues de no ser así no se exe algo xD
        addSeparador("Gráficas Definidas")

        for (elemento in 0 until (this.reporteGraficasDefinidas.size +1)){
            val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
            val numeroReporte:TextView = TextView(context)
            val tipoGrafica:TextView = TextView(context)
            val numeroDefiniciones:TextView = TextView(context)

            if(elemento > 0){
                numeroReporte.text = elemento.toString()
                tipoGrafica.text = (if(elemento == 1) "Barras" else "Pie")
                numeroDefiniciones.text = this.reporteGraficasDefinidas[elemento-1].toString()
            }else{//columnas
                numeroReporte.text = "No."
                tipoGrafica.text = "Tipo de gráfica"
                numeroDefiniciones.text = "Veces definida"
            }

            fila.addView(numeroReporte)
            fila.addView(tipoGrafica)
            fila.addView(numeroDefiniciones)

            tableLayout.addView(fila)
        }
    }

    private fun generarReporte_incidenciaDeOperaciones(){
        addSeparador("Operaciones Ariteméticas")

        if(this.reporteOperaciones.isNotEmpty()){//puesto que no necesariamente debe existir, depende del user xD
            for (elemento in 0 until (this.reporteOperaciones.size +1)){
                val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
                val linea:TextView = TextView(context)
                val columna:TextView = TextView(context)
                val tipoOperacion:TextView = TextView(context)
                val contexto:TextView = TextView(context)

                if(elemento > 0){
                    linea.text = this.reporteOperaciones.get(elemento-1).linea.toString()
                    columna.text = this.reporteOperaciones.get(elemento-1).columna.toString()
                    tipoOperacion.text = this.reporteOperaciones.get(elemento-1).lexema
                    contexto.text = this.reporteOperaciones.get(elemento-1).descripcion
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
    }

    private fun generarReportes_Errores(){
        addSeparador("Errores")

        for (elemento in 0 until (this.errores.size+1)){
            val fila:TableRow = LayoutInflater.from(context).inflate(R.layout.table_row, null, false) as TableRow
            val linea:TextView = TextView(context)
            val columna:TextView = TextView(context)
            val tipoError:TextView = TextView(context)
            val error:TextView = TextView(context)
            val descripcion:TextView = TextView(context)

            if(elemento > 0){//no olvides restarle 1 al índice xD
                linea.text = this.errores.get(elemento-1).linea.toString()
                columna.text = this.errores.get(elemento-1).columna.toString()
                tipoError.text = this.errores.get(elemento-1).tipo
                error.text = this.errores.get(elemento-1).lexema
                descripcion.text = this.errores.get(elemento-1).descripcion
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