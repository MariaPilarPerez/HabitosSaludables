package com.campusdigitalfp.habitossaludables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled. ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.R import com.campusdigitalfp.habitossaludables.sampledata.SampleData
import com.campusdigitalfp.habitossaludables.screens.Habito

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun BarraSuperiorComun(navController: NavHostController,
     atras: Boolean = true, isActionMode: MutableState<Boolean> = remember { mutableStateOf(false) },
     selectedHabits: MutableList<Habito> = remember { mutableStateListOf<Habito>() })
{ var expanded by remember { mutableStateOf(false) }
    TopAppBar( title = { Text(text = stringResource(R.string.app_name)) },
        navigationIcon = {
            if (atras) {
                IconButton(onClick = { navController.popBackStack () })
                {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
// Icono de retroceso
                        contentDescription = "Atrás" // Des cripción del ícono (para accesibilidad)
                    )
                }
            }
        } ,
// Menú desplegable en la esquina superior derecha
     actions = { if (!atras) { IconButton(onClick = { expanded = true })
     { Icon( imageVector = Icons.Filled.Menu, contentDescription = stringResource (R.string.men_de_opciones) ) }
         DropdownMenu( expanded = expanded, onDismissRequest = { expanded = false } )
         { DropdownMenuItem( onClick = { navController.navigate ("new") },
             leadingIcon = { Icon( imageVector = Icons.Filled. Add,
                 contentDescription = stringResource(R.string.a_adir_nuevo_h_bito),
                 tint = MaterialTheme.colorScheme.primary
             )
                           },
             text = { Text("Nuevo") }
         )
             DropdownMenuItem(onClick = { navController.navigate ("about") },
                 leadingIcon = {Icon( imageVector = Icons.Filled. Info,
                     contentDescription = stringResource(R.string.acerca_de),
                     tint = MaterialTheme.colorScheme.primary )
                 },
                 text = { Text(stringResource(R.string.acerca_de)) }
             )
         }
         //Agregar opción de eliminar si está en modo de acción
         if (isActionMode.value) {
             DropdownMenuItem(onClick = { // Acción para eliminar element os seleccionados
                 SampleData.habitSample.removeAll(selectedHabits)
                 selectedHabits.clear()
                 isActionMode.value = false},
                 leadingIcon = { Icon( imageVector = Icons.Filled. Delete,
                     contentDescription = stringResource(R.string.eliminar_seleccionados),
                     tint = MaterialTheme.colorScheme.primary ) },
                 text = { Text(stringResource(R.string.eliminar_seleccionados))
                 }
             )
         }
     }
     }
    )
}