package com.hector_delgado.prog_2_examen.data

data class Medicion(
    val tipo: String, // Ejemplo: Agua, Luz, Gas
    val valor: Float, // Valor registrado en el medidor
    val fecha: String // Fecha en formato dd/MM/yyyy
)
