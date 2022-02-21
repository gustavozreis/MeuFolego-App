package com.gustavozreis.meufolego.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Time::class], version = 1, exportSchema = false)
abstract class TimeRoomDatabase : RoomDatabase() {

    abstract fun timeDao(): TimeDao

    companion object{
        @Volatile
        private var INSTANCE: TimeRoomDatabase? = null

        fun getDatabase(context: Context): TimeRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TimeRoomDatabase::class.java,
                    "time_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}