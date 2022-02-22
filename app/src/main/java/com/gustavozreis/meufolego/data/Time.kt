package com.gustavozreis.meufolego.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordes")
data class Time(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "tempo")
    val tempo: String,
    @ColumnInfo(name = "dia")
    val dia: String
)
