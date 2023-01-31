package com.example.todoapp.Views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.toDoRepository

@Composable
fun CreateToDoScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Create a ToDo",
            fontSize = 35.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = errorMessage,
            color = Color.Red,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        TextField(
            value = title,
            modifier = Modifier.width(300.dp),
            onValueChange = { title = it },
            label = { Text(text = "Title") }
        )

        TextField(
            value = description,
            modifier = Modifier.width(300.dp),
            onValueChange = { description = it },
            label = { Text(text = "Description") }
        )

        Button(
            onClick = {
                val result = toDoRepository.validateTitleAndDescription(title, description)
                if (result is Int) {
                    val id = toDoRepository.addToDo(title, description)
                    navController.navigate("viewOne/${id}") {
                        popUpTo("viewAll")
                    }
                } else if (result is String) {
                    errorMessage = result
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
                text = "Save",
                fontSize = 15.sp
            )
        }
    }
}