package com.EFT.etf_bernardo_bravo


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.EFT.etf_bernardo_bravo.modelo.Recetas

@Preview(showBackground = true, name = "Prueba Usuario")
@Composable
fun PantallaRecetas() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0xffa1e2fa))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lista de Recetas",
                fontSize = 20.sp,
                color = Color.Black
            )
            val lstReceta = Recetas.listarRecetas()

            if (lstReceta != null) {
                Log.d("Proy", "Se listará")
                ListaReceta(lstReceta)
            } else {
                Log.d("Proy", "No se listará")
            }
        }
    }
}

@Composable
fun ListaReceta(lstUser: List<Recetas>) {
    Log.d("Proy", "Entró en Lista")
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(border = BorderStroke(1.dp, Color.Black), shape = CutCornerShape(8.dp)),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(text = "Mail")
                }

                Column() {
                    Text(text = "Pass")
                }
            }
        }
        items(lstUser) { lst ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(border = BorderStroke(1.dp, Color.Black)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = lst.id.toString())
                Text(text = lst.nomReceta)
                Text(text = lst.ingredientes.nomIngrediente)
            }
            Log.d("Proy", "Se lista $lst")
        }
    }
}
