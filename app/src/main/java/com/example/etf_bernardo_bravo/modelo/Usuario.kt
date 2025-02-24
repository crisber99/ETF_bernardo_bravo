package com.EFT.etf_bernardo_bravo.modelo

import android.util.Log

data class Usuario(val email: String? = "", val pass: String? = "") {
    companion object{
        private val listaUsuario = mutableListOf<Usuario>()

        fun agregarUsuario(usuario: Usuario){
            listaUsuario.add(usuario)
        }

        fun obtUsuarioMail(email: String): Usuario?{
            return listaUsuario.find { it.email.equals(email, ignoreCase = true) }
        }

        fun listarUsuarios(): List<Usuario>{
            return listaUsuario
        }

    }

}