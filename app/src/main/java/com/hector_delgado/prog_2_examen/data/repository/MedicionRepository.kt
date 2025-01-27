package com.hector_delgado.prog_2_examen.data.repository

import com.hector_delgado.prog_2_examen.data.Medicion

class MedicionRepository {
    private val mediciones = mutableListOf<Medicion>()

    fun obtenerMediciones(): List<Medicion> = mediciones

    fun agregarMedicion(medicion: Medicion) {
        mediciones.add(medicion)
    }
}
