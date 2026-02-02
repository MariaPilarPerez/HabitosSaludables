package com.campusdigitalfp.habitossaludables.sampledata

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.campusdigitalfp.habitossaludables.R
import com.campusdigitalfp.habitossaludables.screens.Habito
/** * Datos de ejemplo para Hábitos Saludables */
object SampleData { val habitSample = mutableStateListOf<Habito>()
    fun loadData(context: Context)
    { val h1 = Habito( id = habitSample.size,
        titulo = context.getString(R.string.beber_agua),
        descripcion =context.getString(R.string.asegurate) )
            habitSample.add(h1)
      val h2 = Habito( id = habitSample.size, titulo = context.getString(R.string.ejercicio_diario),
          descripcion = context.getString(R.string.realiza_al_menos_30_minutos) )
          habitSample.add(h2)
        val h3 = Habito( id = habitSample.size, titulo = context.getString(R.string.meditaci_n),
            descripcion = context.getString(R.string.dedica_meditacion) )
        habitSample.add(h3)
        val h4 = Habito( id = habitSample.size, titulo = context.getString(R.string.leer_un_libro),
            descripcion = context.getString(R.string.lee_al_menos_un_libro_al_mes) )
        habitSample.add(h4)
        val h5 = Habito( id = habitSample.size, titulo = context.getString(R.string.dormir_bien),
            descripcion = context.getString(R.string.intenta_dormir_entre_7_y_8_horas) )
        habitSample.add(h5)
        val h6 = Habito( id = habitSample.size, titulo = context.getString(R.string.comer_frutas_y_verduras),
            descripcion = context.getString(R.string.incluye_al_menos_5_porciones_de_frutas) )
        habitSample.add(h6)
        val h7 = Habito( id = habitSample.size, titulo = context.getString(R.string.desconectar_de_las_pantallas),
            descripcion = context.getString(R.string.tomate_un_descanso_de_las_pantallas) )
        habitSample.add(h7)
        val h8 = Habito( id = habitSample.size, titulo = context.getString(R.string.practicar_la_gratitud),
            descripcion = context.getString(R.string.anota_tres_cosas_por_las_que_estes_agradecido ) )
        habitSample.add(h8)
        val h9 = Habito( id = habitSample.size, titulo = context.getString(R.string.planificar_el_d_a),
            descripcion = context.getString(R.string.para_planificar_tus_tareas) )
        habitSample.add(h9)
        val h10 = Habito( id = habitSample.size, titulo = context.getString(R.string.socializar),
            descripcion = context.getString(R.string.con_ctate_con_amigos) )
        habitSample.add(h10)
    }
}


