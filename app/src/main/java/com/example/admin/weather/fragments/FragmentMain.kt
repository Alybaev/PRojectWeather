package com.example.admin.weather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.weather.R
import com.example.admin.weather.R.id.*
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.Constants.Companion.APIID
import com.example.admin.weather.utils.Constants.Companion.MODE
import com.example.admin.weather.utils.Constants.Companion.UNITS
import com.example.admin.weather.utils.Constants.Companion.CITY_NAME_KEY_BUNDLE
import com.example.admin.weather.utils.NetWork
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMain : Fragment() {
    var nameOfCity:String?= null
    var weatherInfo:WeatherInfo?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    fun init(){
        nameOfCity = arguments?.getString(CITY_NAME_KEY_BUNDLE)
        weatherInfo = arguments?.getSerializable(Constants.WEATHER_INFO_BUNDLE_KEY) as WeatherInfo?

        Log.d("dat==-", weatherInfo.toString())
        Log.d("nameDattt==-", nameOfCity)

        initAllWeatherInfo()

    }
    fun initAllWeatherInfo(){
        city_name.text = nameOfCity
        tempeture_view.text = weatherInfo!!.list[0].main.temp.toInt().toString() + "\u2103"
        time.text = changeTimeText(weatherInfo!!.list[0].dt_txt)
        humidity_text.text = weatherInfo!!.list[0].main.humidity.toString() + "%"
        wind_speed_text.text = weatherInfo!!.list[0].wind.speed.toString() + "m/s"
        cloud_text.text = weatherInfo!!.list[0].clouds.all.toString() + "%"
    }
    private fun changeTimeText(timeText:String): String {
        var res = timeText.substring(5,11)
        res = res.replace('-', '.')
        return res
    }


}