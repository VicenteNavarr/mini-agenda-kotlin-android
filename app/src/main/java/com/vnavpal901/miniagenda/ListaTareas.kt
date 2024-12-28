package com.vnavpal901.miniagenda

class ListaTareas {

    //el companion object permite acceder a lo que este dentro, en este caso una lista de Tarea
    companion object {

        //creamos lista
        val listaTareas = listOf<Tarea>(

            Tarea("Dentista"),
            Tarea("Inglés"),
            Tarea("Banco"),
            Tarea("Supermercado"),
            Tarea("Colegio"),
            Tarea("Francés"),
            Tarea("Karate"),
            Tarea("Ropa"),
            Tarea("Cocina"),
            Tarea("Limpieza"),



        )

    }


}