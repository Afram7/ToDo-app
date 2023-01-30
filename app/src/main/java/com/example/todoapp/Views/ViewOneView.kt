package com.example.todoapp.Views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.toDoRepository

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
                onClick = { navController.navigate("update/${id}") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Update")
            }

            Button(
                onClick = { navController.navigate("delete/${id}") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Delete")
            }
        }
    }
}