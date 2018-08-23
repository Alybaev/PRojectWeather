package com.example.admin.weather.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.weather.R
import com.example.admin.weather.model.city.City
import com.example.admin.weather.ui.main.MainActivity
import com.example.admin.weather.utils.Constants.Companion.CITY_NAME_KEY_INTENT
import com.example.admin.weather.utils.Constants.Companion.KYRGYZSTAN_LAT_LNG
import com.example.admin.weather.utils.JsonAssetReader

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private var kyrgyzPlaces: LatLng? = null

    lateinit var city: ArrayList<City>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        setSupportActionBar(findViewById(R.id.toolbar))
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setUpMap()
    }

    private fun setUpMap() {

        setUpCameraOnMap()
        getCitiesDataFromAsset()
        setMarkers()

    }

    private fun setUpCameraOnMap() {

        mMap.moveCamera(CameraUpdateFactory.newLatLng(KYRGYZSTAN_LAT_LNG))
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 5.5f ))

    }



    private fun getCitiesDataFromAsset(){

        city = JsonAssetReader.getDataFromJsonCities(this)

    }

    private fun setMarkers(){

        mMap.setOnMarkerClickListener(this)
        for (i in 0 until city.size) {
            kyrgyzPlaces = LatLng(city[i].coord?.lat!!, city[i].coord?.lon!!)
            mMap.addMarker(MarkerOptions().position(kyrgyzPlaces!!).title(city[i].name))

        }

    }

    override fun onMarkerClick(marker: Marker?): Boolean {

        val intent = Intent(this@MapsActivity, MainActivity::class.java)
        intent.putExtra(CITY_NAME_KEY_INTENT, marker!!.title)
        startActivity(intent)
        return true

    }




}
