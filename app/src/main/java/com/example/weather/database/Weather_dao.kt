package com.example.weather.database

import androidx.room.*
import com.example.weather.*
import retrofit2.Response

@Dao
interface Weather_dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCC_Data(databaseModelClass: databaseModelClass)

    /*@Query("Select * From weather_data")
    fun get_weather_data_hour(): Response<databaseModelClass>*/

}