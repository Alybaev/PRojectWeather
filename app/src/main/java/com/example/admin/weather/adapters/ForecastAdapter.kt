package com.example.admin.weather.adapters

import android.content.Context
import android.graphics.Movie
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.weather.R
import com.example.admin.weather.model.weather.WeatherInfo
import kotlinx.android.synthetic.main.cell_forecast.view.*

class ForecastAdapter(var context : Context,  var weatherInfo : WeatherInfo) : RecyclerView.Adapter<ForecastAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return  MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_forecast, null))
    }

    override fun getItemCount(): Int {
        return
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.date.text = data.data1[position]
        holder.day_temperature.text = data.data3[position]
        holder.night_temperature.text = data.data2[position]

    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var date = view.time_text
        var day_temperature = view.text_day_temp
        var night_temperature = view.text_night_temp
    }

    fun setMData(data: GenData, movie: Movie) {
        this.movie = movie
        this.data = data
        notifyDataSetChanged()
    }



}