package com.example.admin.weather.utils

import com.example.admin.weather.model.weather.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForumServices {
   @GET("forecast?")
    fun getData( @Query("q")name: String, @Query("appid") appID: String,@Query("mode") mode: String,@Query("units") units: String) : Call<WeatherInfo>
}