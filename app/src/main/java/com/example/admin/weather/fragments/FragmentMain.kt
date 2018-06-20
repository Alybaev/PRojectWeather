package com.example.admin.weather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.weather.R
import com.example.admin.weather.model.weather.WeatherInfo
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.NetWork
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMain : Fragment() {
    var nameOfCity:String?= null

    var weatherInfo:WeatherInfo?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        getBackData()

        return inflater.inflate(R.layout.fragment_main, null, false)

    }


    fun getBackData(){
        NetWork.getW().getData(nameOfCity!!, Constants.APIID, Constants.mode, Constants.units).enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                weatherInfo = response!!.body()
                NetWork.weatherInfo = weatherInfo
                tempeture_view.text = weatherInfo!!.list[0].main.temp.toString() + "&#8451"
                time.text = changeTimeText(weatherInfo!!.list[0].dt_txt)
                humidity_text.text = weatherInfo!!.list[0].main.humidity.toString() + "%"
                wind_speed_text.text = weatherInfo!!.list[0].wind.speed.toString() + "m/s"
                cloud_text.text = weatherInfo!!.list[0].clouds.all.toString() + "%"

                Log.d("respp", response!!.body().toString())
            }

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("respp", t.toString())
            }

        })
    }
    fun init(){
        nameOfCity = arguments!!.getString("nameOfMarker")
        city_name.text = nameOfCity
    }
    fun changeTimeText(timeText:String): String {
        var res = timeText.substring(5,11)
        res = res.replace(':', '.')
        return res
    }

}