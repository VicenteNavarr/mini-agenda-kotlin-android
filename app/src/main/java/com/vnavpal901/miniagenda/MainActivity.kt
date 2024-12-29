package com.vnavpal901.miniagenda

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vnavpal901.miniagenda.adapter.TareaAdapter

class MainActivity : AppCompatActivity() {


    private var listaTareaMutable: MutableList<Tarea> = ListaTareas.listaTareas.toMutableList()
    private lateinit var adapter: TareaAdapter
    private lateinit var textViewNoTasks: TextView
    private lateinit var preferencesHelper: PreferencesHelper




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferencesHelper = PreferencesHelper(this)
        listaTareaMutable = preferencesHelper.loadTasks().toMutableList()


        initRecyclerView()//ejecutamos funcion
        initAddButton() // Inicializa el botón de añadir tarea

        textViewNoTasks = findViewById(R.id.textViewNoTasks)
        updateNoTasksMessage()



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


    private fun initAddButton() {
        val addButton = findViewById<Button>(R.id.buttonAddTask)
        addButton.setOnClickListener {
            showAddTaskDialog()
        }
    }





    private fun showAddTaskDialog() {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.popup_add_tarea, null)
        val editTextNuevaTarea = dialogView.findViewById<EditText>(R.id.editTextNombreTarea)
        val editTextDescripcion = dialogView.findViewById<EditText>(R.id.editTextDescripcion)
        val buttonAddTask = dialogView.findViewById<Button>(R.id.buttonAddTask)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        buttonAddTask.setOnClickListener {
            val nuevaTarea = editTextNuevaTarea.text.toString()
            val descripcion = editTextDescripcion.text.toString()
            if (nuevaTarea.isNotEmpty()) {
                addItem(Tarea(nuevaTarea, descripcion))
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun addItem(tarea: Tarea) {
        listaTareaMutable.add(tarea)
        adapter.notifyItemInserted(listaTareaMutable.size - 1)
        updateNoTasksMessage()
        preferencesHelper.saveTasks(listaTareaMutable)
    }






    private fun removeItem(position: Int) {
        adapter.removeItem(position)
        updateNoTasksMessage()
        preferencesHelper.saveTasks(listaTareaMutable)
    }

    private fun updateNoTasksMessage() {
        if (listaTareaMutable.isEmpty()) {
            textViewNoTasks.visibility = TextView.VISIBLE
        } else {
            textViewNoTasks.visibility = TextView.GONE
        }
    }



}