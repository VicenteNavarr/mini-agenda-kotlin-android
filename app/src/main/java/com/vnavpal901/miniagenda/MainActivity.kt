package com.vnavpal901.miniagenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vnavpal901.miniagenda.adapter.TareaAdapter

class MainActivity : AppCompatActivity() {


    private var listaTareaMutable: MutableList<Tarea> = ListaTareas.listaTareas.toMutableList()
    private lateinit var adapter: TareaAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()//ejecutamos funcion

    }

    //funcion para llamar el adapter
   private fun initRecyclerView(){

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTarea)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TareaAdapter(listaTareaMutable) { position ->
            removeItem(position)
        }
            recyclerView.adapter = adapter

    }

    private fun removeItem(position: Int) {
        adapter.removeItem(position)
    }




}