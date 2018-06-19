package com.example.admin.weather.model.city

class City {
    var id: Int? = -1
    var name: String? = null
    var country: String? = null
    var coord: Coord? = null

    inner class Coord {
        var lon: Double? = -1.0
        var lat: Double? = -1.0
    }
}