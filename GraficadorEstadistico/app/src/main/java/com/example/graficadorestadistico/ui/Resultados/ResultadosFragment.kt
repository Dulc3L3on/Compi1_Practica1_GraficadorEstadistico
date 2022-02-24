package com.example.graficadorestadistico.ui.Resultados

import Backend.Objetos.Graficas.Grafica
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.graficadorestadistico.R

class ResultadosFragment:Fragment() {
    private lateinit var graficas:ArrayList<Grafica>

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resultado, container, false)
    }

    fun addGraficas(graficas:ArrayList<Grafica>){
        this.graficas = graficas
    }



}