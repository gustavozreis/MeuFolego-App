package com.gustavozreis.meufolego

import android.app.Application
import com.gustavozreis.meufolego.data.Time
import com.gustavozreis.meufolego.data.TimeRoomDatabase

class TimeApplication : Application() {

    val database: TimeRoomDatabase by lazy { TimeRoomDatabase.getDatabase(this) }

}