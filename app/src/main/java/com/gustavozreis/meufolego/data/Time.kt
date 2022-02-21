package com.gustavozreis.meufolego.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "times")
data class Time(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "time")
    val thisTime: String,
    @ColumnInfo(name = "date")
    val timeDate: String
)
