package com.vnavpal901.miniagenda.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vnavpal901.miniagenda.R
import com.vnavpal901.miniagenda.Tarea


//Esta clase pinta cada item del recyclerview(creamos tambien un layout genérico para el item)

class TareaViewHolder(view: View, private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {

    val nombreTarea = view.findViewById<TextView>(R.id.textViewTarea)
    val deleteButton = view.findViewById<ImageButton>(R.id.imageButtonDelete)

    //esta función se va a llamar por cada item de la lista
    fun render(tareaModel: Tarea) {

        nombreTarea.text = tareaModel.nombreTarea
        deleteButton.setOnClickListener {
            onDeleteClick(adapterPosition)

        }
    }


}