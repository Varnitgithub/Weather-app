package com.example.weather.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.database.weather_databse
import com.example.weather.modeldataclass

class Repository(private val apiFunction: ApiFunction,private val weatherDatabse: weather_databse) {

   private val modellivedata = MutableLiveData<modeldataclass>()

            val getlivedata:LiveData<modeldataclass>
            get() = modellivedata

    suspend fun getrepodata(city:String){

       val response =  apiFunction.getdata(city)

        if (response.body()!=null){



            modellivedata.postValue(response.body())
        }
    }
}