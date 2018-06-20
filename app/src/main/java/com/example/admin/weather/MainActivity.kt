package com.example.admin.weather


import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.widget.Toast
import com.example.admin.weather.R.id.view_pager
import com.example.admin.weather.adapters.CustomAdapter
import com.example.admin.weather.fragments.FragmentForecast
import com.example.admin.weather.fragments.FragmentMain


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

    var custom: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        addListenerOnTabLayout()

    }
    fun init(){
        custom = CustomAdapter(supportFragmentManager, applicationContext)
        view_pager.adapter = custom
    }
    fun addListenerOnTabLayout(){
        tabLayout?.setupWithViewPager(view_pager)
        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }
        })
    }

}



