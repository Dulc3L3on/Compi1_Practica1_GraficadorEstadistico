package com.example.graficadorestadistico.ui.Contenedor

import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Reportes.Reporte
import Backend.Objetos.Reportes.ReporteError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.graficadorestadistico.R
import com.example.graficadorestadistico.databinding.FragmentContenedorBinding
import com.example.graficadorestadistico.ui.Adapter.PagerAdapter
import com.example.graficadorestadistico.ui.Graficadora.PrincipalFragment
import com.example.graficadorestadistico.ui.Reportes.ReportesFragment
import com.example.graficadorestadistico.ui.Resultados.ResultadosFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class ContenedorFragment(): Fragment() {
    private lateinit var fragments:ArrayList<Fragment>
    private lateinit var titulos:ArrayList<String>
    private var inicializado:Boolean = false

    lateinit var principalFragment:PrincipalFragment

    private lateinit var tabs: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: PagerAdapter

    private var _binding: FragmentContenedorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ContenedorViewModel::class.java)

        _binding = FragmentContenedorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tabs = root.findViewById(R.id.menutabs)
        viewPager = root.findViewById(R.id.pagetabs)

        inicializarTabs()

  //      tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_menu_gallery).setText("Graficadora"))
 //       tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_menu_gallery).setText("Graficadora"))

 //       adapter = PagerAdapter(requireActivity().supportFragmentManager)
//        viewPager.adapter = this.adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.setOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position;
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //viewPager.setCurrentItem(tab!!.getPosition());
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //viewPager.setCurrentItem(tab!!.getPosition());
            }
        })

        this.viewPager!!.adapter = this.adapter
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabs))

        return root
    }

    private fun inicializarTabs(){
        //lo qe se hacía antes, era addTab
        //crear el adapter [que no heredaba del State]
        //setear la tab de PrincipalFragment adapter!!.setTab(PrincipalFragment() as Fragment, "Graficadora")
        //Agregar el adapter en el viewPager:  this.viewPager!!.adapter = this.adapter

        adapter = PagerAdapter(requireActivity().supportFragmentManager)
        adapter.setTab(PrincipalFragment() as Fragment, "Graficadora")
        adapter.setTab(ResultadosFragment() as Fragment, "Graficas")
        adapter.setTab(ReportesFragment() as Fragment, "Resumen")

        //de alguna no jala las tabs si las clases de los fragment, tienen algo más que no sea el OnCreate y el OnCreateView... lo digo por lo que sucedió con resultado... [que al crear una clase que no tuviera los parámetros para tener el método static de createInstance, ahí si me jalo la app...
        tabs.addTab(tabs.newTab().setIcon(R.drawable.vector_chart).setText("Graficadora"))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.vector_logo).setText("Graficas"))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.vector_reportes).setText("Resumen"))
//      tabs.addTab(tabs.newTab().setIcon(R.drawable.vector_reportes_error).setText("Errores"))

//        this.principalFragment = PrincipalFragment()

//        adapter!!.setTab(PrincipalFragment() as Fragment, "Graficadora")

//        this.principalFragment!!.setContenedorFragments(this)
    }

    //estos los invocará el principlaFragment
    fun setTabsError(errores:ArrayList<ReporteError>){//quizá podríamos add un parám que que especifique el tipo de pestañas que se debe enviar... así se envía una lista nueva y por lo tanto no habrá problemas con el manejo de instancias... [quiere decir que de aquí se tendrían que obtner las instancias para que se muestre lo que se debe
        deleteTabs()
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_menu_gallery).setText("Errores"))

        this.adapter!!.resetList()
        this.adapter!!.setTab(this.principalFragment as Fragment, "Graficadora")
        this.adapter!!.setTab( ReportesFragment() as Fragment, "Errores")
    }

    fun setTabsResultado(graficas:ArrayList<Grafica>, resultados:ArrayList<Reporte>){//quizá podríamos add un parám que que especifique el tipo de pestañas que se debe enviar... así se envía una lista nueva y por lo tanto no habrá problemas con el manejo de instancias... [quiere decir que de aquí se tendrían que obtner las instancias para que se muestre lo que se debe
        deleteTabs()

        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_menu_gallery).setText("Graficas"))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_menu_gallery).setText("Resumen"))

        this.adapter!!.resetList()
        this.adapter!!.setTab(this.principalFragment as Fragment, "Graficadora")
        this.adapter!!.setTab(ContenedorFragment() as Fragment, "Graficas")
        this.adapter!!.setTab( ReportesFragment() as Fragment, "Resumen")
    }

    private fun deleteTabs(){
        for (x in 1..(tabs.tabCount-1)){
            tabs.removeTabAt(x)//si no borra entonces coloca el inicio como 0 xD
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}