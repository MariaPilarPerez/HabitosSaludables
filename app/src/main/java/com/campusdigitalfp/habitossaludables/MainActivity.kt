package com.campusdigitalfp.habitossaludables

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.campusdigitalfp.habitossaludables.navigation.Navigation
import com.campusdigitalfp.habitossaludables.sampledata.SampleData
import com.campusdigitalfp.habitossaludables.sampledata.SampleData.habitSample
import com.campusdigitalfp.habitossaludables.ui.theme.HabitosSaludablesTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Firebase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = FirebaseFirestore.getInstance() // Inicializa Firestore
        //SampleData.loadData(this)
        enableEdgeToEdge()
        setContent {
            //HabitTrackerApp()
            //HabitosSaludablesTheme {
            //    Navigation()


            }
        }
    }

@Preview
@Composable
fun PreviaHabitos()
{
    HabitosSaludablesTheme() {
        Navigation()
    }
}