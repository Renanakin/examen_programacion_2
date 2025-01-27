package com.hector_delgado.prog_2_examen.viewmodel

import androidx.lifecycle.ViewModel
import com.hector_delgado.prog_2_examen.data.Medicion
import com.hector_delgado.prog_2_examen.data.repository.MedicionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MedicionViewModel : ViewModel() {
    private val repository = MedicionRepository()

    // Usamos MutableStateFlow para que sea observable por la UI
    private val _mediciones = MutableStateFlow<List<Medicion>>(emptyList())
    val mediciones: StateFlow<List<Medicion>> = _mediciones

    fun obtenerMediciones(): List<Medicion> = repository.obtenerMediciones()

    fun agregarMedicion(tipo: String, valor: Float, fecha: String) {
        val nuevaMedicion = Medicion(tipo, valor, fecha)
        repository.agregarMedicion(nuevaMedicion)

        // Actualizamos el estado de _mediciones
        _mediciones.value = repository.obtenerMediciones()
    }
}
