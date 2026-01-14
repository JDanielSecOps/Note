package com.app.note.components.updateTodoScreenComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun updateNoteTAB(
    title : String,
    navToHomeScreen :()-> Unit,
    navToupdateTodoScreen: ()->Unit
){
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {navToupdateTodoScreen()}
            ) {
                Icon(Icons.Filled.ArrowBackIosNew,null)
            }
        },
        title = { Text("Note") },
        actions = {
            IconButton(onClick ={navToHomeScreen()}) {
                Icon(Icons.Filled.Edit,null)
            }
        }
    )
}