package com.example.etf_bernardo_bravo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.etf_bernardo_bravo.ui.theme.ETF_bernardo_bravoTheme
import com.example.etf_bernardo_bravo.PantallaUsuarios
import com.example.etf_bernardo_bravo.Routes
import com.example.etf_bernardo_bravo.PantallaLogin
import com.example.etf_bernardo_bravo.PantallaRecetas
import com.example.etf_bernardo_bravo.PantallaRecuperar
import com.example.etf_bernardo_bravo.modelo.Usuario
import com.example.etf_bernardo_bravo.modelo.Usuario.Companion.agregarUsuario

class MainActivity : ComponentActivity() {
    //@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ETF_bernardo_bravoTheme {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .verticalScroll(rememberScrollState())
//                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
//                )
//                {
//                }
                PantallaLogin()
                //para menu barra abajo
//                val navigatonController = rememberNavController()
//                cincoUsuarios()
//                Scaffold (
//                    bottomBar = { MenuBottomNavigation(navController = navigatonController)}
//                ){
//                    NavigationGraph(navController = navigatonController)
//                }
            }
        }
    }
}

//@Composable
//fun MenuBottomNavigation(navController: NavController) {
//    val pantallas = listOf(
//        Routes.PantallaLogin,
//        Routes.PantallaUsuarios,
//        Routes.PantallaRecuperar,
//        Routes.PantallaRecetas,
//        Routes.Registro
//    )
//
//    BottomNavigation {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        pantallas.forEach { pantalla ->
//            BottomNavigationItem(
//                modifier = Modifier
//                    .background(color = Color.Yellow),
//                selected = currentRoute == pantalla.ruta,
//                onClick = { navController.navigate(pantalla.ruta) },
//                icon = { Icon(pantalla.icono, contentDescription = pantalla.titulo) },
//                label = { Text(text = pantalla.titulo) }
//            )
//
//        }
//    }
//}
//
//
//@Composable
//fun NavigationGraph(navController: NavHostController) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color(0xfffeffb0))
//    )
//    {
//        NavHost(
//            navController = navController,
//            startDestination = Routes.PantallaLogin.ruta
//        ) {
//            composable(Routes.PantallaLogin.ruta) { PantallaLogin() }
//            composable(Routes.PantallaUsuarios.ruta) { PantallaUsuarios() }
//            composable(Routes.PantallaRecuperar.ruta) { PantallaRecuperar() }
//            composable(Routes.PantallaRecetas.ruta) { PantallaRecetas() }
//            composable(Routes.Registro.ruta) { FormularioUsuario() }
//        }
//    }
//}
//
//@Preview(showBackground = true, name = "Prueba Menú")
//@Composable
//fun PreView(){
//    val navigatonController = rememberNavController()
//    MenuBottomNavigation(navController = navigatonController)
//    //NavigationGraph(navController = navigatonController)
//
//}
//
//fun cincoUsuarios() {
//    var usuario = Usuario("mail@mail.com", "prueba")
//    for (i in 1..5) {
//        usuario = Usuario("mail$i@mail.com", "prueba $i")
//        Log.d("usuario", "N°$i")
//    }
//
//    agregarUsuario(usuario)
//
//}
//
//fun seguridadNula() {
//    var nombre: String? = null
//    Log.d("SeguridadNula", nombre?: "El valor es nulo")
//
//    nombre = "Kotlin"
//    nombre?.let { Log.d("SeguridadNula", "Nombre no es nulo: $it") }
//}
//
//
//fun imprimirMensaje(mensaje: String): Unit {
//    Log.d("UnitEjemplo", mensaje)
//}
//
//// El tipo Unit es opcional, así que podrías escribir:
//fun imprimirOtroMensaje(mensaje: String) {
//    Log.d("UnitEjemplo", mensaje)
//}
//
//
//
//
//fun verificarTipo(obj: Any) {
//    if (obj is String) {
//        Log.d("VerificaciónTipo", "El objeto es un String con longitud: ${obj.length}")
//    } else {
//        Log.d("VerificaciónTipo", "El objeto no es un String")
//    }
//
//
//    val obj: Any = 42
//    val texto: String? = obj as? String
//    println(texto)
//}
//
//fun verificarNoEsTipo(obj: Any) {
//    if (obj !is Int) {
//        Log.d("VerificaciónNoEsTipo", "El objeto no es un entero")
//    } else {
//        Log.d("VerificaciónNoEsTipo", "El objeto es un entero")
//    }
//}
//
//fun procesarDato(dato: Any) {
//    when (dato) {
//        is String -> println("Es un String con valor: $dato")
//        is Int -> println("Es un Int con valor: $dato")
//        else -> println("Tipo desconocido")
//    }
//}
//
//fun ejemploVal() {
//    val mensaje = "Hola, Kotlin"
//    Log.d("ValEjemplo", mensaje)
//}
//
//fun ejemploVar() {
//    var contador = 0
//    contador += 1
//    Log.d("VarEjemplo", "El contador es: $contador")
//}
//
//fun usarOperadores() {
//    val a = 10
//    val b = 5
//
//    val suma = a + b
//    Log.d("Operadores", "Suma: $suma")
//
//    val esMayor = a > b
//    Log.d("Operadores", "¿Es a mayor que b?: $esMayor")
//
//    val resultadoElvis = b ?: 0
//    Log.d("Operadores", "Resultado Elvis: $resultadoElvis")
//}
//
//fun condicionalBasico(edad: Int) {
//    if (edad >= 18) {
//        Log.d("Condicional", "Eres mayor de edad")
//    } else {
//        Log.d("Condicional", "Eres menor de edad")
//    }
//}
////
////
///*
//import android.util.Log
//
//fun calcularDescuento(precio: Double) {
//    val descuento: Double
//    val precioFinal: Double
//
//    // Aplicar operadores y condicionales
//    if (precio >= 1000) {
//        descuento = precio * 0.20
//        precioFinal = precio - descuento
//    } else if (precio in 500.0..999.99) {
//        descuento = precio * 0.10
//        precioFinal = precio - descuento
//    } else {
//        descuento = 0.0
//        precioFinal = precio
//    }
//
//    // Mostrar resultados en Log.d
//    Log.d("CalculadoraDescuento", "Precio original: $$precio")
//    Log.d("CalculadoraDescuento", "Descuento aplicado: $$descuento")
//    Log.d("CalculadoraDescuento", "Precio final: $$precioFinal")
//}
//
//// Llamar a la función
//fun main() {
//    calcularDescuento(1200.0)  // Caso: aplica 20% de descuento
//    calcularDescuento(800.0)   // Caso: aplica 10% de descuento
//    calcularDescuento(400.0)   // Caso: sin descuento
//}
//* */
//
//
//fun obtenerMensaje(dia: String): String {
//    return when (dia) {
//        "Lunes" -> "Inicio de semana"
//        "Viernes" -> "Fin de semana cercano"
//        "Sábado", "Domingo" -> "Es fin de semana"
//        else -> "Día normal"
//    }
//}
//
//fun usarWhen(dia: String) {
//    when (dia) {
//        "Lunes" -> Log.d("When", "Inicio de semana")
//        "Viernes" -> Log.d("When", "Fin de semana cercano")
//        "Sábado", "Domingo" -> Log.d("When", "Es fin de semana")
//        else -> Log.d("When", "Día normal")
//    }
//}
//
//
//fun cicloForBasico() {
//    for (i in 1..100) {
//        Log.d("CicloFor", "Número: $i")
//    }
//}
//

