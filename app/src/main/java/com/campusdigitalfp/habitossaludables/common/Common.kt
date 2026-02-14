package com.campusdigitalfp.habitossaludables.common

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.models.Habito
import com.campusdigitalfp.habitossaludables.navigation.NavRoutes
import com.campusdigitalfp.habitossaludables.viewmodel.HabitViewModel
/**
 * Componente `BarraSuperiorComun` que representa la barra
superior de la aplicación.
 * Incluye navegación, menú desplegable y opciones para ges
tionar hábitos.
 *
 * @param navController Controlador de navegación.
 * @param atras Indica si se muestra el botón de regreso.
 * @param isActionMode Indica si el modo de selección múlti
ple está activo.
 * @param selectedHabits Lista de hábitos seleccionados.
 * @param viewModel ViewModel que gestiona los hábitos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorComun(
    navController: NavHostController,

atras: Boolean = true,
isActionMode: MutableState<Boolean> = mutableStateOf(false),
selectedHabits: MutableList<Habito> = mutableListOf(),
viewModel: HabitViewModel = viewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    // Estado del menú desplegable
            TopAppBar(
                // Configuración de los colores de la barra superior
              colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primaryContainer,
                  // Fondo de la barra
                  titleContentColor = MaterialTheme.colorScheme.primary
              // Color del texto del título
                  ),
                title = {
                    Text(text = "Hábitos Saludables")
                    // Título de la barra superior
                },
                navigationIcon = {
                    if (atras) {
                    // Si `atras` es `true`, muestra el botón de retroceso
                    IconButton(onClick = { navController.popBackStack() }) {
                          Icon(
                             imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                              contentDescription = "Atrás"
                                    )
                                }
                    }
                },
                actions = {
                    if (!atras) {
                    // Si `atras` es `false`, muestra el menú desplegable
                    IconButton(onClick = { expanded = true }) {
                      Icon(
                         imageVector = Icons.Filled.Menu,
                         contentDescription = "Menú desplegable"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                    // Cierra el menú cuando se toca fuera de él
                        ) {
                            // Opción para añadir un nuevo hábito
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                  navController.navigate(NavRoutes.NEW.abreviatura)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Añadir hábito",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                },
                                text = { Text("Nuevo") }
                            )
                    // Opción para ir a la pantalla "Acerca de"
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    navController.navigate(NavRoutes.ABOUT.abreviatura)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Info,
                                        contentDescription = "Acerca de",
                                                tint = MaterialTheme.colorScheme.primary
                                    )
                                },
                                text = { Text("Acerca de") }
                            )
                            // Opción para añadir hábitos de ejemplo
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    viewModel.addExampleHabits()
                           // Agrega hábitos predefinidos
                                },
                                leadingIcon = {
                                    Icon(
                                      imageVector = Icons.Filled.Refresh,
                                      contentDescription = "Añadir 10 hábitos",
                                      tint = MaterialTheme.colorScheme.primary
                                    )
                                },
                                text = { Text("Añadir 10 hábitos")
                                }
                            )
                        }
              // Opción para eliminar los hábitos seleccionados (solo si está activo el modo selección múltiple)
                        if (isActionMode.value) {
                            DropdownMenuItem(
                                onClick = {
                     // Llamada a la función para eliminar hábitos seleccionados
                            viewModel.deleteSelectedHabits(selectedHabits)
                         // Vacía la lista de hábitos seleccionados y desactiva el modo selección
                          selectedHabits.clear()
                          isActionMode.value = false
                        },
                         leadingIcon = {
                          Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Borrar seleccionados",
                            tint = MaterialTheme.colorScheme.primary
                            )
                          },
                      text = { Text("Eliminar seleccionados") }
                                )
                                }
                        }
                    }
                    )
                }
