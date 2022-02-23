package com.gustavozreis.meufolego.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.databinding.FragmentRecordsBinding
import com.gustavozreis.meufolego.databinding.RecordsListItemBinding
import kotlinx.coroutines.flow.Flow

/*
    Esse adapter criei usando o viewbinding para testar e aprender esse tipo de implementação
 */

class RecordListAdapter(
    private val context: Context?,
    private val records: ArrayList<Time>) : RecyclerView.Adapter<RecordListAdapter.RecordListItemViewHolder>() {

    class RecordListItemViewHolder(binding: RecordsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // referencia os views que serão utilizados
        val tempo: TextView = binding.tvTempo
        val dia: TextView = binding.tvDia
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListItemViewHolder {
        return RecordListItemViewHolder(
            RecordsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecordListItemViewHolder, position: Int) {
        val recordeAtual = records[position]
        holder.tempo.text = recordeAtual.tempo
        holder.dia.text = recordeAtual.dia
    }

    override fun getItemCount(): Int {
        return records.size
    }

}
