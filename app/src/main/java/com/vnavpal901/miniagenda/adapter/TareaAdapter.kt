package com.vnavpal901.miniagenda.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vnavpal901.miniagenda.R
import com.vnavpal901.miniagenda.Tarea

/*La clase TareaAdapter se utiliza para gestionar y adaptar una lista de tareas (listaTareas)
 a un RecyclerView. Esta clase extiende RecyclerView.Adapter y maneja
 la creación y vinculación de los elementos de la lista de tareas a las vistas correspondientes (TareaViewHolder).*/

// Esta clase toma toda la información y la mete en el RecyclerView.
// Extiende de la clase RecyclerView.Adapter e implementa métodos abstractos necesarios.


class TareaAdapter(private val listaTareas:MutableList<Tarea>, private val onDeleteClick: (Int) -> Unit) : RecyclerView.Adapter<TareaViewHolder>() {//la clase va a recibir una lista(de Tarea)

    // Metodo que crea y devuelve una instancia de TareaViewHolder, inflando el layout de cada item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {

        //Toma los atributos de viewHolder y los devuelve desde el layout(pinta)
        val layoutInflater =
            LayoutInflater.from(parent.context)//directamente va a devolver  TareaViewHolder
        return TareaViewHolder(
            layoutInflater.inflate(R.layout.item_tarea, parent, false),
            onDeleteClick
        )

    }


    //devuelve al tamaño de la lista(dinámicamente)
    override fun getItemCount(): Int {

        return listaTareas.size
    }


    // Metodo que vincula los datos de la lista a las vistas del ViewHolder.
    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {

        // Este metodo recorre la lista y llama al metodo render del viewHolder mediante la posicion
        val item = listaTareas[position]
        holder.render(item)

    }


    // Metodo para eliminar un item y notifica al Adapter para actualizar la vista.
    fun removeItem(position: Int) {

        listaTareas.removeAt(position)
        notifyItemRemoved(position)

    }





}