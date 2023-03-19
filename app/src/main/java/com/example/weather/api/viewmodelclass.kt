package com.example.weather.api

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.modeldataclass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewmodelclass(private val repository: Repository) : ViewModel() {
    //private val City = Intent().getStringExtra("City")
    private val pune: String = "Mumbai"
    val modelgetdata: LiveData<modeldataclass> = repository.getlivedata

    fun modelCitydata(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getrepodata(city)
        }
    }
}
