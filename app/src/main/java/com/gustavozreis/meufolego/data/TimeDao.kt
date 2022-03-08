package com.gustavozreis.meufolego.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeDao {

    @Insert
    suspend fun insert(time: Time)

    @Update
    suspend fun update(time: Time)

    @Delete
    suspend fun delete(time: Time)

    @Query("SELECT * from recordes ORDER by tempo DESC LIMIT 20")
    fun pegarTempos(): Flow<List<Time>>

    @Query("DELETE from recordes")
    suspend fun deletarTodosRecordes()

}