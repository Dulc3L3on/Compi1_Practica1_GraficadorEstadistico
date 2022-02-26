package com.example.graficadorestadistico.ui.Resultados

import Backend.Objetos.Graficas.Grafica
import android.content.Context
import android.graphics.Canvas
import android.view.View

class ResultadoViewModelViewModel(contexto: Context, graficas:ArrayList<Grafica>): View(contexto) {
    private var graficas:ArrayList<Grafica> = graficas

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)



    }

    fun recibirDatos(){

    }//se empleará si es que da problemas que tenga parámetros extra el constructor, así como sucede con los fragments...
}