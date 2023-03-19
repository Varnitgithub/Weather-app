package com.example.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [databaseModelClass::class], version = 1, exportSchema = false)
abstract class weather_databse:RoomDatabase() {

    abstract fun weather_dao():Weather_dao

            companion object {

        @Volatile
        var INSTANCE: weather_databse? = null
        fun getinstanse(context: Context): weather_databse {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, weather_databse::class.java, "roomDb"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}