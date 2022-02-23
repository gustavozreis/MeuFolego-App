package com.gustavozreis.meufolego.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.gustavozreis.meufolego.TimeApplication
import com.gustavozreis.meufolego.adapters.RecordListAdapter
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.data.TimeDao
import com.gustavozreis.meufolego.databinding.FragmentRecordsBinding
import com.gustavozreis.meufolego.viewmodel.TimeViewModel
import com.gustavozreis.meufolego.viewmodel.TimeViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecordsFragment: Fragment() {

    private var binding: FragmentRecordsBinding? = null

    private lateinit var recyclerView: RecyclerView

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

        var listaDeRecordes = ArrayList<Time>()

        // cria um observador da lista de tempos e os adiciona a 'listaDeRecordes'
        viewModel.todosOsTempos.observe(this.viewLifecycleOwner) { todosTempos ->
            for (tempo in todosTempos) {
                listaDeRecordes.add(tempo)
            }
        }

        // criar e transmitir o adapter com a lista de tempos
        recyclerView = binding!!.rvRecordes
        recyclerView.adapter = RecordListAdapter(context, listaDeRecordes)

    }

    // Retorna o valor do binding para nulo
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

   /* private fun criaListaTempos(timeDao: TimeDao): ArrayList<Time> {
        var listaDeRecordes: ArrayList<Time> = arrayListOf()
        lifecycleScope.launch {
            timeDao.pegarTempos().collect { temposFlow ->
                for (tempo in temposFlow) {
                    listaDeRecordes.add(tempo)
                    delay(200)
                }
            }
        }
        return listaDeRecordes
    }*/


}