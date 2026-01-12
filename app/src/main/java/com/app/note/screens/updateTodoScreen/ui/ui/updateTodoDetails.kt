package com.app.note.screens.updateTodoScreen.ui.ui

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
import com.app.note.screens.updateTodoScreen.ui.updateTodoScreenIntent
import com.app.note.screens.updateTodoScreen.ui.updateTodoScreenState
import com.app.note.viewModels.updateTodoViewModel
import java.time.LocalDate



@Composable
fun updateTodoScreenRoot(
    viewModel: updateTodoViewModel,
    navtoNote : ()-> Unit,
    navtoHome : ()-> Unit
){

    val layoutDirection = LocalLayoutDirection.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { addTodoDetailsTAB("Update Details",{ navtoHome() }) },
        modifier = Modifier
    ){ paddingValues ->
        updateTodoScreen(
            paddingValues, layoutDirection, state, viewModel::onAction,
            navtoNote
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun updateTodoScreen(
    paddingValues: PaddingValues
    , layoutDirection: LayoutDirection
    , state: updateTodoScreenState
    , onAction : (updateTodoScreenIntent)-> Unit,
    navtoNote: ()-> Unit){

    val focusManager = LocalFocusManager.current

    Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize()
    ){
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)
            .verticalScroll(rememberScrollState())
        , verticalArrangement = Arrangement.spacedBy(8.dp),){

            Text("Title", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), textAlign = TextAlign.Start)

            OutlinedTextField(
                value = state.title,
                onValueChange ={onAction(updateTodoScreenIntent.setTitle(it))},
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
                        onAction(updateTodoScreenIntent.setTitleError(false,
                            ""))
                    }
                }

            )

            Text("Timings", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), textAlign = TextAlign.Start)

            datePickerTextBox(
                label = "Created Date", selectedDate = state.createdDateString ?: "",
                showDatePickerModal = {
                    onAction(updateTodoScreenIntent.showCreatedDateDialog(true))
                },
                resetDate = {},
                required = true
            )

            if(state.showCreatedDateModal){
                DatePickerModal(
                    date=state.createdDateLong,
                    onDateSelected ={date->
                        onAction(updateTodoScreenIntent.setCreatedDate(date ?: LocalDate.now().toEpochDay()))
                    },
                    onDismiss ={
                        onAction(updateTodoScreenIntent.showCreatedDateDialog(false))
                    },
                    required = true
                )
            }

            timePickerTextBox(
                "Created Time",
                showTimePickerModal ={
                    onAction( updateTodoScreenIntent.showCreatedTimeDialog(true))
                },
                timerequired =true,
                selectedTime =state.createdTimeString
            )

            if(state.showCreatedTimeModal){
                timePickerDialog(
                    Hour = state.createdHour,
                    Minute =state.createdMinute,
                  onDismiss = {
                      onAction(updateTodoScreenIntent.showCreatedTimeDialog(false))
                  },
                    onConfirm = {time->
                        onAction(updateTodoScreenIntent.setCreatedTime(time.hour,time.minute))
                    }
                )
            }

            datePickerTextBox(
                label = "Deadline Date", selectedDate =state.deadlineDateString ?: "",
                showDatePickerModal = {
                    onAction(updateTodoScreenIntent.showDeadlineDateDialog(true))
                },
                resetDate = {
                    onAction(updateTodoScreenIntent.setDeadlineDate(null))
                },
                required = false
            )

            if(state.showDeadlineDateModal){
                DatePickerModal(
                    date = state.deadlineDateLong,
                    onDateSelected ={date->
                        onAction(updateTodoScreenIntent.setDeadlineDate(date))
                    },
                    onDismiss ={
                        onAction(updateTodoScreenIntent.showDeadlineDateDialog(false))
                    },
                    required = false
                )
            }



            timePickerTextBox(
                "Deadline Time",
                showTimePickerModal ={
                   onAction( updateTodoScreenIntent.showDeadlineTimeDialog(true))
                },
                resetTime = {
                    onAction(updateTodoScreenIntent.setDeadlineTime(null,null))
                },
                timerequired =false,
                selectedTime =state.deadlineTimeString ?: ""
            )


            if(state.showDeadlineTimeModal){
                timePickerDialog(
                    Hour = state.deadlineHour,
                    Minute =state.deadlineMinute,
                    onDismiss = {
                        onAction(updateTodoScreenIntent.showDeadlineTimeDialog(false))
                    },
                    onConfirm = {time->
                        onAction(updateTodoScreenIntent.setDeadlineTime(time.hour,time.minute))
                    }
                )
            }

            Button(
                onClick = {
                    focusManager.clearFocus()
                    if(state.title.isBlank()){
                        onAction(updateTodoScreenIntent.setTitleError(true,
                            "Title Cannot be empty"))
                        return@Button
                    }

                    navtoNote()},
                content = {Text("Next")},
                modifier = Modifier.align(Alignment.End).padding(vertical = 16.dp)
            )

        }

    }

}



