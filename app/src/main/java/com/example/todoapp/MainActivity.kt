package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val content: String
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
            ViewAllScreen()
        }
    }
}

@Composable
fun ViewAllScreen() {
    LazyColumn {
        items(toDoRepository.getAllToDos()) { toDo ->
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp)
            ) {
                Text(toDo.title)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoAppTheme {

    }
}