package com.campusdigitalfp.habitossaludables.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.habitossaludables.screens.AboutScreen
import com.campusdigitalfp.habitossaludables.screens.EditHabitScreen
import com.campusdigitalfp.habitossaludables.screens.HabitListScreen
import com.campusdigitalfp.habitossaludables.screens.HabitScreen
import com.campusdigitalfp.habitossaludables.screens.NewHabitScreen
import com.campusdigitalfp.habitossaludables.viewmodel.HabitViewModel
/**
 * Enum `NavRoutes` define las rutas de navegación utilizad
as en la aplicación.
 * Cada constante representa una pantalla y su correspondie
nte abreviatura en la navegación.
 */
enum class NavRoutes(val abreviatura: String) {
    LIST("list"), // Pantalla de lista de hábitos
    ABOUT("about"), // Pantalla "Acerca de"
    DETAILSFULL("details/{habitoId}"),
    // Detalles de un hábito con ID dinámico
    DETAILS("details/"), // Base para la ruta de detalles
    EDITFULL("edit/{habitoId}"),
    // Edición de un hábito con ID dinámico
    EDIT("edit/"), // Base para la ruta de edición
    NEW("new") // Pantalla para crear un nuevo hábito
}

/**
 * `Navigation` administra la navegación de la aplicación.
 * Se encarga de definir las rutas y vincularlas con las pa
ntallas correspondientes.
 *
 * }* @param viewModel `HabitViewModel` proporciona los datos
de los hábitos a las pantallas.
 */
@Composable
fun Navigation(viewModel: HabitViewModel) {
    val navController = rememberNavController() // Controlador de navegación
    // `NavHost` define el punto de entrada y la estructurade navegación de la app.
    NavHost(navController = navController, startDestination
    = NavRoutes.LIST.abreviatura) {
        // Ruta para la pantalla principal de lista de hábitos.
        composable(NavRoutes.LIST.abreviatura) {
            HabitListScreen(navController)
        }
        // Ruta para la pantalla "Acerca de".
        composable(NavRoutes.ABOUT.abreviatura) {
            AboutScreen(navController)
        }
        /**
         * Ruta para ver los detalles de un hábito específi
        co.
         * Extrae el ID del hábito desde los argumentos de
        la navegación y lo busca en la lista de hábitos.
         * Si el hábito existe, se muestra la pantalla `Hab
        itScreen`, de lo contrario, no se hace nada.
         */
        composable(NavRoutes.DETAILSFULL.abreviatura) { backStackEntry ->
            val habitoId = backStackEntry.arguments?.getString("habitoId")
            // Obtiene el ID del hábito desde la URL
            val habits by viewModel.habits.collectAsState()
// Obtiene la lista de hábitos en tiempo real
            val habito = habits.find { it.id == habitoId }
// Busca el hábito con el ID obtenido
            habito?.let { HabitScreen(navController, it) }
// Solo muestra la pantalla si el hábito existe
        }
        /**
         * Ruta para la pantalla de edición de un hábito.
         * Similar a la anterior, obtiene el ID del hábito
        y lo busca en la lista.
         * Si el hábito existe, se muestra la pantalla `Edi
        tHabitScreen`, pasando también el `viewModel`.
         */
        composable(NavRoutes.EDITFULL.abreviatura) { backStackEntry ->
            val habitoId = backStackEntry.arguments?.getString("habitoId")
            val habits by viewModel.habits.collectAsState()
            val habito = habits.find { it.id == habitoId }
            habito?.let { EditHabitScreen(navController, it, viewModel) }
        }
        // Ruta para la pantalla de creación de un nuevo hábito.
        composable(NavRoutes.NEW.abreviatura) {
            NewHabitScreen(navController)
        }
    }
}