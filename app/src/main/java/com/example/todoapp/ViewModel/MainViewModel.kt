package com.example.todoapp

class ToDoRepository {
    private val toDos = mutableListOf<ToDo>(
        ToDo(
            1,
            "Feed the pets",
            "Give the cat a fish and the dog a cat."
        ), ToDo(
            2,
            "Exercise",
            "Take a walk and listen to music."
        )
    )

    fun getTitleById(id: Int): String {
        for (value in toDos) {
            if (value.id == id) {
                return value.title
            }
        }
        return ""
    }

    fun getDescriptionById(id: Int): String {
        for (value in toDos) {
            if (value.id == id) {
                return value.description
            }
        }
        return ""
    }

    fun getAllToDos(): MutableList<ToDo> {
        return toDos
    }

    fun addToDo(title: String, description: String): Int {
        val id = when {
            toDos.isEmpty() -> 1
            else -> toDos.last().id + 1
        }
        toDos.add(ToDo(id, title, description))
        return id
    }

    fun updateToDo(id: Int, newTitle: String, newDescription: String) {
        for (value in toDos) {
            if (value.id == id) {
                value.title = newTitle
                value.description = newDescription
            }
        }
    }
}