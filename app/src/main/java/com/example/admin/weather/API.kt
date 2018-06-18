package com.example.admin.weather

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
   @GET("forecast")
    fun getData(@Query("q")city: String) : Call<ResponseBody>
}