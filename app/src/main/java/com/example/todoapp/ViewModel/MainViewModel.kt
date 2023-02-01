package com.example.todoapp

class ToDoRepository {
    private val toDos = mutableListOf<ToDo>()

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

    fun deleteToDo(id: Int) {
        for (value in toDos) {
            if (value.id == id) {
                toDos.removeAt(toDos.indexOf(value))
            }
        }
    }

    fun validateTitleAndDescription(title: String, description: String): String {
        if (title.isEmpty() || description.isEmpty()) {
            return "You must fill in both fields"
        } else if (title.length < 2) {
            return "The Title must at least contain 2 characters"
        } else if (title.length > 20) {
            return "The Title can't be longer than 20 characters"
        }
        return "OK"
    }
}