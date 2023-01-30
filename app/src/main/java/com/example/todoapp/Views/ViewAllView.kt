package com.example.todoapp.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.toDoRepository

@Composable
fun ViewAllScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.align(Alignment.Center)) {
            if (toDoRepository.getAllToDos().isEmpty()) {
                item {
                    Text(
                        text = "It seems you have nothing to do 😃",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
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