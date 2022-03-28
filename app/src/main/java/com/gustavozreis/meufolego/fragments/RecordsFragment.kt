package com.gustavozreis.meufolego.fragments


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gustavozreis.meufolego.TimeApplication
import com.gustavozreis.meufolego.adapters.RecordListAdapter
import com.gustavozreis.meufolego.databinding.DialogLayoutBinding
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

        val rvAdapter = configuraAdapter()
        configuraListenersDeClick()

        // cria o observador e passa os valores dos tempos para a listaDeRecordes
        viewModel.todosOsTempos.observe(this.viewLifecycleOwner) { todosTempos ->
            todosTempos.let {
                rvAdapter.submitList(it)
            }
        }
    }

    private fun configuraListenersDeClick() {
        // configuração botao apagar tempos
        val btnApagar: Button? = binding?.btnApagar
        btnApagar?.setOnClickListener {
            criaAlertaAoApagarFolegos()
        }
    }

    private fun configuraAdapter(): RecordListAdapter {
        // criar e transmitir o adapter com a lista de tempos
        val rvAdapter = RecordListAdapter()
        binding?.rvRecordes?.adapter = rvAdapter
        return rvAdapter
    }

    /*
    Função que cria o alertdialog
     */
    private fun criaAlertaAoApagarFolegos() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.setCanceledOnTouchOutside(true)
        dialogBinding.tvSim.setOnClickListener {
            viewModel.apagarTemposDoDB()
            dialog.dismiss()
        }
        dialogBinding.tvCancelar.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Retorna o valor do binding para nulo
        binding = null
    }
}