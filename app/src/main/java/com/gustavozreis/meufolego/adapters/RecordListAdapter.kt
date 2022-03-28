package com.gustavozreis.meufolego.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.databinding.RecordsListItemBinding

/*
    Esse adapter criei usando o viewbinding para testar e aprender esse tipo de implementação
 */

class RecordListAdapter : ListAdapter<Time, RecordListAdapter.RecordListItemViewHolder>(DiffCallback) {

    class RecordListItemViewHolder(binding: RecordsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // referencia os views que serão utilizados
        val tempo: TextView = binding.tvTempo
        val dia: TextView = binding.tvDia
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListItemViewHolder {
        return RecordListItemViewHolder(
            RecordsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecordListItemViewHolder, position: Int) {
        val recordeAtual = getItem(position)
        holder.tempo.text = recordeAtual.tempo
        holder.dia.text = recordeAtual.dia
    }

    // diffcallback para habilitar a atualização do recycler view quando os tempos são apagados
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Time>() {
            override fun areItemsTheSame(oldItem: Time, newItem: Time): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Time, newItem: Time): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}
