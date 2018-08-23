package com.example.admin.weather.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.ForumServices
import com.example.admin.weather.utils.NetWork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter (var service: ForumServices,
                     var context: Context, var view : MainContract.View) : MainContract.Presenter {
    override fun getWeatherData(cityNameForRequest : String) {
        service.getData(cityNameForRequest!!, Constants.APIID, Constants.MODE, Constants.UNITS).enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                if(response!!.isSuccessful) {
                    view.onSuccess(response.body()!!)

                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
                }

                Log.d("dd==-", response.body()!!.list[0].dt_txt)
            }

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Toast.makeText(context,t.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }
}