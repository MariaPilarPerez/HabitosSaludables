package com.campusdigitalfp.habitossaludables.screens

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.R
import com.campusdigitalfp.habitossaludables.BarraSuperiorComun
import com.campusdigitalfp.habitossaludables.sampledata.SampleData
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme



@Composable
fun HabitListScreen(navController: NavHostController)
{
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val context = LocalContext.current
    val isActionMode = remember { mutableStateOf(false) }
    val selectedHabits = remember { mutableStateListOf<Habito>() }
    if (savedStateHandle != null) { // Observa el valor de "key_result" del savedStateHandle como un State.
    val result by savedStateHandle.getLiveData<String> ("key_result").observeAsState()
        result?.let {// LaunchedEffect se activa cuando `it` cambia, mostrando el Toast una sola vez.
        LaunchedEffect(it) { Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        } } }
        HabitosSaludablesTheme {
            Scaffold(topBar = { BarraSuperiorComun(navController, false, isActionMode, selectedHabits)
            }) { paddingValues -> VistaListaHabitos( SampleData.habitSample, paddingValues = paddingValues,
                navController, isActionMode, selectedHabits ) } } }


data class Habito(val id: Int, val titulo: String, val descripcion: String)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VistaHabito(habito: Habito, onClick: () -> Unit, onLongClick: () -> Unit, isSelected: Boolean)
   {
    val onLongClick = null
    Row( modifier = Modifier .padding(all = 8.dp)
         .combinedClickable(onClick = onClick, onLongClick = onLongClick) )
     {
         Image(painter = painterResource(if (isSelected)
             R.drawable.selected
             else
                 R.drawable.estilo_de_vida),
             contentDescription = stringResource(R.string.icono_estilo_de_vida),
             modifier = Modifier .size(40.dp) .clip(CircleShape)
                 .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape) )
         Spacer(modifier = Modifier.width(8.dp)) // Mantenemos un registro de si el mensaje está expandido o no en este
         var isExpanded by remember { mutableStateOf(false) } // surfaceColor se actualizará gradualmente de un c olor a otro
         val surfaceColor by animateColorAsState( if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
             label = stringResource(R.string.animaciondecolor), ) // Cambiamos el estado de la variable isExpanded cuando hacemos clic en esta Columna
         Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
             Text( text = habito.titulo, color = MaterialTheme.colorScheme.secondary,
                 style = MaterialTheme.typography.titleSmall )
             Spacer(modifier = Modifier.width(4.dp))
             Surface( shape = MaterialTheme.shapes.medium,shadowElevation = 1.dp, // el color surfaceColor cambiará gradualme nte de primario a surface
                     color = surfaceColor, // animateContentSize cambiará el tamaño de la Superficie gradualmente
                     modifier = Modifier .animateContentSize() .padding(1.dp) )
             { Text( text = habito.descripcion, modifier = Modifier.padding(4.dp), // Si el mensaje está expandido, mostra mos el contenido
             // de lo contrario, solo mostramos la p rimera línea
             maxLines = if (isExpanded) Int.MAX_VALUE else 1, style = MaterialTheme.typography.bodyMedium )
             }
         }
     }
}
@Composable
fun VistaListaHabitos(
    habitos: List<Habito>,
    paddingValues: PaddingValues,
    navController: NavHostController,
    isActionMode: MutableState<Boolean>,
    selectedHabits: MutableList<Habito>)
    {
    Column( modifier = Modifier .padding(paddingValues) .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally )
    {
        LazyColumn( modifier = Modifier .weight(1f)
         // Ocupa el espacio disponible, excepto lo que necesita IrAcercaDe
        .padding(16.dp) )
        { items(habitos) { habito ->
        VistaHabito(habito,
            onClick = {
            if (isActionMode.value) {  // Seleccionar/deseleccionar habito
                if (selectedHabits.contains(habito))
                    { selectedHabits.remove(habito)
                     if (selectedHabits.isEmpty())
                        { isActionMode.value = false // Desactiva action mode
                         }
                    } else { selectedHabits.add(habito) } }
            else {
                 navController.navigate("details/${habito.id}") } },

            onLongClick = {
                isActionMode.value = true
                selectedHabits.add(habito) // Agregar a la selección
            },
            isSelected = selectedHabits.contains(habito) )
        }
    }
    }
}
