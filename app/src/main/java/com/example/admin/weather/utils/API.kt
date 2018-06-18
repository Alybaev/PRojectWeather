package com.example.admin.weather.utils

import com.example.admin.weather.model.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
   @GET("forecast")
    fun getData( @Query("APPID") appID: String,@Query("id")id: Int) : Call<WeatherInfo>
}