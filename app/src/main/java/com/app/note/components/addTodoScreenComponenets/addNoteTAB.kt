package com.app.note.components.addTodoScreenComponenets



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun addNoteTAB(
    title : String,
    navtoTodoListScreen :()-> Unit,
    navtoaddTodoScreen : ()->Unit
){
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {navtoaddTodoScreen()}
            ) {
                Icon(Icons.Filled.ArrowBackIosNew,null)
            }
        },
        title = { Text(title) },
        actions = {
            IconButton(onClick ={navtoTodoListScreen()}) {
                Icon(Icons.Filled.Check,null)
            }
        }
    )
}