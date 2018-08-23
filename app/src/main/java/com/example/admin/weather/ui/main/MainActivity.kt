package com.example.admin.weather.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.admin.weather.R
import com.example.admin.weather.adapters.CustomAdapter
import com.example.admin.weather.ui.fragments.FragmentForecast
import com.example.admin.weather.ui.fragments.FragmentMain
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import com.example.admin.weather.utils.Constants
import com.example.admin.weather.utils.Constants.Companion.CITY_NAME_KEY_BUNDLE
import com.example.admin.weather.utils.Constants.Companion.CITY_NAME_KEY_INTENT
import com.example.admin.weather.utils.Constants.Companion.WEATHER_INFO_BUNDLE_KEY
import com.example.admin.weather.utils.ForumServices
import com.example.admin.weather.utils.NetWork
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),MainContract.View {

    var weatherInfo:WeatherInfo?=null
    var cityNameForRequest:String? = null
    var nameOfCity:String? = null

    var custom: CustomAdapter? = null

    var mainFrag: FragmentMain?=null
    var forecastFrag: FragmentForecast?=null

    lateinit var presenter: MainPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        nameOfCity = intent.getStringExtra(CITY_NAME_KEY_INTENT)
        cityNameForRequest = nameOfCity+",kg"
        presenter = MainPresenter(NetWork.getService(),this,this)

        presenter.getWeatherData(cityNameForRequest!!)

    }

    override fun onSuccess(body: WeatherInfo) {
        weatherInfo = body
        init()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun init() {

        initToolbar()

        custom = CustomAdapter(supportFragmentManager, applicationContext)
        view_pager.adapter = custom
        tabLayout?.setupWithViewPager(view_pager)

        mainFrag = FragmentMain()
        forecastFrag  = FragmentForecast()

        mainFrag?.arguments = addDataInBundle()
        forecastFrag?.arguments = addDataInBundle()

        custom?.addFragment(mainFrag!!, "Main")
        custom?.addFragment(forecastFrag!!, "Forecast")


    }
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }


    private fun addDataInBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(CITY_NAME_KEY_BUNDLE, nameOfCity)
        // ensure your object has not null
        if (weatherInfo != null) {
            bundle.putSerializable(WEATHER_INFO_BUNDLE_KEY, weatherInfo)
            Log.d("aWeatherInfo", "is valid")
        } else {
            Log.d("WeatherInfo", "is null")
        }
        return bundle
    }


}



