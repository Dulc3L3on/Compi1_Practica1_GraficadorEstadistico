package com.example.graficadorestadistico.ui.Graficadora

import Backend.Analizadores.AnalizadorSemantico
import Backend.Analizadores.Lexer
import Backend.Analizadores.Parser
import Backend.Manejadores.ManejadorErroresExtra
import Backend.Manejadores.ManejadorGraficacion
import Backend.Manejadores.ManejadorReportes
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graficadorestadistico.R
import com.example.graficadorestadistico.databinding.FragmentPrincipalBinding
import com.example.graficadorestadistico.ui.Contenedor.ContenedorFragment
import java.io.StringReader

class PrincipalFragment : Fragment() {
    //variables para realizar el análisis
    private lateinit var lexer: Lexer
    private lateinit var manejadorErroresExtra: ManejadorErroresExtra
    private lateinit var parser:Parser
    private lateinit var manejadorReportes:ManejadorReportes
    private lateinit var analizadorSemantico: AnalizadorSemantico
    private lateinit var manejadorGraficacion: ManejadorGraficacion

    private lateinit var contenedorFragments: ContenedorFragment
    private lateinit var editText: EditText
    private lateinit var boton_graficar: Button

    private var _binding: FragmentPrincipalBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val homeViewModel = ViewModelProvider(this).get(PrincipalViewModel::class.java)

        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        val root: View = binding.root//creo que no hacía esto [obtener root] en el fragment que había creado para que fuera el contenendor, por eso me daba error...

        editText = root.findViewById(R.id.txt_input)
        boton_graficar = root.findViewById(R.id.btn_graficar)

        boton_graficar.setOnClickListener{ view ->
            analizarEntrada(editText.text.toString())
            ejecutarAcciones()
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun analizarEntrada(textoEntrada:String){
        val reader:StringReader = StringReader(textoEntrada)

        lexer = Lexer(reader)
        parser = Parser(lexer)
        inicializarManejadores(lexer.manejadorErroresExtra)

        parser.parse()
    }

    private fun inicializarManejadores(manejadorErroresExtra: ManejadorErroresExtra){
        this.manejadorErroresExtra = manejadorErroresExtra
        this.manejadorGraficacion = ManejadorGraficacion(manejadorErroresExtra)
        this.manejadorReportes = this.manejadorErroresExtra.getManejadorReportes()
        this.analizadorSemantico = this.manejadorErroresExtra.getAnalizadorSemantico()

        parser.inicializarManejadores(lexer.manejadorErroresExtra, manejadorGraficacion)
    }

    private fun ejecutarAcciones(){
        if(this.manejadorGraficacion.getListaEjecucion().isNotEmpty()){
            //se construye el último reporte faltante
            this.manejadorReportes.reportarCantidadGraficas(this.manejadorGraficacion.getGraficasDefinidas())

            //se setea una var para que cuadno se de click en la tab setee los datos que se deben visualizar [una imagen o algo que diga que no se generó algo debido a los errores
            //o se exe el Intent, no sé tengo que ver cómo enviar datos cuando se trata de un nav tab
            //bueno ahorita se me ocurre que quizá se deba invocar los setter de cada fragment para enviar las listas
            //entocnes cada fragment debe tener dos setter, uno para datos y otro para cb el valor de la var que le indique que no debe mostrar algo, solo info que no hay nada porque... ya la epxlica xD

            //De alls modos debes ver cómo enviar datos con las tab, si se puede con los setter nice xD, pero de todos modos míralo porque debes hacer que se mire el contenido solo cuando se vaya al fragment en cuestión, no hacer que se sobreponga!!!
        }else{
            //se usa el setter para enviar los rep de error
            //se usan los setter de los otros dos fragment para decir que no deben mostrar algo xD, sino solo la explic de por qué están mostrando nada xD
        }
    }

    fun setContenedorFragments(contenedorFragments:ContenedorFragment){
        this.contenedorFragments = contenedorFragments
    }
}