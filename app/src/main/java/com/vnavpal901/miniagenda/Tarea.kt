package com.vnavpal901.miniagenda

//Convertimos la clase a data class, para poder meterle argumentos por defecto

data class Tarea(val nombreTarea: String, val descripcionTarea: String, val hora: String = "", val fecha: String = "", var isCompleted: Boolean = false)
