package com.example.admin.weather.utils

import com.google.android.gms.maps.model.LatLng

class Constants {
    companion object {
        final val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        final val APIID = "5881b1a95ab511e9ae050d0be52f5f72"
        final val MODE = "json"
        final  val UNITS = "metric"
        final val CITY_NAME_KEY_BUNDLE = "cityName"
        final val CITY_NAME_KEY_INTENT = "nameOfMarker"
        final val WEATHER_INFO_BUNDLE_KEY = "aWeatherInfo"
        final val KYRGYZSTAN_LAT_LNG = LatLng(41.2044, 74.7661)

    }
}