package com.example.etf_bernardo_bravo

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Prueba")
@Composable
fun FormularioUsuario() {
    val context = LocalContext.current

    val rut = remember { mutableStateOf("") }
    val nombre = remember { mutableStateOf("") }
    val appaterno = remember { mutableStateOf("") }
    val apmaterno = remember { mutableStateOf("") }
    val direccion = remember { mutableStateOf("") }
    val comuna = remember { mutableStateOf("") }
    val regionState = remember { mutableStateOf("") }

    val regiones = remember { mutableStateOf(emptyList<String>()) }
    val regionExpanded = remember { mutableStateOf(false) }
    val regionSeleccionada = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val dbHelper = DataBaseHelper(context)
        regiones.value = dbHelper.obtRegiones()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = rut.value,
                onValueChange = { rut.value = it },
                label = { Text("RUT") },
                placeholder = { Text("Ej: 12345678-9") },
                modifier = Modifier.fillMaxWidth()

            )

            OutlinedTextField(
                value = nombre.value,
                onValueChange = { nombre.value = it },
                label = { Text("Nombre") },
                placeholder = { Text("Juan") },
                modifier = Modifier.fillMaxWidth()

            )

            OutlinedTextField(
                value = appaterno.value,
                onValueChange = { appaterno.value = it },
                label = { Text("Apellido Paterno") },
                placeholder = { Text("Perez") },
                modifier = Modifier.fillMaxWidth()

            )

            OutlinedTextField(
                value = apmaterno.value,
                onValueChange = { apmaterno.value = it },
                label = { Text("Apellido Materno") },
                placeholder = { Text("Perez") },
                modifier = Modifier.fillMaxWidth()

            )

            OutlinedTextField(
                value = direccion.value,
                onValueChange = { direccion.value = it },
                label = { Text("Dirección") },
                placeholder = { Text("Pasaje 1, tu casa") },
                modifier = Modifier.fillMaxWidth()

            )

            OutlinedTextField(
                value = comuna.value,
                onValueChange = { comuna.value = it },
                label = { Text("Comuna") },
                placeholder = { Text("comuna") },
                modifier = Modifier.fillMaxWidth()

            )

            ExposedDropdownMenuBox(
                expanded = regionExpanded.value,
                onExpandedChange = { regionExpanded.value = !regionExpanded.value }
            ) {
                OutlinedTextField(
                    value = regionSeleccionada.value,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Regiones") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = regionExpanded.value)
                    },
                    modifier = Modifier.fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = regionExpanded.value,
                    onDismissRequest = { regionExpanded.value = false }
                ) {
                    regiones.value.forEach { region ->
                        DropdownMenuItem(
                            text = { Text(region) },
                            onClick = {
                                regionSeleccionada.value = region
                                regionExpanded.value = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    if (regionSeleccionada.value.isEmpty()) {
                        Toast.makeText(context, "Seleccione una región", Toast.LENGTH_LONG).show()
                        return@Button
                    }
                    val dbHelper = DataBaseHelper(context)
                    val resultado = dbHelper.insertarUsuario(
                        rut = rut.value,
                        nombre = nombre.value,
                        appaterno = appaterno.value,
                        apmaterno = apmaterno.value,
                        direccion = direccion.value,
                        region = regionSeleccionada.value,
                        comuna = comuna.value
                    )
                    if (resultado != -1L) {
                        rut.value = ""
                        nombre.value = ""
                        appaterno.value = ""
                        apmaterno.value = ""
                        direccion.value = ""
                        comuna.value = ""
                        Toast.makeText(context, "Usuario Agregado", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Error al agregar Usuario", Toast.LENGTH_LONG)
                            .show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Guardar Usuario")
            }
        }
    }
}
