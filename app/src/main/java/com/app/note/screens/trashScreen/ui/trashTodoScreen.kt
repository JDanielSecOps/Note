package com.app.note.screens.trashScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.trashScreenComponenets.trashCard
import com.app.note.components.trashScreenComponenets.trashScreenTopAppBar
import com.app.note.components.viewTodoListScreenComponents.todoCard
import com.app.note.screens.trashScreen.trashScreenIntent
import com.app.note.screens.trashScreen.trashScreenState
import com.app.note.viewModels.trashScreenViewModel
import com.app.note.viewModels.viewTodoScreenViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun trashScreenRoot(
    navToTrashDataScreen : (Int)-> Unit,
    navToSettings : ()->Unit,
    viewModel: trashScreenViewModel = koinViewModel<trashScreenViewModel>()
){

    val state by viewModel.state.collectAsState()

    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = { trashScreenTopAppBar(
            navToSettings,
            deleteListOfTodoes ={
                viewModel.onAction(trashScreenIntent.delete)
            }
        ) }
    ){paddingValues ->
        trashScreen(paddingValues,layoutDirection,state,viewModel::onAction,navToTrashDataScreen)
    }
}


@Composable
fun trashScreen(paddingValues: PaddingValues, layoutDirection: LayoutDirection,
                state : trashScreenState
                , onAction : (trashScreenIntent) -> Unit,
                navToTrashDataScreen : (Int)-> Unit,){

    if(state.deleteList.isEmpty()){

        Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Trash is empty",
                fontSize = 30.sp, fontWeight = FontWeight.W600)
        }

    }else{


        Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize()
        ){

            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){

                items(items=state.deleteList){todo->
                    trashCard(
                        title = todo.title,
                        Id = todo.id,
                        createdDate = todo.createdDate,
                        createdHour = todo.createdTimeHour,
                        createdMinute = todo.createdTimeMinute,
                        deadlineDate = todo.deadlineDate,
                        deadlineHour = todo.deadlineTimeHour,
                        deadlineMinute = todo.createdTimeMinute,
                        redirect = {
                            navToTrashDataScreen(todo.id)
                        },
                        delete = {
                            onAction(trashScreenIntent.deleteSingle(todo))
                        },
                        restore = {
                            onAction(trashScreenIntent.restore(todo.id,false))
                        },
                    )
                }
            }

        }
    }
}