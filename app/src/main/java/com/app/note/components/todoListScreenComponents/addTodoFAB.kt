package com.app.note.components.todoListScreenComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun addTodo(
    onClick : ()-> Unit
){

    FloatingActionButton(onClick =onClick, shape = CircleShape,
    ) {
          Icon(imageVector = Icons.Filled.Add, contentDescription = "addTodo",
              modifier = Modifier.padding(8.dp))
      }
}

