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

class TareaViewHolder(view: View, private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {

    val nombreTarea = view.findViewById<TextView>(R.id.textViewTarea)
    val deleteButton = view.findViewById<ImageButton>(R.id.imageButtonDelete)
    val checkBoxCompletada: CheckBox = view.findViewById(R.id.checkBoxCompletada)

    //esta función se va a llamar por cada item de la lista
    fun render(tareaModel: Tarea) {

        nombreTarea.text = tareaModel.nombreTarea
        checkBoxCompletada.isChecked = tareaModel.isCompleted

        updateTextStyle(tareaModel.isCompleted)

        checkBoxCompletada.setOnCheckedChangeListener { _, isChecked ->
            tareaModel.isCompleted = isChecked
            updateTextStyle(isChecked)
        }


        deleteButton.setOnClickListener {
            onDeleteClick(adapterPosition)

        }
    }


    private fun updateTextStyle(isCompleted: Boolean) {
        if (isCompleted) {
            nombreTarea.paintFlags = nombreTarea.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            nombreTarea.paintFlags = nombreTarea.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }




}