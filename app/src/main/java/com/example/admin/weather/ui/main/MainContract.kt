package com.example.admin.weather.ui.main

import com.example.admin.weather.model.weatherInfo.WeatherInfo

interface MainContract {
    interface View  {

        fun onSuccess(body: WeatherInfo)
        fun onFailure(message: String)

    }
    interface Presenter {
        fun getWeatherData(cityNameForRequest : String)
    }
}