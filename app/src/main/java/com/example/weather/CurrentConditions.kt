package com.example.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CurrentConditions(
    @PrimaryKey(autoGenerate = true)
    val id :Int=0,
    val cloudcover: Double,
    val conditions: String,
    val datetime: String,
    val datetimeEpoch: Int,
    val dew: Double,
    val feelslike: Double,
    val humidity: Double,
    val icon: String,
    val moonphase: Double,
    val precip: Double,
    val precipprob: Double,
  //  val preciptype: Any,
    val pressure: Double,
    val snow: Double,
    val snowdepth: Double,
   // val solarenergy: Any,
    val solarradiation: Double,
    val source: String,
   // val stations: List<String>,
    val sunrise: String,
    val sunriseEpoch: Int,
    val sunset: String,
    val sunsetEpoch: Int,
    val temp: Double,
    val uvindex: Double,
    val visibility: Double,
    val winddir: Double,
   // val windgust: Any,
    val windspeed: Double
)