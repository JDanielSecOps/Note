package com.app.note.components.todoListScreenComponents

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun todolistScreenTopAppBar(){

    TopAppBar(
        title = { Text("Notes") }
    )
}