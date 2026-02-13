package com.campusdigitalfp.habitossaludables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.campusdigitalfp.habitossaludables.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.campusdigitalfp.habitossaludables.viewmodel.HabitViewModel
import com.google.firebase.firestore.MemoryCacheSettings
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
            override fun onCreate(savedInstanceState: Bundle?) {
                 super.onCreate(savedInstanceState)

        // Inicializa Firebase Firestore para la aplicación.
        val db = FirebaseFirestore.getInstance()
        // Configura Firestore sin almacenamiento en caché persistente.
        val settings = FirebaseFirestoreSettings.Builder()
            .setLocalCacheSettings(
                MemoryCacheSettings.newBuilder().build()
            // Usa solo memoria RAM, sin guardar datos en disco.
            ).build()
        db.firestoreSettings = settings
        // Aplica la configuración a Firestore.
        enableEdgeToEdge()
        setContent {
            // Crea una instancia de `HabitViewModel` usando `viewModel()`.
            val viewModel: HabitViewModel = viewModel()
            // Inicia la navegación en la aplicación y pasa el `viewModel`.
            Navigation(viewModel)
        }
    }
}