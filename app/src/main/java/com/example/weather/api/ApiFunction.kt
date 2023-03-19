package com.example.weather.api

import com.example.weather.modeldataclass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


const val key = "PE8QW38LRAJ2QKFER4J33YXAB"

//6d5441554d46452591c20100230403


interface ApiFunction {
///https://weather.visualcrossing.com/VisualCrossingWebServices/
// rest/servicestimeline/Delhi?unitGroup=metric&key=$key&contentType=json
    @GET("VisualCrossingWebServices/rest/services/timeline/{Mumbai}?unitGroup=metric&key=$key&contentType=json")
    suspend fun getdata(@Path(value = "Mumbai") city:String):Response<modeldataclass>
}