package com.example.admin.weather.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.admin.weather.R
import com.example.admin.weather.model.WeatherData
import com.example.admin.weather.model.weatherInfo.WeatherInfo
import kotlinx.android.synthetic.main.cell_forecast.view.*

class ForecastAdapter(var context: Context, var weatherData: WeatherData) : RecyclerView.Adapter<ForecastAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {

        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_forecast, null, false))
    }

    override fun getItemCount(): Int {
        return weatherData.dayTemp.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.date.text = changeTimeText(weatherData.date!![position])
        holder.day_temperature.text = weatherData.dayTemp!![position].toString() + "\u2103"
        holder.night_temperature.text = weatherData.nightTemp!![position].toString() + "\u2103"

        Glide.with(context).load(weatherData
                .imageURL!![position])
                .into(holder.image_forecast)


    }


    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var date = view.time_text
        var day_temperature = view.text_day_temp
        var night_temperature = view.text_night_temp
        var image_forecast = view.forecast_image
    }

    fun setMData(weatherData: WeatherData) {
        this.weatherData = weatherData
        notifyDataSetChanged()
    }

    fun changeTimeText(timeText: String): String {
        var res = timeText.substring(5, 11)
        res = res.replace(':', '.')
        return res
    }


}