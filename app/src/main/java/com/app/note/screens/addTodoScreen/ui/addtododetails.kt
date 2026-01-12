package com.app.note.screens.addTodoScreen.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.note.components.addTodoScreenComponenets.addTodoDetailsTAB
import com.app.note.components.globalComponents.DatePickerModal
import com.app.note.components.globalComponents.datePickerTextBox
import com.app.note.components.globalComponents.timePickerTextBox
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.globalComponents.timePickerDialog
import com.app.note.screens.addTodoScreen.addTodoScreenIntent
import com.app.note.screens.addTodoScreen.addTodoScreenState
import com.app.note.viewModels.addTodoScreenViewModel
import java.time.LocalDate



@Composable
fun addTodoScreenRoot(
    viewModel: addTodoScreenViewModel,
    navtoNoteScreen : ()-> Unit,
    navtoTodoListScreen : ()-> Unit
){

    val layoutDirection = LocalLayoutDirection.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { addTodoDetailsTAB("Note Details",navtoTodoListScreen) },
        modifier = Modifier
    ){ paddingValues ->
        addTodoScreen(
            paddingValues, layoutDirection, state, viewModel::onAction,
            navtoNoteScreen = navtoNoteScreen
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addTodoScreen(
    paddingValues: PaddingValues
    ,layoutDirection: LayoutDirection
    ,state: addTodoScreenState
    ,onAction : (addTodoScreenIntent)-> Unit,
    navtoNoteScreen : ()-> Unit){

    val focusManager = LocalFocusManager.current

    Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize()
    ){
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)
            .verticalScroll(rememberScrollState())
        , verticalArrangement = Arrangement.spacedBy(8.dp),){

            Text("Title", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), textAlign = TextAlign.Start)

            OutlinedTextField(
                value = state.title,
                onValueChange ={onAction(addTodoScreenIntent.setTitle(it))},
                label = {Text("Title")},
                placeholder = {Text("Title")},
                maxLines = 1,
                singleLine = true,
                isError = state.showTitleFieldError,
                supportingText ={
                    if(state.showTitleFieldError){
                        Text(state.titleFieldError)
                    }
                },
                modifier = Modifier.fillMaxWidth().onFocusChanged{focusState ->
                    if(focusState.isFocused){
                        onAction(addTodoScreenIntent.setTitleError(false,
                            ""))
                    }
                }

            )

            Text("Timings", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), textAlign = TextAlign.Start)

            datePickerTextBox(
                label = "Created Date", selectedDate = state.createdDateString ?: "",
                showDatePickerModal = {
                    onAction(addTodoScreenIntent.showCreatedDateDialog(true))
                },
                resetDate = {},
                required = true
            )

            if(state.showCreatedDateModal){
                DatePickerModal(
                    date=state.createdDateLong,
                    onDateSelected ={date->
                        onAction(addTodoScreenIntent.setCreatedDate(date ?: LocalDate.now().toEpochDay()))
                    },
                    onDismiss ={
                        onAction(addTodoScreenIntent.showCreatedDateDialog(false))
                    },
                    required = true
                )
            }

            timePickerTextBox(
                "Created Time",
                showTimePickerModal ={
                    onAction( addTodoScreenIntent.showCreatedTimeDialog(true))
                },
                timerequired =true,
                selectedTime =state.createdTimeString
            )

            if(state.showCreatedTimeModal){
                timePickerDialog(
                    Hour = state.createdHour,
                    Minute =state.createdMinute,
                  onDismiss = {
                      onAction(addTodoScreenIntent.showCreatedTimeDialog(false))
                  },
                    onConfirm = {time->
                        onAction(addTodoScreenIntent.setCreatedTime(time.hour,time.minute))
                    }
                )
            }

            datePickerTextBox(
                label = "Deadline Date", selectedDate =state.deadlineDateString ?: "",
                showDatePickerModal = {
                    onAction(addTodoScreenIntent.showDeadlineDateDialog(true))
                },
                resetDate = {
                    onAction(addTodoScreenIntent.setDeadlineDate(null))
                },
                required = false
            )

            if(state.showDeadlineDateModal){
                DatePickerModal(
                    date = state.deadlineDateLong,
                    onDateSelected ={date->
                        onAction(addTodoScreenIntent.setDeadlineDate(date))
                    },
                    onDismiss ={
                        onAction(addTodoScreenIntent.showDeadlineDateDialog(false))
                    },
                    required = false
                )
            }



            timePickerTextBox(
                "Deadline Time",
                showTimePickerModal ={
                   onAction( addTodoScreenIntent.showDeadlineTimeDialog(true))
                },
                resetTime = {
                    onAction(addTodoScreenIntent.setDeadlineTime(null,null))
                },
                timerequired =false,
                selectedTime =state.deadlineTimeString ?: ""
            )


            if(state.showDeadlineTimeModal){
                timePickerDialog(
                    Hour = state.deadlineHour,
                    Minute =state.deadlineMinute,
                    onDismiss = {
                        onAction(addTodoScreenIntent.showDeadlineTimeDialog(false))
                    },
                    onConfirm = {time->
                        onAction(addTodoScreenIntent.setDeadlineTime(time.hour,time.minute))
                    }
                )
            }

            Button(
                onClick = {
                    focusManager.clearFocus()
                    if(state.title.isBlank()){
                        onAction(addTodoScreenIntent.setTitleError(true,
                            "Title Cannot be empty"))
                        return@Button
                    }

                    navtoNoteScreen()},
                content = {Text("Next")},
                modifier = Modifier.align(Alignment.End).padding(vertical = 16.dp)
            )

        }

    }

}



