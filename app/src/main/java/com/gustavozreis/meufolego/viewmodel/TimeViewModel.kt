package com.gustavozreis.meufolego.viewmodel

import androidx.lifecycle.*
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.data.TimeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class TimeViewModel(private val timeDao: TimeDao) : ViewModel() {

    // pega lista de tempos
    val todosTempos: LiveData<List<Time>> = timeDao.pegarTempos().asLiveData()

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

    fun criarListaRecordes(): LiveData<List<Time>> {
        return timeDao.pegarTempos().asLiveData()
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



