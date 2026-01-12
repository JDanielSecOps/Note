package com.app.note.screens.todoListScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.todoListScreenComponents.addTodo
import com.app.note.components.todoListScreenComponents.todolistScreenTopAppBar
import com.app.note.components.viewTodoListScreenComponents.todoCard
import com.app.note.screens.todoListScreen.todoListScreenIntent
import com.app.note.screens.todoListScreen.todoListScreenState
import com.app.note.viewModels.todoListScreenViewmodel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun todolistScreenRoot(
    navToAddTodo : () -> Unit ,
    navtoseeTodo: (Int)-> Unit,
    navtoUpdateTodo :(Int)-> Unit,
    viewmodel: todoListScreenViewmodel = koinViewModel<todoListScreenViewmodel>()
){

    val layoutDirection = LocalLayoutDirection.current

    val state by viewmodel.state.collectAsStateWithLifecycle()


    Scaffold(
        floatingActionButton = { addTodo(
            onClick = {navToAddTodo()}
        ) },
        topBar = {todolistScreenTopAppBar()}
    ){ paddingValues ->
        todolistScreen(paddingValues,layoutDirection,state,navtoseeTodo,viewmodel::onAction,
            navtoUpdateTodo)
    }
}


@Composable
fun todolistScreen(paddingValues: PaddingValues,
   layoutDirection: LayoutDirection,
   state: todoListScreenState,
   navtoseeTodo: (Int)-> Unit,
   onAction : (todoListScreenIntent) ->Unit,
   navtoUpdateTodo :(Int)-> Unit
){


    if(state.isLoading){

        Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
    else if(state.todolist.isEmpty()){

        Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Notes is empty",
                fontSize = 30.sp, fontWeight = FontWeight.W600)
        }

    }else {

        Box(modifier = generalScreenScaffoldPadding(paddingValues, layoutDirection).fillMaxSize()) {

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(items = state.todolist) { todo ->
                    todoCard(
                        title = todo.title,
                        Id = todo.id,
                        createdDate = todo.createdDate,
                        createdHour = todo.createdTimeHour,
                        createdMinute = todo.createdTimeMinute,
                        deadlineDate = todo.deadlineDate,
                        deadlineHour = todo.deadlineTimeHour,
                        deadlineMinute = todo.createdTimeMinute,
                        redirect = { id ->
                            navtoseeTodo(id)
                        },
                        update = {
                            navtoUpdateTodo(todo.id)
                        },
                        delete = {
                            onAction(todoListScreenIntent.deleteSingle(todo.id))
                        }
                    )
                }
            }

        }


    }
}