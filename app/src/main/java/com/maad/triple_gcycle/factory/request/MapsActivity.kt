package com.maad.triple_gcycle.factory.request

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.maad.triple_gcycle.R
import com.maad.triple_gcycle.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val cairo = LatLng(30.0444, 31.2357)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cairo, 12f))

        binding.fab.setOnClickListener {
            val lat = mMap.cameraPosition.target.latitude
            val lon = mMap.cameraPosition.target.longitude
            val i = Intent()
            i.putExtra("lat", lat)
            i.putExtra("lon", lon)
            setResult(RESULT_OK, i)
            finish()
        }

    }
}