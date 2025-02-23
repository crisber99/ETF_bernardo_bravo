package com.EFT.etf_bernardo_bravo

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.Address
import android.provider.CallLog
import android.provider.CallLog.Locations
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class LocationHelper(private val context:Context, private val activity: Activity) {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    fun getCurrentCity(onResult: (String?) -> Unit) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    fechCityFromLocation(it, onResult)
                }
            } ?: run {
                onResult(null)
            }
        }
    }

    suspend fun fechCityFromLocation(locations: Location, onResult: (String?) -> Unit) {
        withContext(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val adresses: List<Address>? = geocoder.getFromLocation(locations.latitude, locations.longitude, 1)
                if(adresses != null && adresses.isNotEmpty()){
                    val city = adresses[0].locality
                    withContext(Dispatchers.Main){
                        onResult(city)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }
    }


}