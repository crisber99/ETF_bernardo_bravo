package com.EFT.etf_bernardo_bravo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DBname, null, DBversion) {

    companion object{
        private const val DBname = "usuarios.db"
        private const val DBversion = 1

        const val Tusuario = "usuarios"
        const val Crut = "rut"
        const val Cnombre = "nombre"
        const val CapPaterno = "ap_paterno"
        const val CapMaterno = "ap_materno"
        const val Cregion = "region"
        const val Cdireccion = "direccion"
        const val Ccomuna = "comuna"

        const val Tregiones = "regiones"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTablaQuery = """
            CREATE TABLE $Tusuario(
            $Crut text primary key,
            $Cnombre text not null,
            $CapPaterno text not null,
            $CapMaterno text not null,
            $Cregion text not null,
            $Cdireccion text not null,
            $Ccomuna text not null
            )
        """.trimIndent()

        if (db != null) {
            db.execSQL(createTablaQuery)
        }

        val createRegionesTable = """
            CREATE TABLE $Tregiones(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            region TEXT NOT NULL,
            abreviatura TEXT NOT NULL,
            capital TEXT NOT NULL
            )
        """.trimIndent()
        if (db != null) {
            db.execSQL(createRegionesTable)
        }

        if (db != null) {
            insertaRegionInicial(db)
        }
    }

    private fun insertaRegionInicial(db: SQLiteDatabase){
        val regiones = listOf(
            Triple("Arica y Parinacota", "AP", "Arica"),
            Triple("Tarapacá", "TA", "Iquique"),
            Triple("Antofagasta", "AN", "Antofagasta"),
            Triple("Atacama", "AT", "Copiapó"),
            Triple("Coquimbo", "CO", "La Serena"),
            Triple("Valparaiso", "VA", "Valparaíso"),
            Triple("Metropolitana de Santiago", "RM", "Santiago"),
            Triple("Libertador General Bernardo O'Higgins", "OH", "Rancagua"),
            Triple("Maule", "MA", "Talca"),
            Triple("Ñuble", "NB", "Chillán"),
            Triple("Biobío", "BI", "Concepción"),
            Triple("La Araucanía", "IAR", "Temuco"),
            Triple("Los Ríos", "LR", "Valdivia"),
            Triple("Los Lagos", "LL", "Puerto Montt"),
            Triple("Aysén del General Carlos Ibáñez del Campo", "AI", "Coyhaique"),
            Triple("Magallanes y de la Antártica Chilena", "MG", "Punta Arenas")
        )

        regiones.forEach{(region, abreviatura, capital) ->
            val values = ContentValues().apply {
                put("region", region)
                put("abreviatura", abreviatura)
                put("capital", capital)
            }
            db.insert(Tregiones, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $Tusuario")
        }
        onCreate(db)
    }

    fun insertarUsuario(
        rut: String,
        nombre: String,
        appaterno: String,
        apmaterno: String,
        region: String,
        direccion: String,
        comuna: String
    ):Long{
        val db = this.writableDatabase
        val contextValues = ContentValues().apply {
            put(Crut, rut)
            put(Cnombre, nombre)
            put(CapPaterno, appaterno)
            put(CapMaterno, apmaterno)
            put(Cregion, region)
            put(Cdireccion, direccion)
            put(Ccomuna, comuna)
        }

        val result = db.insert(Tusuario, null, contextValues)
        db.close()
        return result
    }

    fun obtRegiones(): List<String>{
        val regiones = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("Select region from $Tregiones order by id", null)

        if(cursor.moveToFirst()){
            do{
                regiones.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return regiones
    }
}