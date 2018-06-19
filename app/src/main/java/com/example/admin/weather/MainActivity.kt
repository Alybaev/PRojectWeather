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
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson




class MainActivity : AppCompatActivity() {
    var call: Call<WeatherInfo>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val result = getDataFromJsonCities()
        info.text = result[0].name


        NetWork.getW().getData(idOfBishkek, APIID, mode, units).enqueue(object : Callback<WeatherInfo> {
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
    // For Json Asset
    fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val `is` =assets.open("city.list.kg.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
    private fun getDataFromJsonCities(): ArrayList<City> {
        val json = loadJSONFromAsset()
        return Gson().fromJson<ArrayList<City>>(json, object : TypeToken<ArrayList<City>>() {}.type)
    }

}
