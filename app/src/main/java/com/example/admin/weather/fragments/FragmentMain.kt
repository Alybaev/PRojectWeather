package com.example.admin.weather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.weather.R
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import com.example.admin.weather.utils.Constants.Companion.APIID
import com.example.admin.weather.utils.Constants.Companion.MODE
import com.example.admin.weather.utils.Constants.Companion.UNITS
import com.example.admin.weather.utils.Constants.Companion.cityNameKeyBundle
import com.example.admin.weather.utils.Constants.Companion.weatherInfoBundleKey
import com.example.admin.weather.utils.NetWork
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMain : Fragment() {
    var nameOfCity:String?= null
    var weatherInfo:WeatherInfo?=null
    var cityNameForRequest:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getBackData()

    }


    fun getBackData(){
        NetWork.getW().getData(cityNameForRequest!!, APIID, MODE, UNITS).enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                weatherInfo = response!!.body()
                Log.d("dd==-", response.body().toString())
                tempeture_view.text = weatherInfo!!.list[0].main.temp.toInt().toString() + "\u2103"
                time.text = changeTimeText(weatherInfo!!.list[0].dt_txt)
                humidity_text.text = weatherInfo!!.list[0].main.humidity.toString() + "%"
                wind_speed_text.text = weatherInfo!!.list[0].wind.speed.toString() + "m/s"
                cloud_text.text = weatherInfo!!.list[0].clouds.all.toString() + "%"
                Log.d("respp", response!!.body().toString())
                sendDataThroughtBundle()
            }

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("respp", t.toString())
            }

        })
    }
    fun init(){
        nameOfCity = arguments?.getString(cityNameKeyBundle)
        city_name.text = nameOfCity
        cityNameForRequest = nameOfCity + ",kg"
    }
    fun changeTimeText(timeText:String): String {
        var res = timeText.substring(5,11)
        res = res.replace(':', '.')
        return res
    }
    fun sendDataThroughtBundle(){
        val bundles = Bundle()

        var fragForecast = FragmentForecast()

        // ensure your object has not null
        if (weatherInfo != null) {
            bundles.putSerializable(weatherInfoBundleKey, weatherInfo)
            Log.e("aWeatherInfo", "is valid")

        } else {
            Log.e("WeatherInfo", "is null")

        }
        fragForecast.arguments = bundles

    }

}