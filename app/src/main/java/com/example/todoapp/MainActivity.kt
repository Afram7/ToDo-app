package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

data class ToDo(
    val id: Int,
    val title: String,
    val description: String
)

class ToDoRepository {
    private val toDos = mutableListOf<ToDo>(
        ToDo(
            1,
            "Feed the pets",
            "Give the cat a fish and the dog a cat."
        ),
        ToDo(
            2,
            "Exercise",
            "Take a walk and listen to music."
        )
    )

    fun getAllToDos(): MutableList<ToDo> {
        return toDos
    }
}

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
    }
}

@Composable
fun ViewAllScreen(navController: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(toDoRepository.getAllToDos()) { toDo ->
            Button(
                onClick = { navController.navigate("viewOne/${toDo.id}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp)
            ) {
                Text(
                    toDo.title,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun ViewOneScreen(navController: NavHostController, id: Int) {
    val toDo = toDoRepository.getAllToDos().find { it.id == id }!!
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Title: " + toDo.title,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp)
        )
        Text(
            text = "Description: " + toDo.description,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 5.dp, vertical = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoAppTheme {

    }
}