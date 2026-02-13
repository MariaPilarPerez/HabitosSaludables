package com.campusdigitalfp.habitossaludables.screens

import android.widget.Toast
import com.campusdigitalfp.habitossaludables.models.Habito


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.R
import com.campusdigitalfp.habitossaludables.BarraSuperiorComun
import com.campusdigitalfp.habitossaludables.sampledata.SampleData.habitSample
import com.campusdigitalfp.habitossaludables.navigation.NavRoutes
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme
import com.campusdigitalfp.habitossaludables.viewmodel.HabitViewModel
/**
 * Pantalla principal que muestra la lista de hábitos almac
enados en Firestore.
 *
 * @param navController Controlador de navegación para camb
Cloud Firestore 21
iar entre pantallas.
 * @param viewModel ViewModel que gestiona los hábitos y su
estado.
 */
@Composable
fun HabitListScreen(navController: NavHostController, viewModel: HabitViewModel = viewModel()) {
    val isActionMode = remember { mutableStateOf(false) }
// Indica si el modo de selección múltiple está activo.
    val selectedHabits = remember { mutableStateListOf<Habito>() } // Lista de hábitos seleccionados.
    val habitos by viewModel.habits.collectAsState()
    // Estado de los hábitos observados desde el ViewModel.
    // Manejo de mensajes temporales a través del NavController.
    navController.currentBackStackEntry?.savedStateHandle?.
    let { savedStateHandle ->
        val context = LocalContext.current
        val result by savedStateHandle.getLiveData<String>  ("key_result").observeAsState()
        LaunchedEffect(result) {
            result?.let{Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
    HabitosSaludablesTheme() {
        Scaffold(
            topBar = {
                BarraSuperiorComun(
                    navController = navController,
                    atras = false,
                    isActionMode = isActionMode,
                    selectedHabits = selectedHabits

                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                VistaListaHabitos(
                    habitos,
                    navController,
                    isActionMode,
                    selectedHabits
                )
            }
        }
    }
}
/**
 * Vista que representa un solo hábito en la lista.
 *
 * @param habito Objeto que contiene la información del háb
ito.
 * @param onClick Acción al hacer clic en el hábito.
 * @param onLongClick Acción al hacer una pulsación larga.
 * @param isSelected Indica si el hábito está seleccionado
en modo selección múltiple.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VistaHabito(
    habito: Habito,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    isSelected: Boolean
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
    ) {
        Image(
            painter = painterResource(if (isSelected) R.drawable.comprobado else R.drawable.estilo_de_vida),
            contentDescription = "Icono estilo de vida",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false)
        } // Controla si se expande la descripción.
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "AnimacionDeColor"
        )
        Column(modifier = Modifier.clickable { isExpanded =
            !isExpanded }) {
            Text(
                text = habito.titulo,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = habito.descripcion,
                    modifier = Modifier.padding(4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
/**
 * Lista de hábitos representados en un LazyColumn.
 *
 * @param habitos Lista de hábitos a mostrar.
 * @param navController Controlador de navegación para camb
iar entre pantallas.
 * @param isActionMode Indica si la selección múltiple está
activa.
 * @param selectedHabits Lista de hábitos seleccionados en
modo selección múltiple.
 */
@Composable
fun VistaListaHabitos(
    habitos: List<Habito>,
    navController: NavHostController,
    isActionMode: MutableState<Boolean>,
    selectedHabits: MutableList<Habito>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(habitos) { habito ->
            VistaHabito(
                habito,
                onClick = {
                    if (isActionMode.value) {
                        // Maneja la selección y deselección en modo selección múltiple.
                        if (selectedHabits.contains(habito)) {
                            selectedHabits.remove(habito)
                            if (selectedHabits.isEmpty()) {
                                isActionMode.value = false
                            }
                        } else {
                            selectedHabits.add(habito)
                        }
                    } else {
                        // Navega a la pantalla de detalles del hábito seleccionado.
                        navController.navigate(NavRoutes.DETAILS.abreviatura + habito.id)
                    }
                },
                onLongClick = {
                    // Activa el modo selección múltiple y agrega el hábito seleccionado.
                    isActionMode.value = true
                    selectedHabits.add(habito)
                },
                isSelected = selectedHabits.contains(habito)
            )
        }
    }
}
