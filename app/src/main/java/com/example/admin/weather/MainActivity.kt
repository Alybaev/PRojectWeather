package com.example.admin.weather


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.weather.adapters.CustomAdapter
import com.example.admin.weather.fragments.FragmentForecast
import com.example.admin.weather.fragments.FragmentMain
import com.example.admin.weather.utils.Constants.Companion.cityNameKeyBundle


import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var custom: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        custom = CustomAdapter(supportFragmentManager, applicationContext)
        view_pager.adapter = custom
        tabLayout?.setupWithViewPager(view_pager)

        val nameOfCity = intent.getStringExtra("nameOfMarker")

        val mainFrag = FragmentMain()
        mainFrag.arguments = addDataInBundle(nameOfCity)
        val forecastFrag = FragmentForecast()
        forecastFrag.arguments = addDataInBundle(nameOfCity)

        custom?.addFragment(mainFrag, "Main")
        custom?.addFragment(forecastFrag, "Forecast")
    }

    private fun addDataInBundle(title: String): Bundle {
        val bundle = Bundle()
        bundle.putString(cityNameKeyBundle, title)
        return bundle
    }

}



