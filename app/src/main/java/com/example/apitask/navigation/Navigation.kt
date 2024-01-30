package com.example.apitask.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitask.composables.AddPetScreen
import com.example.apitask.composables.PetListComposableScreen
import com.example.apitask.petViewModel.PetViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    var viewModel: PetViewModel = viewModel()
    var navController = rememberNavController()

    Scaffold(modifier = Modifier
        .background(Color(color = 0xFFE68A00)),
        topBar = {
            TopAppBar(title = { Text(text = "My Pet List")})
                 },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addPet") }) {
                Text(text = "+")
            }
        }
    ) { padding ->

        NavHost(navController = navController, startDestination = "petList") {
            composable("petList") {
                PetListComposableScreen(viewModel, padding, modifier = Modifier)
            }
            composable("addPet") {
                AddPetScreen(viewModel,
                    back={
                    navController.popBackStack()
                })
            }
        }
    }
}
