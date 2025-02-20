package com.example.etf_bernardo_bravo.modelo


data class Recetas(val id: Int, val nomReceta: String, val ingredientes: Ingredientes) {
    companion object {
        private val listaReceta = mutableListOf<Recetas>()

        fun agregarReceta(Receta: Recetas){
            listaReceta.add(Receta)
        }

        fun obtRecetaMail(receta: String): Recetas?{
            return listaReceta.find { it.nomReceta.equals(receta, ignoreCase = true) }
        }

        fun listarRecetas(): List<Recetas>{
            return listaReceta
        }
    }
}