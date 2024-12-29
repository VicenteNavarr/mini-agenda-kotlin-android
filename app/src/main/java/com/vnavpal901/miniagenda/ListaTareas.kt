package com.vnavpal901.miniagenda

class ListaTareas {

    //el companion object permite acceder a lo que este dentro, en este caso una lista de Tarea
    companion object {

        //creamos lista
        val listaTareas = listOf<Tarea>(

            Tarea("Dentista", "Ir al dentista"),
            Tarea("Inglés", "Ir al Inglés"),
            Tarea("Banco", "Ir al banco"),
            Tarea("Supermercado", "Ir al super"),
            Tarea("Colegio", "Ir al colegio"),
            Tarea("Francés", "Ir a francés"),
            Tarea("Karate", "Ir a karate"),
            Tarea("Ropa", "Comprar ropa"),
            Tarea("Cocina", "Hacer la comida"),
            Tarea("Limpieza", "Limpiar toda la casa"),



        )

    }


}