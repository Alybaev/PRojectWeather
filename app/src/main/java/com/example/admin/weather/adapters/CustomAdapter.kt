package com.example.admin.weather.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class CustomAdapter : FragmentPagerAdapter {
    private var titles: ArrayList<String> = ArrayList()
    private var fragments: ArrayList<Fragment> = ArrayList()

    constructor(fragment: FragmentManager, applicationContext: Context) : super(fragment)

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment? {
        return fragments[position]
    }


    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}

