package com.example.graficadorestadistico.ui.Graficadora

import Backend.Analizadores.AnalizadorSemantico
import Backend.Analizadores.Lexer
import Backend.Analizadores.Parser
import Backend.Manejadores.ManejadorErroresExtra
import Backend.Manejadores.ManejadorGraficacion
import Backend.Manejadores.ManejadorReportes
import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Reportes.Reporte
import Backend.Objetos.Reportes.ReporteError
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var tvw_ubicacion:TextView

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
        tvw_ubicacion = root.findViewById(R.id.tvw_posicion)

        editText.setOnClickListener { view ->
            mostrarPosicionCareta()
        }

        editText.addTextChangedListener( object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                inicializarUbicacion()
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mostrarPosicionCareta()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })//hizo falta el keyUP, por si acaso conectan un teclado [o fuera una VM xD, como es el caso tuyo (lo digo por el emu xD)]

        boton_graficar.setOnClickListener{ view ->
            analizarEntrada(editText.text.toString())
            ejecutarAcciones()
        }//en dado caso no funcionara, puede que sea porque no colocaste esto  [y las declaraciones de las var] en el onViewCreate()...

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

    //se emplea en el listener del botón
    private fun ejecutarAcciones(){
        if(this.manejadorGraficacion.getListaEjecucion().isNotEmpty()){
            this.manejadorReportes.reportarCantidadGraficas(this.manejadorGraficacion.getGraficasDefinidas())//se construye el último reporte faltante

            setResultados(this.manejadorGraficacion.getListaEjecucion(), manejadorReportes.getListaReporteOperaciones(),
                manejadorReportes.getCantidadGraficas())
            //se setea una var para que cuadno se de click en la tab setee los datos que se deben visualizar [una imagen o algo que diga que no se generó algo debido a los errores
            //o se exe el Intent, no sé tengo que ver cómo enviar datos cuando se trata de un nav tab
            //bueno ahorita se me ocurre que quizá se deba invocar los setter de cada fragment para enviar las listas
            //entocnes cada fragment debe tener dos setter, uno para datos y otro para cb el valor de la var que le indique que no debe mostrar algo, solo info que no hay nada porque... ya la epxlica xD

            //De alls modos debes ver cómo enviar datos con las tab, si se puede con los setter nice xD, pero de todos modos míralo porque debes hacer que se mire el contenido solo cuando se vaya al fragment en cuestión, no hacer que se sobreponga!!!
        }else{
            setErrores(this.manejadorReportes.getListaErrores())
            //se usa el setter para enviar los rep de error
            //se usan los setter de los otros dos fragment para decir que no deben mostrar algo xD, sino solo la explic de por qué están mostrando nada xD
        }
    }

    private fun setResultados(graficas:ArrayList<Grafica>, reporteOperaciones: ArrayList<Reporte>,
                              reporteGraficasDefinidas:IntArray){
        var bundle:Bundle = Bundle()

        bundle.putSerializable("graficas", graficas)
        bundle.putSerializable("reporteOperaciones", reporteOperaciones)
        bundle.putSerializable("reporteGraficasDefinidas", reporteGraficasDefinidas)
        bundle.putBoolean("errores", false)

        parentFragmentManager.setFragmentResult("resultados", bundle)
    }

    private fun setErrores(errores:ArrayList<ReporteError>){
        var bundle:Bundle = Bundle()

        bundle.putSerializable("reporteErrores", errores)
        bundle.putBoolean("errores", true)

        parentFragmentManager.setFragmentResult("resultados", bundle)//que irá a pasr por haberle dejado el mismo nombre?? se add a los demás y en el caso del boolean, reemplazaría o devolvera un error por el simple hecho de llamarse igual, o no sucederá nada puesto que ese bundle ya desapareció...???
    }

    //métodos para interfaz
    private fun inicializarUbicacion(){
        var ubicacion:String = "linea: 0 columna:0"
        this.tvw_ubicacion.text = ubicacion
    }

    private fun mostrarPosicionCareta(){
        var columna:Double = editText.selectionStart.toDouble()
        var lineCount:Int = editText.lineCount
        var linea:Double = (lineCount - ((editText.length()-columna)/37))//aquí posee decimales...

        val ubicacion:String = "linea: "+ (if(linea%1>0)(linea+1).toInt() else linea.toInt()) + " columna: "+ columna.toInt()

        this.tvw_ubicacion.text = ubicacion
    }

    fun setContenedorFragments(contenedorFragments:ContenedorFragment){//ya no lo miro necesario xD
        this.contenedorFragments = contenedorFragments
    }
}