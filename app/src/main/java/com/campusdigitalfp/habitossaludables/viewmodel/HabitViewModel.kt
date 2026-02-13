package com.campusdigitalfp.habitossaludables.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdigitalfp.habitossaludables.models.Habito
import com.campusdigitalfp.habitossaludables.repository.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
/**
 * ViewModel que gestiona la lógica de la aplicación relaci
onada con los hábitos.
 * Se comunica con el repositorio (`HabitRepository`) para
realizar operaciones en Firestore.
 */
class HabitViewModel : ViewModel() {
    // Instancia del repositorio que maneja las operaciones de Firestore.
    private val repository = HabitRepository()
    // `_habits` almacena la lista de hábitos de forma interna y permite su modificación.
    private val _habitos = MutableStateFlow<List<Habito>>(emptyList())
    // `habits` expone los hábitos a la UI sin permitir modificaciones directas.
    val habits: StateFlow<List<Habito>> get() = _habitos
    // Se ejecuta al inicializar el ViewModel y activa la escucha de cambios en Firestore.

    init {
        listenToHabits()
    }
    /**
     * Escucha los cambios en Firestore en tiempo real.
     * Cuando se detecta un cambio en la colección de hábit
    os, la UI se actualiza automáticamente.
     */
    private fun listenToHabits() {
        repository.listenToHabitsUpdates { updatedHabits ->
            _habitos.value = updatedHabits
        }
    }
    /**
     * Recupera la lista de hábitos desde Firestore y actua
    liza `_habits`.
     * Se ejecuta dentro de `viewModelScope.launch` para ev
    itar bloquear la UI.
     */
    private fun fetchHabits() {
        viewModelScope.launch {
            _habitos.value = repository.getHabits()
        }
    }
    /**
     * Agrega un nuevo hábito a Firestore y actualiza la li
    sta de hábitos.
     */
    fun addHabit(habito: Habito) {
        viewModelScope.launch {
            repository.addHabit(habito)
            fetchHabits() // Recarga la lista después de añadir un nuevo hábito.
        }
    }
    /**
     * Actualiza un hábito en Firestore y recarga la lista
    de hábitos.
     */
    fun updateHabit(habito: Habito) {
        viewModelScope.launch {
            repository.updateHabit(habito)
            fetchHabits()
        }
    }
    /**
     * Elimina un hábito de Firestore según su ID y actuali
    za la lista.
     */
    fun deleteHabit(habitId: String) {
        viewModelScope.launch {
            repository.deleteHabit(habitId)
            fetchHabits()
        }
    }
    /**
     * Agrega una lista de 10 hábitos de ejemplo a Firestor
    e.
     * Se usa para pruebas o para pre-cargar la base de dat
    os con hábitos iniciales.
     */
    fun addExampleHabits() {
        val habitos = listOf(
            Habito(titulo = "Leer 10 páginas al día",
                descripcion = "Mejorar mis conocimientos leyendo un poco cada día."),
                    Habito(titulo = "Hacer ejercicio durante 30 minutos",
                        descripcion = "Mantenerme activo con una rutina corta de ejercicio."),
                    Habito(titulo = "Beber 2 litros de agua",
                        descripcion = "Mantenerme hidratado para mejorar mi salud."),
                    Habito(titulo = "Meditar 10 minutos",
                        descripcion = "Reducir el estrés y mejorar mi enfoque diario."),
                    Habito(titulo = "Escribir en un diario",
                        descripcion = "Reflexionar sobre mi día y establecer metas."),
                    Habito(titulo = "Dormir al menos 7 horas",
                        descripcion = "Descansar lo suficiente para mejorar mi energía y salud."),
                    Habito(titulo = "Desconectar del móvil antes de dormir",
                        descripcion = "Evitar pantallas 30 minutos antes de dormir para mejorar el descanso."),
                    Habito(titulo = "Aprender algo nuevo cada día",
                descripcion = "Leer, investigar o ver un video educativo."),
                    Habito(titulo = "Cocinar una comida saludable",
                descripcion = "Preparar una comida casera saludable al menos una vez al día."),
                    Habito(titulo = "Hacer una buena acción al día",
                            descripcion = "Ayudar a alguien o hacer algo positivo por los demás.")
            )
                    viewModelScope.launch {
                repository.addMultipleHabits(habitos) // Inserta los hábitos en una sola operación.
            }
    }
    /**
     * Elimina múltiples hábitos seleccionados en una sola
    operación en Firestore.
     * Luego, actualiza la lista de hábitos en la UI.
     */
    fun deleteSelectedHabits(selectedHabits: List<Habito>)
    {
        viewModelScope.launch {
            repository.deleteMultipleHabits(selectedHabits)
            fetchHabits() // Recarga la lista tras la eliminación.
        }
    }
}
