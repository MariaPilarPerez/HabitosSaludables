package com.campusdigitalfp.habitossaludables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.R
import com.campusdigitalfp.habitossaludables.common.BarraSuperiorComun
@Composable fun AboutScreen(navController: NavHostController)
{ Scaffold (topBar = { BarraSuperiorComun(navController) })
{ paddingValues ->
    Column( modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top )
        { // Logo de la aplicación
        Image( painter = painterResource(id = R.drawable.estilo_de_vida),
            contentDescription = stringResource(R.string.logo_de_la_aplicaci_n),
            modifier = Modifier.size(120.dp) )
            Spacer(modifier = Modifier.height(16.dp)) // Nombre de la aplicación
            Text( text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp)) // Versión de la aplicación
            Text( text = stringResource(R.string.versi_n_1_0_0),
            style = MaterialTheme.typography.bodyMedium )
            Spacer(modifier = Modifier.height(16.dp))
            // Breve descripción de la aplicación
            Text( text = stringResource(R.string.definicion_app),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp),
                   textAlign = TextAlign.Justify ) // Alinea el texto al centro
            Spacer(modifier = Modifier.height(16.dp)) // Información del equipo de desarrollo
            Text( text = "Desarrollado por Esther Agulló",
                style = MaterialTheme.typography.bodyMedium )
            Spacer(modifier = Modifier.height(16.dp)) // Botón para volver atrás
            Button( onClick = { navController.popBackStack() },
                modifier = Modifier.padding(vertical = 8.dp) ) { Text(text = "Volver")
            }
    }
}
}