package com.gustavozreis.meufolego.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.gustavozreis.meufolego.TimeApplication
import com.gustavozreis.meufolego.adapters.RecordListAdapter
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.data.TimeDao
import com.gustavozreis.meufolego.databinding.FragmentRecordsBinding
import com.gustavozreis.meufolego.viewmodel.TimeViewModel
import com.gustavozreis.meufolego.viewmodel.TimeViewModelFactory
import kotlinx.coroutines.flow.Flow

class RecordsFragment: Fragment() {

    private var binding: FragmentRecordsBinding? = null

    private lateinit var recyclerView: RecyclerView

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
        binding = FragmentRecordsBinding.inflate(inflater, container, false)
        val listaDeRecordes: ArrayList<Time> = viewModel.criarListaRecordes()

        val listaDeRecordesTeste: ArrayList<Time> = arrayListOf(Time(1, "1212", "11212"), Time(2,"2121", "2121"))

        val teste: TextView? = binding?.tvTempo
        teste?.text = listaDeRecordesTeste[0].tempo
        recyclerView = binding!!.rvRecordes
        recyclerView.adapter = RecordListAdapter(context, listaDeRecordesTeste)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // Retorna o valor do binding para nulo
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}