package com.vnavpal901.miniagenda.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vnavpal901.miniagenda.R
import com.vnavpal901.miniagenda.Tarea

//Esta clase toma toda la información y la mete en el recyclerview
// (hacemos que extienda de la clase recyclerview e implementados métodos abstractos)

class TareaAdapter(private val listaTareas:MutableList<Tarea>, private val onDeleteClick: (Int) -> Unit) : RecyclerView.Adapter<TareaViewHolder>() {//la clase va a recibir una lista(de Tarea)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {

        //Toma los atributos de viewHolder y los devuelve desde el layout(pinta)

        val layoutInflater =
            LayoutInflater.from(parent.context)//directamente va a devolver  TareaViewHolder
        return TareaViewHolder(
            layoutInflater.inflate(R.layout.item_tarea, parent, false),
            onDeleteClick
        )

    }

    override fun getItemCount(): Int {

        //devuelve al tamaño de la lista(dinámicamente)

        return listaTareas.size
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {

        // Este metodo recorre la lista y llama al metodo render del viewHolder mediante la posicion
        val item = listaTareas[position]
        holder.render(item)


    }


        // Metodo para eliminar un item
        fun removeItem(position: Int) {
            listaTareas.removeAt(position)
            notifyItemRemoved(position)
        }





}