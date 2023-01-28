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
fun UpdateScreen(navController: NavHostController, id: Int) {
    var title by remember { mutableStateOf(toDoRepository.getTitleById(id)) }
    var description by remember { mutableStateOf(toDoRepository.getDescriptionById(id)) }
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
                if (title.isEmpty() && description.isEmpty()) {
                    errorMessage = "You must fill in both fields"
                } else if (title.length < 2 ){
                    errorMessage = "The Title must at least contain 2 characters"
                } else if (title.length > 20 ){
                    errorMessage = "The Title can't be longer than 20 characters"
                } else {
                    toDoRepository.updateToDo(id, title, description)
                    navController.navigate("viewOne/${id}")
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 200.dp, height = 50.dp)
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Update", fontSize = 15.sp
            )
        }
    }
}