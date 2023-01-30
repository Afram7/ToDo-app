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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.toDoRepository

@Composable
fun DeleteToDoScreen(navController: NavHostController, id: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Do you want to delete ${toDoRepository.getTitleById(id)}?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 30.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    navController.navigate("viewOne/${id}") {
                        popUpTo("viewAll")
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.size(height = 40.dp, width = 150.dp)
            ) {
                Text(text = "Cancel")
            }

            Button(
                onClick = {
                    toDoRepository.deleteToDo(id)
                    navController.navigate("viewAll") {
                        popUpTo("viewAll") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.size(height = 40.dp, width = 150.dp)
            ) {
                Text(text = "Delete")
            }
        }
    }
}