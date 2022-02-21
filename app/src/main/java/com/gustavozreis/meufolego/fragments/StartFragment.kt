package com.gustavozreis.meufolego.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gustavozreis.meufolego.databinding.FragmentStartBinding
import com.gustavozreis.meufolego.viewmodel.TimeViewModel

class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null // vinculação de visualização

    private val timeViewModel: TimeViewModel by activityViewModels() // instância do viewModel

    var cronometro: Chronometer? = null // instância do cronometro

    // vinculacao dos views
    var btnBotao: ImageButton? = null
    var tvTextoInstrucao: TextView? = null
    var textoTeste: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding?.root // vincula o fragment ao layout
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cronometro = binding?.crTempoPassado

        btnBotao = binding?.ibImageButton
        tvTextoInstrucao = binding?.tvInstrucao
        textoTeste = binding?.tvTextoTeste

        // define as ações a serem tomadas se o botao esta pressionado ou não
        btnBotao?.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == ACTION_DOWN) iniciaCronometro()
            if (motionEvent.action == ACTION_UP) paraCronometro()
            true
        }
    }

    /*
    Função que inicia o cronometro
     */
    fun iniciaCronometro() {
        cronometro?.base = SystemClock.elapsedRealtime()
        cronometro?.start()
        tvTextoInstrucao?.text = "Segure sua respiração!!"
    }

    /*
    Função que pausa o cronometro
     */
    fun paraCronometro() {
        cronometro?.stop()
        textoTeste?.text = cronometro?.text.toString()
        tvTextoInstrucao?.text = "Aperte e segure para iniciar a contagem."
    }

    // Retorna o valor do binding para nulo
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}



