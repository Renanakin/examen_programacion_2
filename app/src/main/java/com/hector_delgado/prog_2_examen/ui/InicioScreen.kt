package com.hector_delgado.prog_2_examen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen(goToListado: () -> Unit, goToRegistro: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Menú Principal", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { goToListado() }, modifier = Modifier.padding(10.dp)) {
            Text("Ver Mediciones")
        }

        Button(onClick = { goToRegistro() }, modifier = Modifier.padding(10.dp)) {
            Text("Registrar Mediciones")
        }
    }
}
