package com.vnavpal901.miniagenda

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/*Esta clase gestiona la persistencia de datos. Utilizamos Json con su libreria GSon
*Utilizamos SharedPreferences*/
class PreferencesHelper(context: Context) {

    // Se inicializa SharedPreferences con el nombre "MiniAgendaPrefs"(o el nombre que quieras)
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MiniAgendaPrefs", Context.MODE_PRIVATE)

    // Se inicializa Gson para convertir objetos a JSON y viceversa
    private val gson = Gson()

    // Función para guardar la lista de tareas en SharedPreferences
    fun saveTasks(tasks: List<Tarea>) {

        val editor = sharedPreferences.edit()// Se obtiene el editor de SharedPreferences
        val json = gson.toJson(tasks)// Se convierte la lista de tareas a JSON
        editor.putString("tasks", json)// Se guarda el JSON en SharedPreferences con la clave "tasks"
        editor.apply()//aplicamos
    }

    // Función para cargar la lista de tareas desde SharedPreferences
    fun loadTasks(): List<Tarea> {

        val json = sharedPreferences.getString("tasks", null)// Se obtiene el JSON guardado, si existe

        return if (json != null) {

            // definimos el tipo de lista de tareas
            val type = object : TypeToken<List<Tarea>>() {}.type
            gson.fromJson(json, type)// Se convierte el JSON a una lista de tareas

        } else {

            listOf()// Si no hay JSON, se devuelve una lista vacía
        }
    }



}
