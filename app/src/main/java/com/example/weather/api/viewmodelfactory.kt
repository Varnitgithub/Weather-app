package com.example.weather.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class viewmodelfactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewmodelclass(repository) as T
    }
}