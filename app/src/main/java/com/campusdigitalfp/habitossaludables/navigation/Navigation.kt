package com.campusdigitalfp.habitossaludables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.habitossaludables.screens.AboutScreen
import com.campusdigitalfp.habitossaludables.screens.HabitListScreen

@Composable
fun Navigation()
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list")
    {
        composable("list"){HabitListScreen(navController)}
        composable("about"){AboutScreen(navController)}
    }
}