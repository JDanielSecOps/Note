package com.app.note.screens.viewTodoScreen.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.viewTodoListScreenComponents.todoDetailsCard
import com.app.note.components.viewTodoListScreenComponents.viewTodoListScreenTAB
import com.app.note.screens.viewTodoScreen.viewTodoScreenIntent

import com.app.note.screens.viewTodoScreen.viewTodoScreenState
import com.app.note.viewModels.viewTodoScreenViewModel


@Composable
fun viewTodoScreenRoot(
    navToTodoListScreen:()-> Unit,
    navToUpdate:(Id : Int)-> Unit,
    viewModel: viewTodoScreenViewModel
){

    val layoutDirection = LocalLayoutDirection.current


    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            viewTodoListScreenTAB(state.title
                , {navToTodoListScreen()}
                ,{
                    viewModel.onAction(viewTodoScreenIntent.Delete)
                    navToTodoListScreen()
                },{
                    navToUpdate(state.id)
                })
        }
    ){paddingValues ->
        viewTodoScreen(paddingValues,layoutDirection,state)
    }
}


@Composable
fun viewTodoScreen(paddingValues: PaddingValues
    ,layoutDirection: LayoutDirection
   ,state: viewTodoScreenState
){

    Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize()
    ){

        state.todoList.forEach { todo ->
            todoDetailsCard(
                Id = todo.id,
                title = todo.title,
                createdDate = todo.createdDate,
                createdHour = todo.createdTimeHour,
                createdMinute = todo.createdTimeMinute,
                deadlineDate = todo.deadlineDate,
                deadlineHour = todo.deadlineTimeHour,
                deadlineMinute = todo.deadlineTimeMinute,
                note = todo.Todo
            )
        }
    }

}