package com.example.weather

data class modeldataclass(
    val address: String,
    val alerts: List<Any>,
    val currentConditions: CurrentConditions,
    val days: List<days>,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val queryCost: Int,
    val resolvedAddress: String,
    val stations: Stations,
    val timezone: String,
    val tzoffset: Double
)