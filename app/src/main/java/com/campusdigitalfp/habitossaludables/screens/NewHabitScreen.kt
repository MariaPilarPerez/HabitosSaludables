package com.campusdigitalfp.habitossaludables.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.common.BarraSuperiorComun
import com.campusdigitalfp.habitossaludables.models.Habito
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme
import com.campusdigitalfp.habitossaludables.viewmodel.HabitViewModel
/**
 * Pantalla para la creación de un nuevo hábito.
 *
 * @param navController Controlador de navegación para gest
ionar la navegación entre pantallas.
 * @param viewModel ViewModel que maneja la lógica de los h
ábitos.
 */
@Composable
fun NewHabitScreen(navController: NavHostController, viewModel: HabitViewModel = viewModel()) {
    // Definimos colores personalizados para los botones
    val cancelButtonBackground = Color(0xFFEEEEEE) // Fondo del botón "Cancelar"
    val cancelButtonText = Color(0xFF757575) // Color del texto del botón "Cancelar"
    val saveButtonBackground = Color(0xFFA5D6A7) // Fondo del botón "Guardar"
    val saveButtonText = Color(0xFF388E3C) // Color del texto del botón "Guardar"
    // Estados para almacenar el título y la descripción ingresados por el usuario
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    HabitosSaludablesTheme() {
        Scaffold(
            topBar = { BarraSuperiorComun(navController) }
// Agrega la barra superior común
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Título de la pantalla
                Text(
                    text = "Nuevo Hábito",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Campo de texto para ingresar el título del hábito
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
                            // Campo de texto para ingresar la descripción del hábito
                            TextField(
                                    value = descripcion,
                                    onValueChange = { newText -> descripcion = newText },
                                    label = { Text("Descripción del hábito") },
                                    placeholder = { Text("Ejemplo: Leer 10 páginas para mejorar mi conocimiento.") },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = false,
                                    keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    capitalization = KeyboardCapitalization.Sentences
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                        // Botones de acción: Cancelar y Guardar
                                        Row {
                                     // Botón para cancelar la operación y volver atrás sin guardar cambios
                                          Button(
                                            onClick = {
                                               navController.previousBackStackEntry?.savedStateHandle?.set("key_result",
                                                   "Operación cancelada")
                                                            navController.popBackStack()
                                                // Regresa a la pantalla anterior
                                                },
                                                modifier = Modifier.weight(1f),
                                                colors = ButtonDefaults.buttonColors(containerColor = cancelButtonBackground),
                                                border = BorderStroke(1.dp, cancelButtonText)
                                            ) {
                                                Text(text = "Cancelar", color = cancelButtonText)
                                            }
                                            Spacer(modifier = Modifier.width(8.dp))
                                            // Botón para guardar el nuevo hábito en la base de datos
                                            Button(
                                                onClick = {
                                                    if (titulo.isNotBlank() && descripcion.isNotBlank()) {
                                                        // Verifica que los campos no estén vacíos
                                                        viewModel.addHabit(Habito(titulo = titulo, descripcion = descripcion))
                                                        navController.previousBackStackEntry?.savedStateHandle?.set("key_result", "Hábito creado con éxito")
                                                                    navController.popBackStack()
                                                    // Regresa a la pantalla de lista de hábitos
                                                    }
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