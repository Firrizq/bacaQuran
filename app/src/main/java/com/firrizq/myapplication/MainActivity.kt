package com.firrizq.myapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.firrizq.myapplication.databinding.ActivityMainBinding
import com.firrizq.myapplication.utils.LOC_PERMISSION_REQ_CORE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _fusedLocation: FusedLocationProviderClient? = null
    private val fusedLocation get() = _fusedLocation as FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        getUserLocation()

        val bottomNavView = binding.navBottomView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavView.setupWithNavController(navController)

    }

    private fun getUserLocation() {
        if (checkLocationPermission()) {
            if (isLocationOn()) {
                fusedLocation.lastLocation.addOnCompleteListener {
                    val geocoder = Geocoder(this, Locale.getDefault())
                    geocoder.getFromLocation(
                        -6.3927881,
                        106.8506263,
//                            it.result.latitude,
//                            it.result.longitude,
                        1
                    ) { listAddress ->
                        val city = listAddress[0].subAdminArea
                        val resultOfCity = city.split(" ")
                        Snackbar.make(binding.root, resultOfCity[1], Snackbar.LENGTH_LONG).show()
                    }
//                    if (it.result != null) {
//
//                    } else {
//                        Toast.makeText(this, "Sorry, something wrong.", Toast.LENGTH_SHORT).show()
//                    }
                }
            } else {
                Toast.makeText(this, "Please turn on your location.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestLocationPermission()
        }
    }

    private fun isLocationOn(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOC_PERMISSION_REQ_CORE
        )
    }

    private fun checkLocationPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false
        }
        return true
    }
}