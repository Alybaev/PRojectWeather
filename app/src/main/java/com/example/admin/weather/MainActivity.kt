package com.example.admin.weather


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import com.example.admin.weather.model.weather.WeatherInfo

import com.example.admin.weather.utils.Constants.Companion.APIID

import com.example.admin.weather.utils.Constants.Companion.mode
import com.example.admin.weather.utils.Constants.Companion.units
import com.example.admin.weather.utils.NetWork
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {
    var nameOfCity:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        getBackData()

    }
    fun getBackData(){
        NetWork.getW().getData(nameOfCity!!, APIID, mode, units).enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                var weatherInfo = response!!.body()

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
        nameOfCity = intent.getStringExtra("nameOfMarker") + ",kg"
        city_name.text = nameOfCity
    }
    fun changeTimeText(timeText:String): String {
        var res = timeText.substring(5,11)
        res = res.replace(':', '.')
        return res
    }


}
