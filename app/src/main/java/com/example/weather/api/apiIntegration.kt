package com.example.weather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiIntegration {

    private val retrofit: Retrofit by lazy {
Retrofit.Builder().baseUrl("https://weather.visualcrossing.com/").
addConverterFactory(
    GsonConverterFactory.create()).build()
    }

    val ApiInterface: ApiFunction by lazy {
        retrofit.create(ApiFunction::class.java)
    }
}