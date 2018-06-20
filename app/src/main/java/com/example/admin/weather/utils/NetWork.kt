package com.example.admin.weather.utils

import android.app.Application
import com.example.admin.weather.model.weather.WeatherInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetWork : Application() {
    companion object {
        var weatherInfo: WeatherInfo? = null
        var forum: ForumServices? = null
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        fun getW() : ForumServices {
            forum = retrofit.create(ForumServices::class.java)
            return forum!!
        }

    }
}