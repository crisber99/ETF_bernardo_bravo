package com.example.etf_bernardo_bravo

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

private lateinit var locationHelper: LocationHelper
private var currentCity by mutableStateOf<String?>("")

@Composable
fun Gps(){
//    locationHelper = LocationHelper(this, this)
//
//    locationHelper.getCurrentCity { city ->
//        city?.let{
//            currentCity = it
//            Toast.makeText(this, "Ciudad Actual: $it", Toast.LENGTH_LONG).show()
//        } ?: run{
//            Toast.makeText(this, "No se pudo obtener la ciudad", Toast.LENGTH_LONG).show()
//        }
//    }
}
