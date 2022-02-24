package com.example.graficadorestadistico.ui.Reportes

import Backend.Objetos.Reportes.Reporte
import Backend.Objetos.Reportes.ReporteError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.graficadorestadistico.R

class ReportesFragment() : Fragment() {
    private lateinit var resultados:ArrayList<Reporte>
    private lateinit var errores:ArrayList<ReporteError>

    //si creo una clase que herede de View [es decir creo un ienzo, tendré que enviar estos datos allá, simi a como lo harás con R// que ya tiene un lienzo xD

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reportes, container, false)
    }

    fun addListaResultados(resumen:ArrayList<Reporte>){
        this.resultados = resumen
    }

    fun addListaErrores(errores:ArrayList<ReporteError>){
        this.errores = errores
    }
}