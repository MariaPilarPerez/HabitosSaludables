package com.campusdigitalfp.habitossaludables.sampledata

import com.campusdigitalfp.habitossaludables.screens.Habito


/*
* Datos de ejemplo para Habitos Saludables
 */
object SampleData {
    //Ejemplo de hábito
    val habitSample: MutableList<Habito> = mutableListOf()
    init{
        val h1 = Habito(
            id = habitSample.size,
            "Beber agua",
            "Asegúrate de beber al menos 2 libros de agua al día para mantenerte hidratado."
        )
        habitSample.add(h1)
        val h2 = Habito(
            habitSample.size,
            "Ejercicio diario",
            "Realiza al menos 30 minutos de ejercicio cada día para mantenerte en forma."
        )
        habitSample.add(h2)
        val h3 = Habito(
            habitSample.size,
            "Meditación",
            "Dedica 10 minutos al dia a la meditación para reducir el estrés y mejorar la concentración."
        )
        habitSample.add(h3)
        val h4 = Habito(
            habitSample.size,
            "Leer un libro",
            "Lee al menos un libro al mes para enriquecer tu conocimiento y estimular tu mente."
        )
        habitSample.add(h4)
        val h5 = Habito(
            habitSample.size,
            "Dormir bien",
            "Intenta dormir entre 7 y 8 horas cada noche para mejorar tu salud mental y física."
        )
        habitSample.add(h5)
        val h6 = Habito(
            habitSample.size,
            "Comer frutas y verduras",
            "Incluye al menos 5 porciones de frutas y verduras en tu dieta diaria para una alimentación balanceada."
        )
        habitSample.add(h6)
        val h7 = Habito(
            habitSample.size,
            "Desconectar de las pantallas",
            "Tómate un descanso de las pantallas al menos una hora antes de dormir para mejorar la calidad del sueño."
        )
        habitSample.add(h7)
        val h8 = Habito(
            habitSample.size,
            "Practicar la gratitud",
            "Anota tres cosas por las que estás agradecido cada día para fomentar una mentalidad positiva."
        )
        habitSample.add(h8)
        val h9 = Habito(
            habitSample.size,
            "Planificar el día",
            "Dedica unos minutos cada mañana para planificar tus tareas y establecer prioridades."
        )
        habitSample.add(h9)
        val h10 = Habito(
            habitSample.size,
            "Socializar",
            "Conéctate con amigos y familiares al menos una vez a la semana para fortalecer tus relaciones."
        )
        habitSample.add(h10)

    }

}