package com.EFT.etf_bernardo_bravo

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.telephony.CarrierConfigManager.Gps
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.EFT.etf_bernardo_bravo.modelo.Usuario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

val database = FirebaseDatabase.getInstance().getReference("Usuario")
@Preview(showBackground = true, name = "Prueba Inicial")
@Composable
fun PantallaInicial() {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val image = painterResource(R.drawable.ic_launcher_foreground)
            Image(
                painter = image,
                contentDescription = null
            )
//            Text(text = "Pantalla Inicial")
            SpeechRecognizerApp()

        }

    }
    Spacer(modifier = Modifier.height(15.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row {
            Button(
                onClick = {
                    val intent = Intent(context, GpsActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Ver tiempo",
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(15.dp))
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 100.dp)
                    .fillMaxWidth(),
                text = "Bienvenido ${obtDatosFirebaseUsuario().toString()}"
            )
        }
    }


}

fun obtDatosFirebaseUsuario(){
    val myRef =  database.child("Usuario")

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            val value = dataSnapshot.getValue<String>()
            Log.d(TAG, "Value is: $value")
        }

        override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException())
        }
    })
}


@Composable
fun SpeechRecognizerApp(){
    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    var speechText by remember { mutableStateOf("Presione el boton y habla") }


    val recognizerIntent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
        }
    }

    val permissionLaincher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted){
            speechRecognizer.startListening(recognizerIntent)
        }
        else{
            Toast.makeText(context,"Persmisos del microfono denegados", Toast.LENGTH_LONG).show()
        }
    }

    val recognizerListener = object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {
            Log.d("SpeechReconicer", "Listo para esuchar!")
        }

        override fun onBeginningOfSpeech() {
            speechText = "Escuchando ..... ...."
        }

        override fun onRmsChanged(p0: Float) {
            Log.d("SpeechReconicer", "Listo para esuchar!")
        }

        override fun onBufferReceived(p0: ByteArray?) {
            Log.d("SpeechReconicer", "Listo para esuchar!")
        }

        override fun onEndOfSpeech() {
            speechText = "Procesando ....."
        }

        override fun onError(p0: Int) {
            speechText = "Error al reconocer la Voz"
        }

        override fun onResults(p0: Bundle?) {
            val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            speechText = matches?.firstOrNull() ?: "No se pudo reconocer"

        }

        override fun onPartialResults(p0: Bundle?) {
            Log.d("SpeechReconicer", "Listo para esuchar!")
        }

        override fun onEvent(p0: Int, p1: Bundle?) {
            Log.d("SpeechReconicer", "Listo para esuchar!")
        }

    }

    speechRecognizer.setRecognitionListener(recognizerListener)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text =  speechText,
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)

        )
        Button(
            onClick = {
                permissionLaincher.launch(Manifest.permission.RECORD_AUDIO)
            }
        ) {
            Text(text = "Presionar y Hablar")
        }


    }



}