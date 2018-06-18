package com.example.admin.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.admin.weather.model.WeatherInfo
import com.example.admin.weather.utils.API
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.NetWork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var call: Call<WeatherInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        getDataFromBack()
    }
    private fun getDataFromBack() {
        call!!.enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                val weatherInfo = response!!.body()
                info.text = weatherInfo!!.list[0].main.temp.toString()
                Log.d("Main Activity",weatherInfo!!.list[0].main.temp.toString())
            }
            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun init() {
        var weatherApi = NetWork.retrofit.create(API::class.java)
        call = weatherApi.getData(Constants.APIID,Constants.idOfBishkek)


    }
}
