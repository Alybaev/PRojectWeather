package com.example.admin.weather.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.admin.weather.fragments.FragmentForecast
import com.example.admin.weather.fragments.FragmentMain

class CustomAdapter : FragmentPagerAdapter {
    var frgMain = FragmentMain()
    var frgForecast = FragmentForecast()

    private val fragments = arrayOf("Main", "Forecast")

    constructor(fragment: FragmentManager, applicationContext: Context) : super(fragment)

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return FragmentMain()
            1 -> return FragmentForecast()
        }
        return null
    }


    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position]
    }
}

