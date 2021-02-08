package com.delta_se.tegalur.ui.activity

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding : ActivityMapsBinding

    val data by getParcelableExtra<DataPariwisata>(DetailPariwisata.EXTRA_MYDATA)

    companion object {
        const val EXTRA_MYDATA = "extra_mydata"
    }

    inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = lazy {
        intent.getParcelableExtra<T>(key)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.apply {
            gmapsTitle.text = data?.title.toString()
            gmapsBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var lat: Double? = data?.lat
        var lng: Double? = data?.lang
        val location = LatLng(lat!!, lng!!)
        mMap.addMarker(MarkerOptions().position(location).title(data?.title.toString()))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f))
        mMap.setOnMapLongClickListener { latLng ->
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)
            markerOptions.draggable(true)
            markerOptions.title(data?.title.toString())
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            mMap.addMarker(markerOptions)
        }
    }
}