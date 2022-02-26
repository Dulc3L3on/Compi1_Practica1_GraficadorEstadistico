package com.example.graficadorestadistico.ui.Resultados

import Backend.Objetos.Graficas.Grafica
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.graficadorestadistico.R

class ResultadosFragment:Fragment() {
    private lateinit var tvw_areaResultado:TextView

    private var hubieronErrores:Boolean = true
    private lateinit var graficas:ArrayList<Grafica>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recibirInformacion(savedInstanceState!!)//puesto que por la forma de trabajo, siempre le enviaremos un bundle xD
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_resultado, container, false)

        this.tvw_areaResultado = view.findViewById(R.id.tvw_resultado)

        return view
    }

    private fun recibirInformacion(bundle:Bundle){
        setFragmentResultListener("resultados") { resultados, bundle ->
            hubieronErrores = bundle.getBoolean("errores")

            if(!hubieronErrores){
                this.tvw_areaResultado.text = ""
                this.graficas = bundle.getSerializable("graficas") as ArrayList<Grafica>

                //aquí el llamado al método que se encargue de graficar...
            }else{
                this.tvw_areaResultado.text = "Nada que graficar"
            }
        }
    }
}