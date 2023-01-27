package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
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
        ), ToDo(
            2,
            "Exercise",
            "Take a walk and listen to music."
        )
    )

    fun getAllToDos(): MutableList<ToDo> {
        return toDos
    }

    fun addToDo(title: String, description: String): Int {
        val id = when {
            toDos.isEmpty() -> 1
            else -> toDos.last().id + 1
        }

        toDos.add(
            ToDo(
                id,
                title,
                description
            )
        )

        return id
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

        composable("createOne") {
            CreateToDoScreen(navController)
        }
    }
}

@Composable
fun ViewAllScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
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
        Button(
            onClick = { navController.navigate("createOne") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            modifier = Modifier.align(Alignment.BottomCenter),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Add a ToDo")
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

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("viewAll") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Show all")
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Update")
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Delete")
            }
        }
    }
}

@Composable
fun CreateToDoScreen(navController: NavHostController) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = errorMessage,
            color = Color.Red,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Title") }
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Description") }
        )

        Button(
            onClick = {
                val id: Int
                if (!title.text.isEmpty() && !description.text.isEmpty()) {
                    id = toDoRepository.addToDo(title.text, description.text)
                    navController.navigate("viewOne/${id}")
                } else {
                    errorMessage = "You must fill in both fields"
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 200.dp, height = 50.dp)
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Save", fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoAppTheme {

    }
}