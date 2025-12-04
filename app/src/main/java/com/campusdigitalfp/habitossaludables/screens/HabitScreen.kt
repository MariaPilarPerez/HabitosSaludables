package com.campusdigitalfp.habitossaludables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.R
import com.campusdigitalfp.habitossaludables.BarraSuperiorComun
@Composable
fun HabitScreen(navController: NavHostController, habito: Habito) {
    Scaffold(topBar = { BarraSuperiorComun(navController) })
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Text(
                text = habito.titulo, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = TextAlign.Justify
            )
            Text(
                text = habito.descripcion, style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(onClick = { navController.popBackStack() }) {
                Text(text = stringResource(R.string.volver))
            }
        }
    }
}