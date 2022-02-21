package com.example.graficadorestadistico.ui.ManualUso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graficadorestadistico.databinding.FragmentManualUsoBinding

class ManualUsoFragment : Fragment() {

    private var _binding: FragmentManualUsoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(ManualUsoViewModel::class.java)

        _binding = FragmentManualUsoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it//quiere decir que en estos textView que trae por defecto es donde se puede setear la info a mostrar que se almacena en el modelo [la otra clas [la viewModel]
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}