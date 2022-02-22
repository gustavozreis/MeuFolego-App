package com.gustavozreis.meufolego.viewmodel

import android.os.CountDownTimer
import android.widget.Chronometer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.data.TimeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class TimeViewModel(private val timeDao: TimeDao) : ViewModel() {

    /*
   Função que adiciona o tempo final ao banco de dados
    */
    fun adicionaTempoFinalAoDB(tempoFinal: String) {
        // cria um calendário e pega a data de agora
        val calendar = Calendar.getInstance()
        val dataAgora = calendar.time
        val formatadorData = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dataParaEnviar: String = formatadorData.format(dataAgora)

        // cria o objeto de Time para adicionar ao banco de dados
        val timeAdicionar = Time(0, tempoFinal, dataParaEnviar)

        // insere a data no banco de dados
        viewModelScope.launch {
            timeDao.insert(timeAdicionar)
        }
    }

    /*
   Função que pega os tempos do banco de dados e os coloca em uma lista
    */
    fun criarListaRecordes(): ArrayList<Time> {
        val listaRecordes: ArrayList<Time> = arrayListOf()
        viewModelScope.launch {
            timeDao.pegarTempos().collect { listaRecordesFlow ->
                for (tempo in listaRecordesFlow) {
                    listaRecordes.add(tempo)
                }
            }
        }
        return listaRecordes
    }

}

// construtor para o viewmodel ter acesso ao banco de dados

class TimeViewModelFactory(private val timeDao: TimeDao):ViewModelProvider.Factory {

      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimeViewModel(timeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



