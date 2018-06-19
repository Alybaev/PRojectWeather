package com.example.admin.weather


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.admin.weather.model.city.City

import com.example.admin.weather.model.weather.WeatherInfo

import com.example.admin.weather.utils.Constants.Companion.APIID
import com.example.admin.weather.utils.Constants.Companion.idOfBishkek
import com.example.admin.weather.utils.Constants.Companion.mode
import com.example.admin.weather.utils.Constants.Companion.units
import com.example.admin.weather.utils.NetWork

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {
    var call: Call<WeatherInfo>? = null
    var nameOfCity:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()



        NetWork.getW().getData(nameOfCity!!, APIID, mode, units).enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                val weatherInfo = response!!.body()

  //              info.text = weatherInfo!!.list[0].main.temp.toString()
                        Log.d("respp", response!!.body().toString())
            }

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("respp", t.toString())
            }

        })
    }
    fun init(){
        nameOfCity = intent.getStringExtra("nameOfMarker") + ",kg"
    }


}
