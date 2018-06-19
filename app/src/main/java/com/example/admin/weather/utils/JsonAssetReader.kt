package com.example.admin.weather.utils


import android.app.Activity
import android.content.Context
import com.example.admin.weather.model.city.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class JsonAssetReader {


    companion object {
        private fun loadJSONFromAsset(context: Context): String? {
            var json: String? = null
            try {
                val `is` = context.assets.open("city.list.kg.json")
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                json = String(buffer)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }

        fun getDataFromJsonCities(context: Context): ArrayList<City> {
            val json = loadJSONFromAsset(context)
            return Gson().fromJson<ArrayList<City>>(json, object : TypeToken<ArrayList<City>>() {}.type)
        }

    }

}