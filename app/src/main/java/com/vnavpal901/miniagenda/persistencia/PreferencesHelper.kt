package com.vnavpal901.miniagenda

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MiniAgendaPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveTasks(tasks: List<Tarea>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(tasks)
        editor.putString("tasks", json)
        editor.apply()
    }

    fun loadTasks(): List<Tarea> {
        val json = sharedPreferences.getString("tasks", null)
        return if (json != null) {
            val type = object : TypeToken<List<Tarea>>() {}.type
            gson.fromJson(json, type)
        } else {
            listOf()
        }
    }
}
