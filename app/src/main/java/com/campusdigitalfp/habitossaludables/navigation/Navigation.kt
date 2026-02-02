package com.campusdigitalfp.habitossaludables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.habitossaludables.sampledata.SampleData
import com.campusdigitalfp.habitossaludables.screens.AboutScreen
import com.campusdigitalfp.habitossaludables.screens.HabitListScreen
import com.campusdigitalfp.habitossaludables.screens.HabitScreen
import com.campusdigitalfp.habitossaludables.screens.NewHabitScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list")
    {
        composable("list") { HabitListScreen(navController) }
        composable("about") { AboutScreen(navController) }
        composable("details/{habitoId}") { backStackEntry ->
            val habitoId = backStackEntry.arguments?.getString("habitoId")?.toIntOrNull()
            val habito = SampleData.habitSample.find { it.id == habitoId }
            habito?.let { HabitScreen(navController, it) }
        }
        composable("new"){ NewHabitScreen(navController) }
    }
}