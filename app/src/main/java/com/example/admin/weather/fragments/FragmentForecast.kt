package com.example.admin.weather.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.weather.R
import com.example.admin.weather.adapters.ForecastAdapter
import com.example.admin.weather.model.WeatherData
import com.example.admin.weather.model.weatherInfo.ListOfWeather
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.Constants.Companion.weatherInfoBundleKey
import com.example.admin.weather.utils.NetWork
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentForecast : Fragment() {
    var weatherData:WeatherData? =null

    var cityNameForRequest:String? = null
    var nameOfCity:String?= null
    var mAdapter: ForecastAdapter? = null
    var weatherInfo: WeatherInfo? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(context!!)
        getBackData()

    }
    fun getBackData(){
        NetWork.getW().getData(cityNameForRequest!!, Constants.APIID, Constants.MODE, Constants.UNITS).enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                weatherInfo = response!!.body()
                Log.d("dd==-", response.body().toString())
                changeDataForGetDayNightTemp()
                mAdapter!!.setMData( weatherData!!)


            }

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("respp", t.toString())
            }

        })
    }
    fun init(context: Context){
//        val bundle = arguments
//            weatherInfo = bundle!!.getSerializable(weatherInfoBundleKey) as WeatherInfo?
//        Log.d("dat==-", weatherInfo.toString())
        nameOfCity = arguments?.getString(Constants.cityNameKeyBundle)
        cityNameForRequest = nameOfCity + ",kg"
        weatherData = WeatherData(ArrayList(), ArrayList(),ArrayList(),ArrayList())
        mAdapter = ForecastAdapter(context, weatherData!!)
        recycle_view_weather.layoutManager = LinearLayoutManager(this.activity)
        recycle_view_weather.adapter = mAdapter
    }
    private fun changeDataForGetDayNightTemp() {

        for(i in 0 until 10){
            if(i % 2 == 0) {
                weatherData!!.dayTemp!!.add(weatherInfo!!.list[i * 4].main.temp.toInt())
            }else{
                weatherData!!.nightTemp!!.add(weatherInfo!!.list[i * 4].main.temp.toInt())
            }
        }
        for (i in 0 until 5){
            weatherData!!.date!!.add(weatherInfo!!.list[i * 8].dt_txt)
            var iconCode = weatherInfo!!.list[i * 8].weather[0].icon
            weatherData!!.imageURL.add("http://openweathermap.org/img/w/" + iconCode + ".png")
        }

    }

}