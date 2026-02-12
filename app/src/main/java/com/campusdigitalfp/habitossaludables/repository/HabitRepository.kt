package com.campusdigitalfp.habitossaludables.repository

import android.util.Log
import com.campusdigitalfp.habitossaludables.models.Habito
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
/**
 * HabitRepository maneja todas las operaciones relacionada
s con los hábitos en Firestore.
 * Este repositorio actúa como una capa intermedia entre la
base de datos y el ViewModel.
 */
class HabitRepository {
    // Obtiene una instancia de Firestore.
    private val db = FirebaseFirestore.getInstance()
    // Referencia a la colección "habitos" en Firestore don
    //de se almacenan los hábitos.
    private val habitsCollection = db.collection("habitos")
    /**
     * Agrega un nuevo hábito a Firestore.
     * La función es suspendida para ejecutarse en un hilo
    de fondo sin bloquear la UI.
     */
    suspend fun addHabit(habit: Habito) {
        habitsCollection.add(habit).await()
    }
    /**
     * Recupera todos los hábitos almacenados en Firestore
    y los devuelve como una lista.
     * Utiliza `await()` para esperar a que Firestore devue
    lva los datos antes de continuar.
     * Si ocurre un error, devuelve una lista vacía en luga
    r de lanzar una excepción.
     */
    suspend fun getHabits(): List<Habito> {

        return try {
            val snapshot = habitsCollection.get().await()
// Obtiene los documentos de Firestore
            snapshot.documents.mapNotNull { it.toObject(Habito::class.java)?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList() // En caso de error, devuelve una lista vacía para evitar fallos en la UI
        }
    }
    /**
     * Actualiza un hábito existente en Firestore.
     * Usa `set()` para sobrescribir los datos en Firestore
    con la nueva información del hábito.
     */
    suspend fun updateHabit(habit: Habito) {
        habitsCollection.document(habit.id).set(habit).await()
    }
    /**
     * Elimina un hábito específico de Firestore dado su I
    D.
     */
    suspend fun deleteHabit(habitId: String) {
        habitsCollection.document(habitId).delete().await()
    }
    /**
     * Escucha cambios en tiempo real en la colección de há
    bitos de Firestore.
     * Cada vez que se detecta un cambio en Firestore (adic
    ión, modificación o eliminación),
     * la lista de hábitos se actualiza y se pasa al callba
    ck `onUpdate`.
     */
    fun listenToHabitsUpdates(onUpdate: (List<Habito>) -> Unit) {
        habitsCollection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("HS_error", "Error al obtener hábitos: ${exception.message}")
                return@addSnapshotListener
            }
            // Convierte los documentos de Firestore a objetos `Habito`
            val habits = snapshot?.documents?.mapNotNull {
                it.toObject(Habito::class.java)?.copy(id = it.id) } ?: emptyList()
            onUpdate(habits) // Llama al callback con la lista actualizada de hábitos
        }
    }
    /**
     * Agrega múltiples hábitos en una sola operación usand
    o `WriteBatch`.
     * Esto optimiza el rendimiento al reducir el número de
    peticiones individuales a Firestore.
     */
    suspend fun addMultipleHabits(habits: List<Habito>) {
        val batch = db.batch() // Crea un batch para realizar múltiples operaciones en una sola transacción
        habits.forEach { habit ->
            val newDocRef = habitsCollection.document() //            Crea un nuevo ID para cada hábito
            batch.set(newDocRef, habit.copy(id = newDocRef.
            id)) // Guarda el hábito con su nuevo ID
        }
        try {
            batch.commit().await() // Ejecuta la operación en Firestore
                    Log.i("HS_info", "10 hábitos añadidos correctamente a Firestore")
        } catch (e: Exception) {
            Log.e("HS_error", "Error al añadir hábitos: ${e.message}")
        }
    }
    /**
     * Elimina múltiples hábitos en una sola transacción us
    ando `WriteBatch`.
     * Reduce la cantidad de operaciones de red, lo que mej
    ora la eficiencia de la base de datos.
     */
    suspend fun deleteMultipleHabits(habits: List<Habito>)
    {
        val batch = db.batch() // Crea una operación en lote
        habits.forEach { habit ->
            habit.id.let { habitId ->
                batch.delete(habitsCollection.document(habitId)) // Marca cada hábito para eliminación
            }
        }
        try {
            batch.commit().await() // Ejecuta la eliminación de todos los hábitos en una sola operación
            Log.i("HS_info", "Hábitos eliminados correctamente de Firestore")
        } catch (e: Exception) {
            Log.e("HS_error", "Error al eliminar hábitos: ${e.message}")
        }
    }
}
