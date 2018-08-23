package com.example.admin.weather.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.weather.R
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.Constants.Companion.CITY_NAME_KEY_BUNDLE
import kotlinx.android.synthetic.main.fragment_main.*

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
    private fun init(){

        getDataFromBundle()
        initAllWeatherInfo()

    }

    private fun getDataFromBundle() {
        nameOfCity = arguments?.getString(CITY_NAME_KEY_BUNDLE)
        weatherInfo = arguments?.getSerializable(Constants.WEATHER_INFO_BUNDLE_KEY) as WeatherInfo?

        Log.d("dat==-", weatherInfo.toString())
        Log.d("nameDat==-", nameOfCity)
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