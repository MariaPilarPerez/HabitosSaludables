package com.campusdigitalfp.habitossaludables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.habitossaludables.screens.AboutScreen
import com.campusdigitalfp.habitossaludables.screens.HabitlistScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination="list")
    {composable("list"){ HabitlistScreen(navController) }
    composable("about"){ AboutScreen((navController)) }}
}