package com.campusdigitalfp.habitossaludables.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.R
import com.campusdigitalfp.habitossaludables.VistaListaHabitos
import com.campusdigitalfp.habitossaludables.sampledata.SampleData.habitSample
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme

/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitosSaludablesTheme {
                VistaHabito(Habito("Comer Saludable","Objetivo de comer saludable todos los dias de la semana"))
            }
        }
    }
} */

@Composable
fun HabitlistScreen(navController: NavHostController){
    HabitosSaludablesTheme() {
        Scaffold() {paddingValues ->
            //modificación
            VistaListaHabitos(habitSample)
        }
    }
}
data class Habito (val titulo: String, val descripcion: String)

//Esta es la función principal, en la que organizamos todos los elementos.

@Composable
fun VistaHabito(Habito: Habito) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.estilo_de_vida),
            contentDescription = "Icono estilo de vida",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(width = 1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
// Mantenemos un registro de si el mensaje está expandido o no
        var isExpanded by remember {mutableStateOf(false)}
        //Cambiamos el estado de la variable isExpanded cuando hacemos clic en esta columna
        Column (modifier = Modifier.clickable {isExpanded = !isExpanded}){
            Text(
                text = Habito.titulo,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = Habito.descripcion,
                    modifier = Modifier.padding(4.dp),
                    // Si el mensaje está expandido mostramos
                    // todo el contenido, sino solo la primera linea
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}

@Composable
fun VistaListaHabitos(habitos: List<Habito>, paddingValues: PaddingValues, navController: NavHostController){
    LazyColumn (modifier = Modifier.padding(paddingValues)){
        items(habitos){
                habito->VistaHabito(habito)

        }

    }
    IrAcercaDe( navController = navController)
}
@Composable
fun IrAcercaDe(navController: NavHostController){
    Button(onClick= {navController.navigate("about")}){
        Text("Acerca de")
    }
}



@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name="Dark Mode")


@Preview(showBackground = true, name="Ligth Mode")
@Composable
fun PreviewListaHabitos() {
    HabitosSaludablesTheme {
        VistaListaHabitos(habitos = habitSample,)
    }

}