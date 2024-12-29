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
    val descripcion = view.findViewById<TextView>(R.id.textViewDescripcion)
    val fechaTarea: TextView = view.findViewById(R.id.textViewFecha)
    val horaTarea: TextView = view.findViewById(R.id.textViewHora)
    val deleteButton = view.findViewById<ImageButton>(R.id.imageButtonDelete)
    val checkBoxCompletada: CheckBox = view.findViewById(R.id.checkBoxCompletada)
    private val fondo: View = view.findViewById(R.id.viewFondo)

    //esta función se va a llamar por cada item de la lista
    fun render(tareaModel: Tarea) {

        nombreTarea.text = tareaModel.nombreTarea
        descripcion.text = tareaModel.descripcionTarea
        fechaTarea.text = tareaModel.fecha
        horaTarea.text = tareaModel.hora
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
            fondo.setBackgroundResource(R.drawable.rounded_corners_completed)
        } else {
            nombreTarea.paintFlags = nombreTarea.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            fondo.setBackgroundResource(R.drawable.rounded_corners)

        }
    }




}