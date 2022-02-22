package com.example.graficadorestadistico.ui.Graficadora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graficadorestadistico.databinding.FragmentPrincipalBinding
import com.example.graficadorestadistico.ui.Contenedor.ContenedorFragment

class PrincipalFragment : Fragment() {
    private lateinit var contenedorFragments: ContenedorFragment

    private var _binding: FragmentPrincipalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(PrincipalViewModel::class.java)

        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setContenedorFragments(contenedorFragments:ContenedorFragment){
        this.contenedorFragments = contenedorFragments
    }
}