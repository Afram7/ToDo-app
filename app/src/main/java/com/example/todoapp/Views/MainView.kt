package com.example.todoapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.Views.CreateToDoScreen
import com.example.todoapp.Views.UpdateScreen
import com.example.todoapp.Views.ViewAllScreen
import com.example.todoapp.Views.ViewOneScreen

// Global variable used to store all ToDos.
val toDoRepository = ToDoRepository()

@Composable
fun AppScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "viewAll"
    ) {
        composable("viewAll") {
            ViewAllScreen(navController)
        }

        composable("viewOne/{id}") {
            val id = it.arguments!!.getString("id")!!.toInt()
            ViewOneScreen(navController, id)
        }

        composable("createOne") {
            CreateToDoScreen(navController)
        }

        composable("update/{id}") {
            val id = it.arguments!!.getString("id")!!.toInt()
            UpdateScreen(navController, id)
        }
    }
}