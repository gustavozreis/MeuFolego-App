package com.gustavozreis.meufolego.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gustavozreis.meufolego.TimeApplication
import com.gustavozreis.meufolego.adapters.RecordListAdapter
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.databinding.FragmentRecordsBinding
import com.gustavozreis.meufolego.viewmodel.TimeViewModel
import com.gustavozreis.meufolego.viewmodel.TimeViewModelFactory

class RecordsFragment : Fragment() {

    private var binding: FragmentRecordsBinding? = null

    // instância do viewModel
    private val viewModel: TimeViewModel by activityViewModels {
        TimeViewModelFactory(
            (activity?.application as TimeApplication).database.timeDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // cria lista de tempos para passar para o adapter
        val listaDeRecordes = ArrayList<Time>()

        // criar e transmitir o adapter com a lista de tempos
        val rvAdapter = RecordListAdapter()
        binding?.rvRecordes?.adapter = rvAdapter

        // cria o observador e passa os valores dos tempos para a listaDeRecordes
        viewModel.todosOsTempos.observe(this.viewLifecycleOwner) { todosTempos ->
            todosTempos.let {
                rvAdapter.submitList(it)
            }
        }

        // configuração botao apagar tempos
        val btnApagar: Button? = binding?.btnApagar
        btnApagar?.setOnClickListener {
            viewModel.apagarTemposDoDB()
        }

    }

    override fun onStart() {
        super.onStart()

    }

    // Retorna o valor do binding para nulo
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}