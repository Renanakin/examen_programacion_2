package com.hector_delgado.prog_2_examen.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable // Importación necesaria
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hector_delgado.prog_2_examen.viewmodel.MedicionViewModel
import java.util.*

@Composable
fun RegistroMedicionesScreen(
    navController: NavHostController,
    viewModel: MedicionViewModel
) {
    val context = LocalContext.current

    var medidor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var tipoMedicion by remember { mutableStateOf("Agua") }
    var medidorError by remember { mutableStateOf(false) }
    var fechaError by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            val formattedDate = String.format("%02d/%02d/%04d", day, month + 1, year)
            fecha = formattedDate
            fechaError = false
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text("Registro Medidor", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        // Campo para el número del medidor
        OutlinedTextField(
            value = medidor,
            onValueChange = {
                medidor = it
                medidorError = medidor.toIntOrNull()?.let { value -> value <= 0 } ?: true
            },
            isError = medidorError,
            label = { Text("Medidor (Pesos)") },
            placeholder = { Text("Ingrese un valor entero positivo") },
            modifier = Modifier.fillMaxWidth()
        )
        if (medidorError) {
            Text(
                text = "Debe ingresar un número entero positivo.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Campo para la fecha
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            isError = fechaError,
            label = { Text("Fecha") },
            placeholder = { Text("dd/MM/yyyy") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() }, // Solución del error
            readOnly = true
        )
        if (fechaError) {
            Text(
                text = "Debe seleccionar una fecha válida.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botones para seleccionar el tipo de medición
        Text("Medidor de:", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(10.dp))

        // Botones de opción para seleccionar tipo
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = tipoMedicion == "Agua",
                    onClick = { tipoMedicion = "Agua" }
                )
                Text("Agua", modifier = Modifier.padding(start = 8.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = tipoMedicion == "Luz",
                    onClick = { tipoMedicion = "Luz" }
                )
                Text("Luz", modifier = Modifier.padding(start = 8.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = tipoMedicion == "Gas",
                    onClick = { tipoMedicion = "Gas" }
                )
                Text("Gas", modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Botón para registrar la medición
        Button(
            onClick = {
                if (medidor.toIntOrNull() == null || medidor.toInt() <= 0) {
                    medidorError = true
                } else {
                    medidorError = false
                }

                if (fecha.isEmpty()) {
                    fechaError = true
                } else {
                    fechaError = false
                }

                if (!medidorError && !fechaError) {
                    viewModel.agregarMedicion(tipoMedicion, medidor.toFloat(), fecha)
                    navController.navigate("listado")
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = !medidorError && !fechaError && medidor.isNotEmpty() && fecha.isNotEmpty()
        ) {
            Text("Registrar medición")
        }
    }
}
