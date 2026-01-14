package com.app.note.components.viewTodoListScreenComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun viewTodoListScreenTAB(
    title : String,
    navToTodoListScreen :()->Unit,
    delete:()-> Unit,
    update :()->Unit
){

    TopAppBar(
        title ={
            Text(title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1)
        },
        navigationIcon = {

            IconButton(
                onClick = {navToTodoListScreen()}
            ) {
                Icon(Icons.Filled.ArrowBackIosNew,null)
            }
        }
        ,
        actions = {
            IconButton(
                onClick = {update()}
            ) {
                Icon(Icons.Filled.Edit,null)
            }
            IconButton(
                onClick = {delete()}
            ) {
                Icon(Icons.Filled.Delete,null)
            }
        }
    )
}