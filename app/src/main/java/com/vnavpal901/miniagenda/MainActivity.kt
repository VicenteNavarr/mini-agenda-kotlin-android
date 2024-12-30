package com.vnavpal901.miniagenda

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vnavpal901.miniagenda.adapter.TareaAdapter

class MainActivity : AppCompatActivity() {


    // Definimos una lista mutable de tareas que será utilizada para gestionar las tareas.
    private var listaTareaMutable: MutableList<Tarea> = ListaTareas.listaTareas.toMutableList()
    // Inicializamos el adapter para el RecyclerView.
    private lateinit var adapter: TareaAdapter
    // TextView(mensaje) que se mostrará cuando no haya tareas.
    private lateinit var textViewNoTasks: TextView
    // Helper para manejar preferencias compartidas(Persistencia de datos local).
    private lateinit var preferencesHelper: PreferencesHelper




    //Metodo que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Carga una imagen GIF en el ImageView usando Glide.(En el activity_main metí un gif animado,
        añadiendo glide a las librerias y dependencias consigo que se vea animado)*/
        val imageViewHeader = findViewById<ImageView>(R.id.imageViewHeader)
        Glide.with(this).asGif().load(R.drawable.agenda_animada2).into(imageViewHeader)

        // Inicializa el helper de preferencias y carga las tareas guardadas.
        preferencesHelper = PreferencesHelper(this)
        listaTareaMutable = preferencesHelper.loadTasks().toMutableList()

        // Inicializa el RecyclerView y el botón de añadir tarea.
        initRecyclerView()//ejecutamos funcion
        initAddButton() // Inicializa el botón de añadir tarea

        // Inicializa el TextView que muestra el mensaje de "No hay tareas".(se muestra si la lista de tareas esta vacía)
        textViewNoTasks = findViewById(R.id.textViewNoTasks)
        updateNoTasksMessage()

    }



    //funcion para llamar el adapter. Inicia recyclerview
   private fun initRecyclerView(){

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTarea)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TareaAdapter(listaTareaMutable) { position ->
            removeItem(position)
        }
            recyclerView.adapter = adapter

    }


    //Inicia el botón de añadir tarea
    private fun initAddButton() {
        val addButton = findViewById<Button>(R.id.buttonAddTask)
        addButton.setOnClickListener {
            showAddTaskDialog()
        }
    }


    //Muestra el popup de añadir tarea(layout popup_add_tarea.xml)
    private fun showAddTaskDialog() {

        //declaramos elementos con sus respectivos ids
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.popup_add_tarea, null)
        val editTextNuevaTarea = dialogView.findViewById<EditText>(R.id.editTextNombreTarea)
        val editTextDescripcion = dialogView.findViewById<EditText>(R.id.editTextDescripcion)
        val datePicker = dialogView.findViewById<DatePicker>(R.id.datePicker)
        val timePicker = dialogView.findViewById<TimePicker>(R.id.timePicker)
        val buttonAddTask = dialogView.findViewById<Button>(R.id.buttonAddTask)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        buttonAddTask.setOnClickListener {

            //Argumentos
            val nuevaTarea = editTextNuevaTarea.text.toString()
            val descripcion = editTextDescripcion.text.toString()
            val dia = datePicker.dayOfMonth
            val mes = datePicker.month + 1
            val anio = datePicker.year
            val hora = timePicker.hour
            val minuto = timePicker.minute
            val fecha = "$dia/$mes/$anio"
            val horaFormateada = String.format("%02d:%02d", hora, minuto)

            // Validación de los campos antes de añadir la tarea.(si nombre o descripcion estan vacios, muestra un mensaje toast)
            if (nuevaTarea.isEmpty()) {

                Toast.makeText(this, "Es necesario rellenar el nombre de la tarea", Toast.LENGTH_SHORT).show()

            } else if (descripcion.isEmpty()) {

                Toast.makeText(this, "Es necesario rellenar la descripción de la tarea", Toast.LENGTH_SHORT).show()

            } else {

                addItem(Tarea(nuevaTarea, descripcion, fecha, horaFormateada))
                dialog.dismiss()
            }

        }

        dialog.show()
    }



    // Metodo para añadir una nueva tarea a la lista y actualizar el RecyclerView.
    private fun addItem(tarea: Tarea) {
        listaTareaMutable.add(tarea)
        adapter.notifyItemInserted(listaTareaMutable.size - 1)
        updateNoTasksMessage()
        preferencesHelper.saveTasks(listaTareaMutable)
    }


    // Metodo para eliminar una tarea de la lista y actualizar el RecyclerView.
    private fun removeItem(position: Int) {
        adapter.removeItem(position)
        updateNoTasksMessage()
        preferencesHelper.saveTasks(listaTareaMutable)
    }


    // Metodo para mostrar u ocultar el mensaje de "No hay tareas" según corresponda.
    private fun updateNoTasksMessage() {
        if (listaTareaMutable.isEmpty()) {
            textViewNoTasks.visibility = TextView.VISIBLE
        } else {
            textViewNoTasks.visibility = TextView.GONE
        }
    }



}