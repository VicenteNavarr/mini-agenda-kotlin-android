package com.vnavpal901.miniagenda

//Convertimos la clase a data class, para poder meterle argumentos por defecto

data class Tarea(

    val nombreTarea: String,
    val descripcionTarea: String,
    val hora: String = "",//toma del timepicker, que convertimos a string para mostrar
    val fecha: String = "",//toma del datepicker, que convertimos a string para mostrar
    var isCompleted: Boolean = false//controla el checkboz
)
