package com.campusdigitalfp.habitossaludables.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.R
import com.campusdigitalfp.habitossaludables.BarraSuperiorComun
import com.campusdigitalfp.habitossaludables.sampledata.SampleData.habitSample
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme

@Composable
fun NewHabitScreen(navController: NavHostController)
{
    val context = LocalContext.current
    val cancelButtonBackground = Color(0xFFEEEEEE)
    val cancelButtonText = Color(0xFF757575)
    val saveButtonBackground = Color(0xFFA5D6A7)
    val saveButtonText = Color(0xFF388E3C)
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    HabitosSaludablesTheme {
        Scaffold(topBar = { BarraSuperiorComun(navController)
        })
        { paddingValues ->
            Column(modifier = Modifier .padding(paddingValues) .padding(16.dp))
            {
                Text(
                    text = stringResource(R.string.nuevo_h_bito),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                TextField(
                    value = titulo,
                    onValueChange = { newText -> titulo = newText },
                    label = { Text(stringResource(R.string.t_tulo_del_h_bito)) },
                    placeholder = { Text(stringResource(R.string.t_tulo_del_h_bito)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
                TextField(
                    value = descripcion, onValueChange = { newText -> descripcion = newText },
                    label = { Text(stringResource(R.string.descripci_n_del_h_bito)) },
                    placeholder = { Text(stringResource(R.string.descripci_n_del_h_bito)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(
                        onClick = {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "key_result", context.getString(R.string.operaci_n_cancelada)
                            )
                            navController.popBackStack()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = cancelButtonBackground),
                        border = BorderStroke(1.dp, cancelButtonText)
                    )
                    {
                        Text(text = stringResource(R.string.cancelar), color = cancelButtonText)
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val nuevoHabito = Habito(
                                id = habitSample.size,
                                titulo = titulo, descripcion = descripcion
                            )
                            habitSample.add(nuevoHabito)
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "key_result",
                                context.getString(R.string.h_bito_creado_con_xito)
                            )
                            navController.popBackStack()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = saveButtonBackground),
                        border = BorderStroke(1.dp, saveButtonText)
                    )
                    {
                        Text(text = stringResource(R.string.guardar), color = saveButtonText)
                    }
                }
            }
        }
    }
}

