package com.gustavozreis.meufolego.fragments

import android.annotation.SuppressLint
import android.app.usage.UsageEvents
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gustavozreis.meufolego.databinding.FragmentStartBinding
import com.gustavozreis.meufolego.viewmodel.TimeViewModel

class StartFragment: Fragment() {

    private var binding: FragmentStartBinding? = null // vinculação de visualização

    private val timeViewModel: TimeViewModel by activityViewModels() // instância do viewModel

    var cronometro: Chronometer? = null

    var botao: ImageButton? = null


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

        botao = binding?.ibImageButton

        botao?.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == ACTION_DOWN) iniciaCronometro()
            if (motionEvent.action == ACTION_UP) paraCronometro()
            true
        }


    }


    /*
    Função que inicia o cronometro
     */
    fun iniciaCronometro() {
        cronometro?.start()
    }

    /*
    Função que pausa o cronometro
     */
    fun paraCronometro() {
        cronometro?.stop()
    }


    // Retorna o valor do binding para nulo
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}



