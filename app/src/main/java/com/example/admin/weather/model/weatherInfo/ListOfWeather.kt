package com.example.admin.weather.model.weatherInfo

import java.io.Serializable

class ListOfWeather(var main: Main, var weather: ArrayList<Weather>, var wind: Wind, var dt_txt: String,var clouds : Cloud): Serializable