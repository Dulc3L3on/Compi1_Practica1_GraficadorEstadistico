package com.example.graficadorestadistico.ui.Reportes

import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Reportes.Reporte
import Backend.Objetos.Reportes.ReporteError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.graficadorestadistico.R

class ReportesFragment() : Fragment() {
    private lateinit var tvw_area_reportes:TextView

    private var hubieronErrores:Boolean = true
    private lateinit var reporteGraficasDefinidas:IntArray
    private lateinit var reporteOperaciones:ArrayList<Reporte>
    private lateinit var errores:ArrayList<ReporteError>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recibirInformacion(savedInstanceState!!)//puesto que por la forma de trabajo, siempre le enviaremos un bundle xD
    }
    //si creo una clase que herede de View [es decir creo un ienzo, tendré que enviar estos datos allá, simi a como lo harás con R// que ya tiene un lienzo xD

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view:View = inflater.inflate(R.layout.fragment_reportes, container, false)

        this.tvw_area_reportes = view.findViewById(R.id.tvw_reportes)

        return view
    }

    private fun recibirInformacion(bundle:Bundle){
        setFragmentResultListener("resultados") { resultados, bundle ->
            hubieronErrores = bundle.getBoolean("errores")

            if(!hubieronErrores){
                this.reporteGraficasDefinidas = bundle.getSerializable("reporteGraficasDefinidas") as IntArray
                this.reporteOperaciones = bundle.getSerializable("reporteOperaciones") as ArrayList<Reporte>

                //aquí el llamado al método que se setear estos dos listados a la tabla
            }else{
                this.errores = bundle.getSerializable("reporteErrores") as ArrayList<ReporteError>

                //aquí llamado al método que se encarga de setear el listado de errores a la tabla...
            }
        }

    }

}