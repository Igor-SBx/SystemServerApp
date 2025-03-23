package com.example.systemserver.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.systemserver.databinding.FragmentHomeBinding
import kotlin.math.round

// Lógica do Fragment que possue a lógica de controle de brilho através do WindowManager
class HomeFragment : Fragment() {

    // Referência do binding para o processo de inflate
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Valor inicial do brilho
    var brightnessValue = 100

    // Evento disparado quando a View é criada
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Home Fragment", "onCreateView chamado")

        // Processo de criação da View
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Referência para activity
        val activity = getActivity()
        if (activity != null) {
            val context = activity.applicationContext

            // Obtenção das Window atual da MainActivity para interagir com a API do WindowManager
            val window = activity.window

                // Lógica de controle de briho

                // Evento de click para o botão de reduzir briho
                binding.decreaseBrightness.setOnClickListener{

                    // Condicional para o brilho não ser setado para valores negativos
                    if (brightnessValue >= 11) {
                        brightnessValue -= 10

                        // Novo valor de brilho
                        val newBrightness = brightnessValue.toDouble() / 100

                        // Interação com a Window API para controle de brilho
                        val params: WindowManager.LayoutParams = window.attributes
                        params.screenBrightness = newBrightness.toFloat()

                        // Configuração do novo valor de brilho
                        window.attributes = params

                        // Demonstração do parâmetro de brilho sendo alterado
                        // Recomendável demonstração em dispositivo físico, já que a AVD não configura essa propriedade corretamente
                        Log.d("Brilho atual", params.screenBrightness.toString())

                        // Output para o usuário sobre a configuração do brilho
                        Toast.makeText(
                            context, "Briho reduzido para : ${round(newBrightness * 100)}%",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                // Evento de click para o botão de aumentar briho
                binding.increaseBrightness.setOnClickListener{

                    // Condicional para o brilho não ser setado para valores acima de 100%
                    if (brightnessValue <= 90) {
                        brightnessValue += 10

                        // Novo valor de brilho
                        val newBrightness = brightnessValue.toDouble() / 100

                        // Interação com a Window API para controle de brilho
                        val params: WindowManager.LayoutParams = window.attributes
                        params.screenBrightness = newBrightness.toFloat()

                        // Configuração do novo valor de brilho
                        window.attributes = params

                        // Demonstração do parâmetro de brilho sendo alterado
                        // Recomendável demonstração em dispositivo físico, já que a AVD não configura essa propriedade corretamente
                        Log.d("Brilho atual", params.screenBrightness.toString())

                        // Output para o usuário sobre a configuração do brilho
                        Toast.makeText(
                            context, "Brilho aumentado para : ${round(newBrightness * 100)}%",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        // Interação com a ViewModel para configurar a TextView
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Binding da TextView
        val textView: TextView = binding.textHome

        // Configuração da propriedade text
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

    // Evento chamado na destruição da View
    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("Home Fragment", "onDestroyView chamado")

        // Destruição do binding para evitar Memory Leaks
        _binding = null
    }
}