package com.hector_delgado.prog_2_examen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hector_delgado.prog_2_examen.R
import com.hector_delgado.prog_2_examen.data.Medicion
import com.hector_delgado.prog_2_examen.viewmodel.MedicionViewModel

@Composable
fun ListadoMedicionesScreen(
    navController: NavHostController,
    viewModel: MedicionViewModel
) {
    // Observar los cambios en la lista de mediciones desde el ViewModel
    val mediciones by viewModel.mediciones.collectAsState(initial = emptyList())
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("registro") }) {
                Icon(Icons.Filled.ElectricBolt, contentDescription = stringResource(R.string.add_button))
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Menú desplegable para cambiar idioma
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextButton(onClick = { expanded = true }) {
                    Text("Idioma / Language")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Español") },
                        onClick = {
                            LanguageManager.setLocale(context, "es")
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("English") },
                        onClick = {
                            LanguageManager.setLocale(context, "en")
                            expanded = false
                        }
                    )
                }
            }

            // Título de la pantalla
            Text(
                text = stringResource(R.string.listado_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            // Lista de mediciones
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(mediciones) { medicion ->
                    FilaMedicion(medicion = medicion)
                }
            }
        }
    }
}

@Composable
fun FilaMedicion(medicion: Medicion) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.ElectricBolt,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = medicion.tipo,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "${medicion.valor} (${medicion.fecha})",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
