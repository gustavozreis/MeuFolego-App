package com.gustavozreis.meufolego.viewmodel

import androidx.lifecycle.*
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.data.TimeDao
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TimeViewModel(private val timeDao: TimeDao) : ViewModel() {

    // variavel que armazena o último tempo conseguido
    var _ultimoTempo = MutableLiveData<String>("00:00")
    val ultimoTempo: LiveData<String>
        get() = _ultimoTempo

    // lista de livedata que é observada no fragment e utilizada no recyclerview
    val todosOsTempos: MutableLiveData<List<Time>> =
        timeDao.pegarTempos().asLiveData() as MutableLiveData<List<Time>>

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
    Função que apaga os tempos do banco de dados
     */
    fun apagarTemposDoDB() {
        viewModelScope.launch {
            timeDao.deletarTodosRecordes()
        }
    }

    /*
    Função que pega os tempos do banco de dados e os coloca em uma lista
    [NÃO FUNCIONA, É USADO LIVEDATA e .observe NO FRAGMENTO]
     */

    //fun criarListaRecordes(): ArrayList<Time> {
    //var todosTempos: ArrayList<Time> = arrayListOf(Time(1, "1212", "11212"), Time(2,"2121", "2121"))
    //var todosTempos = ArrayList<Time>()
    //return todosTempos
    //}

}

// builder para o viewmodel ter acesso ao banco de dados
class TimeViewModelFactory(private val timeDao: TimeDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimeViewModel(timeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



