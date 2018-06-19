package com.example.admin.weather

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.example.admin.weather.model.city.City
import com.example.admin.weather.utils.JsonAssetReader

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private var kyrgyzPlaces: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setUpMap()
    }

    private fun setUpMap() {
        mMap.setOnMarkerClickListener(this)

        var city = JsonAssetReader.getDataFromJsonCities(this)
        for (i in 0 until city.size) {
            kyrgyzPlaces = LatLng(city[i].coord?.lat!!, city[i].coord?.lon!!)
            mMap.addMarker(MarkerOptions().position(kyrgyzPlaces!!).title(city[i].name))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(kyrgyzPlaces))
        }

    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val intent = Intent(this@MapsActivity, MainActivity::class.java)

        intent.putExtra("nameOfMarker", marker!!.title)
        startActivity(intent)
        return true
    }


}
