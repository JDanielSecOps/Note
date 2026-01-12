package com.app.note.components.trashScreenComponenets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun trashScreenTopAppBar(
    navToSettings : ()->Unit,
    deleteListOfTodoes: ()->Unit
){

    TopAppBar(
        title = { Text("Trash") },
        navigationIcon = {
            IconButton(
                onClick =  navToSettings
            ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
            }
        },
        actions = {
            IconButton(
                onClick =  deleteListOfTodoes
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
            }
        }
    )
}