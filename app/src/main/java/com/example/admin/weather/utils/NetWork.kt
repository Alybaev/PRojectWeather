package com.example.admin.weather.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetWork {
    companion object {

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

    }
}