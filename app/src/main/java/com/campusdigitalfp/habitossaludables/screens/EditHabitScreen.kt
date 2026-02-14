package com.campusdigitalfp.habitossaludables.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.common.BarraSuperiorComun
import com.campusdigitalfp.habitossaludables.models.Habito
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme

import com.campusdigitalfp.habitossaludables.viewmodel.HabitViewModel
/**
 * Pantalla para editar un hábito existente.
 *
 * Permite al usuario modificar el título y la descripción
de un hábito guardado en Firestore.
 *
 * @param navController Controlador de navegación para camb
iar de pantalla.
 * @param habito Objeto que contiene los datos del hábito s
eleccionado.
 * @param viewModel ViewModel que maneja la actualización d
el hábito en Firestore.
Cloud Firestore 40
 */
@Composable
fun EditHabitScreen(navController: NavHostController, habito: Habito, viewModel: HabitViewModel) {

    // Colores personalizados para los botones
    val cancelButtonBackground = Color(0xFFEEEEEE)
    // Color de fondo del botón "Cancelar"
    val cancelButtonText = Color(0xFF757575)
    // Color del texto del botón "Cancelar"
    val saveButtonBackground = Color(0xFFA5D6A7)
    // Color de fondo del botón "Guardar"
    val saveButtonText = Color(0xFF388E3C)
    // Color del texto del botón "Guardar"
    // Estados que almacenan los valores ingresados por el usuario
    var titulo by remember { mutableStateOf(habito.titulo)}
    var descripcion by remember { mutableStateOf(habito.descripcion) }
    HabitosSaludablesTheme() {
        Scaffold(
            // Agrega la barra superior común a la pantalla
            topBar = { BarraSuperiorComun(navController = navController) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Título de la pantalla de edición
                Text(
                    text = "Editando Hábito: $titulo",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Campo de entrada para editar el título del hábito
                        TextField(
                            value = titulo,
                            onValueChange = { newText -> titulo = newText },
                            label = { Text("Título del hábito") },
                            placeholder = { Text("Ejemplo: Leer 10 páginas al día") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Sentences
                                )
                            )
                    // Campo de entrada para editar la descripción del hábito
                        TextField(
                           value = descripcion,
                           onValueChange = { newText -> descripcion = newText },
                           label = { Text("Descripción del hábito") },
                           placeholder = { Text("Ejemplo: Leer todos los días para mejorar el conocimiento.") },
                           modifier = Modifier.fillMaxWidth(),
                           singleLine = false,
                           keyboardOptions = KeyboardOptions(
                           keyboardType = KeyboardType.Text,
                           capitalization = KeyboardCapitalization.Sentences
                                )
                           )
                           Spacer(modifier = Modifier.height(16.dp))
                        // Botones de acción: Cancelar y Guardar cambios
                    Row {
                    // Botón para cancelar la edición y volver a la pantalla anterior sin guardar cambios
                      Button(
                         onClick = {
                            navController.previousBackStackEntry?.savedStateHandle?.set("key_result",
                                "Operación cancelada")
                                navController.popBackStack()
                                },
                           modifier = Modifier.weight(1f),
                           colors = ButtonDefaults.buttonColors(containerColor = cancelButtonBackground),
                           border = BorderStroke(1.dp, cancelButtonText)
                           ) {
                           Text(text = "Cancelar", color = cancelButtonText)
                            }
                           Spacer(modifier = Modifier.width(8.dp))
                         // Botón para guardar los cambios y actualizar el hábito en Firestore
                          Button(
                             onClick = {
                               val updatedHabit = habito.copy(
                                titulo = titulo,
                                descripcion = descripcion
                                  )
                              viewModel.updateHabit(updatedHabit) // Llama al ViewModel para actualizar Firestore
                              navController.previousBackStackEntry?.savedStateHandle?.set("key_result",
                                  "Hábito modificado con éxito")
                                  navController.popBackStack()
                                 // Regresa a la pantalla anterior
                                 },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = saveButtonBackground),
                                border = BorderStroke(1.dp, saveButtonText)
                               ) {
                                Text(text = "Guardar", color = saveButtonText)
                                            }
                                        }
                                    }
                                    }
                            }
            }