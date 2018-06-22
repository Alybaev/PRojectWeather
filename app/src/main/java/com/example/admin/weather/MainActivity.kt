package com.example.admin.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.admin.weather.R.id.tabLayout
import com.example.admin.weather.R.id.view_pager
import com.example.admin.weather.adapters.CustomAdapter
import com.example.admin.weather.fragments.FragmentForecast
import com.example.admin.weather.fragments.FragmentMain
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.Constants.Companion.CITY_NAME_KEY_BUNDLE
import com.example.admin.weather.utils.Constants.Companion.CITY_NAME_KEY_INTENT
import com.example.admin.weather.utils.Constants.Companion.WEATHER_INFO_BUNDLE_KEY
import com.example.admin.weather.utils.NetWork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var weatherInfo:WeatherInfo?=null
    var cityNameForRequest:String? = null
    var nameOfCity:String? = null

    var custom: CustomAdapter? = null

    var mainFrag: FragmentMain?=null
    var forecastFrag: FragmentForecast?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        getBackData()
    }

    private fun init() {
        custom = CustomAdapter(supportFragmentManager, applicationContext)
        view_pager.adapter = custom
        tabLayout?.setupWithViewPager(view_pager)

        nameOfCity = intent.getStringExtra(CITY_NAME_KEY_INTENT)

        mainFrag = FragmentMain()
        forecastFrag  = FragmentForecast()

        cityNameForRequest = nameOfCity+",kg"

        custom?.addFragment(mainFrag!!, "Main")
        custom?.addFragment(forecastFrag!!, "Forecast")
    }
    fun getBackData(){
        NetWork.getW().getData(cityNameForRequest!!, Constants.APIID, Constants.MODE, Constants.UNITS).enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                weatherInfo = response!!.body()

                mainFrag?.arguments = addDataInBundle()
                forecastFrag?.arguments = addDataInBundle()

                Log.d("dd==-", response.body().toString())
            }

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("respp", t.toString())
            }

        })
    }
    private fun addDataInBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(CITY_NAME_KEY_BUNDLE, nameOfCity)
        // ensure your object has not null
        if (weatherInfo != null) {
            bundle.putSerializable(WEATHER_INFO_BUNDLE_KEY, weatherInfo)
            Log.e("aWeatherInfo", "is valid")
        } else {
            Log.e("WeatherInfo", "is null")
        }
        return bundle
    }


}



