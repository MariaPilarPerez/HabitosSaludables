package com.campusdigitalfp.habitossaludables.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.common.BarraSuperiorComun
import com.campusdigitalfp.habitossaludables.models.Habito
import com.campusdigitalfp.habitossaludables.navigation.NavRoutes
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme
import com.campusdigitalfp.habitossaludables.viewmodel.HabitViewModel

/**
 * Pantalla que muestra los detalles de un hábito.
 *
 * Permite al usuario visualizar su información, marcarlo c
omo completado,
 * editarlo o eliminarlo.
 *
 * @param navController Controlador de navegación.
 * @param habito Objeto que contiene los datos del hábito.
 * @param viewModel ViewModel para gestionar los hábitos.
 */
@Composable
fun HabitScreen(
    navController: NavHostController,
    habito: Habito,
    viewModel: HabitViewModel = viewModel()
) {
    var isCompleted by remember { mutableStateOf(habito.completado) }
    // Estado local para marcar el hábito como completado
    HabitosSaludablesTheme() {
        Scaffold(
            topBar = { BarraSuperiorComun(navController = navController) }
        // Barra superior
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título del hábito
                Text(
                    text = habito.titulo,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )
                // Descripción del hábito
                Text(
                    text = habito.descripcion,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Justify
                )
                // Botón para marcar el hábito como completado
                Button(
                    onClick = {
                        isCompleted = !isCompleted
                        // Cambia el estado local
                       viewModel.updateHabit(habito.copy(completado = isCompleted))
                              // Actualiza Firestore
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCompleted)
                            MaterialTheme.colorScheme.secondary
                        else MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) {
                    Text(text = if (isCompleted) "Hábito Completado ✅"
                    else "Marcar como Completado")
                }
                // Botón para editar el hábito

                Button(
                    onClick = { navController.navigate(NavRoutes.EDIT.abreviatura + habito.id) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(text = "Editar")
                }
                // Botón para eliminar el hábito
                Button(
                    onClick = {
                        habito.id.let { viewModel.deleteHabit(it) }
                        // Elimina el hábito
                        navController.navigate(NavRoutes.LIST.abreviatura)
                       // Regresa a la lista
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) {
                    Text(text = "Eliminar")
                }
                // Botón para volver atrás
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Volver")
                }
            }
        }
    }
}