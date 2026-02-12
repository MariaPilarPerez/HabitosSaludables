package com.campusdigitalfp.habitossaludables.models

data class Habito(
    val id: String="",  //identificador del documentoen Firestore
    var titulo: String="",
    var descripcion: String="",
    val completado: Boolean=false
)