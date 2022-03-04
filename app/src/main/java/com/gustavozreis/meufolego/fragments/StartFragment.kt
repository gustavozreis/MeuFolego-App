package com.gustavozreis.meufolego.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gustavozreis.meufolego.R
import com.gustavozreis.meufolego.TimeApplication
import com.gustavozreis.meufolego.databinding.FragmentStartBinding
import com.gustavozreis.meufolego.viewmodel.TimeViewModel
import com.gustavozreis.meufolego.viewmodel.TimeViewModelFactory

class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null // vinculação de visualização

    var cronometro: Chronometer? = null // instância do cronometro

    // vinculacao dos views
    var btnBotao: ImageView? = null
    var tvTextoInstrucao: TextView? = null
    var tvTempoFinal: TextView? = null
    var btnBotaoMeusRecordes: TextView? = null
    var clMain: View? = null

    // instância do viewModel
    private val viewModel: TimeViewModel by activityViewModels {
        TimeViewModelFactory(
            (activity?.application as TimeApplication).database.timeDao()
        )
    }

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

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cronometro = binding?.crTempoPassado
        btnBotao = binding?.ibImageButton
        tvTextoInstrucao = binding?.tvInstrucao
        tvTempoFinal = binding?.tvTextoTeste
        btnBotaoMeusRecordes = binding?.btnMeusRecordes
        clMain = binding?.clMain

        // define o ultimo tempo como uma variavel do viewmodel para manter o dado ao
        // recriar o fragment
        tvTempoFinal?.text = "Último fôlego: ${viewModel.ultimoTempo.value}"

        // define as ações a serem tomadas se o botao esta pressionado ou não
        btnBotao?.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == ACTION_DOWN) iniciaCronometro()
            if (motionEvent.action == ACTION_UP) paraCronometro()
            true
        }

        // muda de fragment ao clicar no botão
        btnBotaoMeusRecordes?.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_recordsFragment)
        }
    }

    /*
    Função que inicia o cronometro
     */
    fun iniciaCronometro() {
        cronometro?.base = SystemClock.elapsedRealtime()
        cronometro?.start()
        tvTextoInstrucao?.text = "Segure sua respiração!!"
        //clMain?.performContextClick(0.1f, 0.1f)
        clMain?.setPressed(true)
    }

    /*
    Função que pausa o cronometro
     */
    fun paraCronometro() {
        cronometro?.stop()
        viewModel._ultimoTempo.value = cronometro?.text.toString()
        tvTempoFinal?.text = "Último fôlego: ${cronometro?.text.toString()}"
        tvTextoInstrucao?.text = "Aperte e segure para iniciar a contagem."
        timeParaViewModel()
        clMain?.setPressed(false)
    }

    /*
    Função que chama o viewmodel e envia o tempo final
     */
    fun timeParaViewModel() {
        if (!cronometro?.text.toString().equals("00:00")) // se o tempo for zero nao envia para DB
        viewModel.adicionaTempoFinalAoDB(cronometro?.text.toString())
    }


    // Retorna o valor do binding para nulo
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}



