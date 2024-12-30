package com.vnavpal901.miniagenda.adapter

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vnavpal901.miniagenda.R
import com.vnavpal901.miniagenda.Tarea


//Esta clase pinta cada item del recyclerview(creamos tambien un layout genérico para el item)
/*Se utiliza para gestionar la visualización de cada item en el RecyclerView en una aplicación Android.
Esta clase extiende RecyclerView.ViewHolder y define los elementos visuales de cada tarea,
permitiendo su renderización, actualización y eliminación.*/

class TareaViewHolder(view: View, private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {

    // Inicializamos los elementos visuales del layout.
    val nombreTarea = view.findViewById<TextView>(R.id.textViewTarea)
    val descripcion = view.findViewById<TextView>(R.id.textViewDescripcion)
    val fechaTarea: TextView = view.findViewById(R.id.textViewFecha)
    val horaTarea: TextView = view.findViewById(R.id.textViewHora)
    val deleteButton = view.findViewById<ImageButton>(R.id.imageButtonDelete)
    val checkBoxCompletada: CheckBox = view.findViewById(R.id.checkBoxCompletada)
    private val fondo: View = view.findViewById(R.id.viewFondo)

    // Esta función se llama para cada item de la lista, asignando datos a las vistas.
    fun render(tareaModel: Tarea) {

        nombreTarea.text = tareaModel.nombreTarea
        descripcion.text = tareaModel.descripcionTarea
        fechaTarea.text = tareaModel.fecha
        horaTarea.text = tareaModel.hora
        checkBoxCompletada.isChecked = tareaModel.isCompleted

        updateTextStyle(tareaModel.isCompleted)

        // Controla cambios en el checkbox y actualiza el estado completado de la tarea.
        checkBoxCompletada.setOnCheckedChangeListener { _, isChecked ->
            tareaModel.isCompleted = isChecked
            updateTextStyle(isChecked)//llama al metodo que actualiza el estado cuando el checkbox esta marcado
        }


        // Botón de eliminación de tarea
        deleteButton.setOnClickListener {

            onDeleteClick(adapterPosition)

        }
    }



    // Actualiza el estilo del texto según el estado de la tarea (completada o no).
    /*Para el texto, lo único que hace es tachar el nombre de la tarea
    * Para el color de fondo, he creado 2 archivos de estilo xml en drawable(para hacer los bordes redondeados)
    * Cada uno tiene un background diferente, uno para completa y otro para no completa.
    * fondo.setbackgroundresource lo que hace es llamar a uno o a otro segun el checkbox*/
    private fun updateTextStyle(isCompleted: Boolean) {

        if (isCompleted) {

            nombreTarea.paintFlags = nombreTarea.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG//tacha el texto(nombre de tarea)
            fondo.setBackgroundResource(R.drawable.rounded_corners_completed)//Cambia de fondo(através del recurso xml)

        } else {

            nombreTarea.paintFlags = nombreTarea.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            fondo.setBackgroundResource(R.drawable.rounded_corners)

        }
    }




}