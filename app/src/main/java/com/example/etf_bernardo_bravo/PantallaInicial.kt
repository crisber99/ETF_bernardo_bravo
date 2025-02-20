package com.example.etf_bernardo_bravo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, name = "Prueba Inicial")
@Composable
fun PantallaInicial() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val image = painterResource(R.drawable.ic_launcher_foreground)
        Image(
            painter = image,
            contentDescription = null
        )
        Text(text = "Pantalla Inicial")
    }

}