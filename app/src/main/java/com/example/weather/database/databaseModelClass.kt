package com.example.weather.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("weather_data")
data class databaseModelClass(
    @PrimaryKey(autoGenerate = true)
    val id :Int=0,
    val address:String,
    val date:String,
    val time:String,
    val dayUpdate:String,
    val temp:String,
    val mintemp:String,
    val maxtemp:String,
    val sunshinetime:String,
    val sunsettime:String,
    val windspeed:String,
    val pressure:String,
    val humidity:String
    )
