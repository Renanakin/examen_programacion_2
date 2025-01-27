package com.hector_delgado.prog_2_examen.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hector_delgado.prog_2_examen.ui.ListadoMedicionesScreen
import com.hector_delgado.prog_2_examen.ui.RegistroMedicionesScreen
import com.hector_delgado.prog_2_examen.viewmodel.MedicionViewModel

@Composable
fun AppNavigation(viewModel: MedicionViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "listado") {
        composable("listado") {
            ListadoMedicionesScreen(navController, viewModel)
        }
        composable("registro") {
            RegistroMedicionesScreen(navController, viewModel)
        }
    }
}
