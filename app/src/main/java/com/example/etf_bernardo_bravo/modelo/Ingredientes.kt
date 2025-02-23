package com.EFT.etf_bernardo_bravo.modelo

data class Ingredientes(val nomIngrediente: String) {
    companion object{
        private val listaIngrediente = mutableListOf<Ingredientes>()
    }
}