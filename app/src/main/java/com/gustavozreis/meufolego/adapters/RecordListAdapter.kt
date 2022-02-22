package com.gustavozreis.meufolego.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.databinding.FragmentRecordsBinding
import com.gustavozreis.meufolego.databinding.RecordsListItemBinding

/*
    Esse adapter criei usando o viewbinding para testar e aprender esse tipo de implementação
 */

class RecordListAdapter(private val records: List<Time>) : RecyclerView.Adapter<RecordListAdapter.RecordListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListItemViewHolder {
        return RecordListItemViewHolder(
            RecordsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecordListItemViewHolder, position: Int) {
        holder.tempo.text = records[position].tempo
        holder.dia.text = records[position].dia
    }

    override fun getItemCount(): Int {
        return records.size
    }

}

class RecordListItemViewHolder(private val binding: RecordsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    // referencia os views que serão utilizados
    val tempo: TextView = binding.tvTempo
    val dia: TextView = binding.tvDia
}
