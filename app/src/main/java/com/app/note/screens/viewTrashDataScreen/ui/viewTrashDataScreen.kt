package com.app.note.screens.viewTrashDataScreen.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.viewTodoListScreenComponents.todoDetailsCard
import com.app.note.components.viewTodoListScreenComponents.viewTodoListScreenTAB
import com.app.note.components.viewTrashDataScreenComponents.viewTrashDataScreenTAB
import com.app.note.screens.trashScreen.ui.trashScreenRoot
import com.app.note.screens.viewTodoScreen.viewTodoScreenIntent

import com.app.note.screens.viewTodoScreen.viewTodoScreenState
import com.app.note.viewModels.viewTodoScreenViewModel


@Composable
fun viewTrashDataScreenRoot(

    navtoTrashScreen:()-> Unit,
    viewModel: viewTodoScreenViewModel

){

    val layoutDirection = LocalLayoutDirection.current


    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            viewTrashDataScreenTAB(
                title = state.title,
                navToTrashScreen = {
                    navtoTrashScreen()
                },
                delete ={
                    viewModel.onAction(viewTodoScreenIntent.PermaDelete)
                    navtoTrashScreen()
                },
                restore ={
                    viewModel.onAction(viewTodoScreenIntent.restore)
                    navtoTrashScreen()
                }
            )
        }
    ){paddingValues ->
        viewTrashDataScreen(paddingValues,layoutDirection,state)
    }
}


@Composable
fun viewTrashDataScreen(paddingValues: PaddingValues
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