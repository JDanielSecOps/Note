package com.app.note.screens.updateTodoScreen.ui.ui



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.note.components.addTodoScreenComponenets.addNoteTAB
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.globalComponents.observeAsEvent
import com.app.note.components.updateTodoScreenComponents.updateNoteTAB
import com.app.note.screens.updateTodoScreen.ui.channel.updateTodoScreenChannel

import com.app.note.screens.updateTodoScreen.ui.updateTodoScreenIntent
import com.app.note.screens.updateTodoScreen.ui.updateTodoScreenState
import com.app.note.viewModels.addTodoScreenViewModel
import com.app.note.viewModels.updateTodoViewModel



@Composable
fun updateNoteScreenRoot(
    viewModel: updateTodoViewModel,
    navToUpdateTodo : ()->Unit,
    navToRoot : ()-> Unit
){

    val layoutDirection = LocalLayoutDirection.current
    val localfocus = LocalFocusManager.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    observeAsEvent(viewModel.channel_events) {events->

        when(events){
            updateTodoScreenChannel.navigateToSeeTodo ->{
                navToRoot()
            }
        }

    }

    Scaffold(
        topBar = {
            updateNoteTAB(
                "Update Note",
                navToHomeScreen = {
                    viewModel.onAction(updateTodoScreenIntent.Update)
                },
                navToupdateTodoScreen = {navToUpdateTodo()}
            )
        },
    ){ paddingValues ->
        updateNoteScreen(viewModel,paddingValues,layoutDirection,state, viewModel::onAction)
    }
}



@Composable
fun updateNoteScreen(
    viewModel: updateTodoViewModel,
    paddingValues: PaddingValues
    ,layoutDirection: LayoutDirection
    ,state: updateTodoScreenState
    ,onAction : (updateTodoScreenIntent)-> Unit){

    val textFieldState = rememberTextFieldState()

    Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize()
        .consumeWindowInsets(paddingValues)
        .imePadding()
    ){

            OutlinedTextField(
                state=viewModel.todofield_state,

                isError = state.showTodoFieldError,
                supportingText ={
                    if(state.showTodoFieldError){
                        Text(state.todoFieldError)
                    }
                },
                modifier = Modifier.fillMaxSize().padding(16.dp).onFocusChanged{focusState ->
                    if(focusState.isFocused){
                        onAction(updateTodoScreenIntent.setTodoError(false,
                            ""))
                    }
                }
            )


    }

}



