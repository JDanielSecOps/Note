package com.app.note.components.viewTrashDataScreenComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun viewTrashDataScreenTAB(
    title : String,
    navToTrashScreen : ()-> Unit,
    delete : ()-> Unit,
    restore : ()-> Unit
){

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {

            IconButton(
                onClick = {navToTrashScreen()}
            ) {
                Icon(Icons.Filled.ArrowBackIosNew,null)
            }
        },
        actions = {
            IconButton(
                onClick = {delete()}
            ) {
                Icon(Icons.Filled.Delete,null)
            }
            IconButton(
                onClick = {restore()}
            ) {
                Icon(Icons.Filled.RestoreFromTrash,null)
            }
        }
    )
}