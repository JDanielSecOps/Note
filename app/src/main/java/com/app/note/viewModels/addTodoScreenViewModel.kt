package com.app.note.viewModels

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.note.components.globalComponents.millistoDate
import com.app.note.components.globalComponents.numtoTime
import com.app.note.repository.todoRepo
import com.app.note.screens.addTodoScreen.addTodoScreenIntent
import com.app.note.screens.addTodoScreen.addTodoScreenState
import com.app.note.screens.updateTodoScreen.ui.channel.updateTodoScreenChannel
import com.app.note.source.roomDatabase.TodoTable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset


@OptIn(ExperimentalMaterial3Api::class)
class addTodoScreenViewModel(val todoRepo: todoRepo): ViewModel() {

    val todofield_state = TextFieldState()
    private val _state = MutableStateFlow(addTodoScreenState())
    val state =_state.asStateFlow()


    private val _channel_events = Channel<updateTodoScreenChannel>()
    val channel_events =_channel_events.receiveAsFlow()

    val currentDate = LocalDate.now()
    val currentDateLong=currentDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
    val currenttime = LocalDateTime.now()

    init {
        _state.update {it->
            it.copy(
                createdDateLong =currentDateLong ,
                createdDateString =millistoDate(currentDateLong) ,
                createdMinute =currenttime.minute,
                createdHour = currenttime.hour,
                createdTimeString = numtoTime(currenttime.hour,currenttime.minute)
            )
        }


    }


    fun onAction(events : addTodoScreenIntent){
        when(events){
            addTodoScreenIntent.Submit ->{

                val title               = state.value.title
                val todo                = todofield_state.text.toString()
                val createdDate         = state.value.createdDateLong
                val deadlineDate        = state.value.deadlineDateLong
                val createdTimeHour     = state.value.createdHour
                val createdTimeMinute   = state.value.createdMinute
                val deadlineTimeHour    = state.value.deadlineHour
                val deadlineTimeMinute  = state.value.deadlineMinute


                if(todo.isBlank()){
                    _state.update { it->
                        it.copy(
                            showTodoFieldError = true,
                            todoFieldError = "Note cannot be empty"
                        )
                    }
                    return
                }

                viewModelScope.launch{
                    eventsOnChannel(updateTodoScreenChannel.navigateToSeeTodo)
                }

                val todoentry = TodoTable(
                    title = title,
                    Todo = todo,
                    createdDate = createdDate!!,
                    deadlineDate = deadlineDate,
                    deleted = false,
                    createdTimeHour = createdTimeHour,
                    createdTimeMinute = createdTimeMinute,
                    deadlineTimeHour = deadlineTimeHour,
                    deadlineTimeMinute = deadlineTimeMinute
                )



                Log.v("test", todoentry.toString())

                viewModelScope.launch {
                    todoRepo.insertTodo(todoentry)
                }
            }
            is addTodoScreenIntent.setCreatedDate ->{

                _state.update { it->
                    it.copy(
                        createdDateLong = events.createdDate,
                        createdDateString = millistoDate(events.createdDate)
                    )
                }
            }
            is addTodoScreenIntent.setDeadlineDate ->{

                _state.update { it->
                    it.copy(
                        deadlineDateLong = events.deadlineDate ,
                        deadlineDateString = millistoDate(events.deadlineDate )
                    )
                }

            }
            is addTodoScreenIntent.setTitle ->{

                _state.update { it->
                    it.copy(
                        title = events.title
                    )
                }
            }
            is addTodoScreenIntent.showCreatedDateDialog ->{

                _state.update { it->
                    it.copy(
                        showCreatedDateModal = events.showCreatedDateDialog
                    )
                }
            }
            is addTodoScreenIntent.showDeadlineDateDialog ->{

                _state.update { it->
                    it.copy(
                        showDeadlineDateModal = events.showCreatedDateDialog
                    )
                }

            }

            is addTodoScreenIntent.showCreatedTimeDialog ->{

                _state.update { it ->
                    it.copy(
                        showCreatedTimeModal = events.showCreatedDateDialog
                    )
                }
            }
            is addTodoScreenIntent.showDeadlineTimeDialog ->{

                _state.update { it->
                    it.copy(
                        showDeadlineTimeModal =events.showCreatedDateDialog
                    )
                }
            }

            is addTodoScreenIntent.setCreatedTime ->{

                val hour =events.hour
                val minutes =events.minute

                _state.update { it->
                    it.copy(
                        createdHour =hour,
                        createdMinute = minutes,
                        createdTimeString = numtoTime(hour,minutes),
                        showCreatedTimeModal = false
                    )
                }
            }

            is addTodoScreenIntent.setDeadlineTime ->{

                val hour =events.hour
                val minutes =events.minute

                _state.update { it->
                    it.copy(
                        deadlineHour =hour,
                        deadlineMinute = minutes,
                        deadlineTimeString = numtoTime(hour,minutes),
                        showDeadlineTimeModal = false
                    )
                }
            }

            is addTodoScreenIntent.setTitleError ->{

                _state.update {it ->

                    it.copy(
                        showTitleFieldError = events.showTitleError,
                        titleFieldError = events.titleError
                    )
                }
            }
            is addTodoScreenIntent.setTodoError ->{

                _state.update {it ->

                    it.copy(
                        showTodoFieldError = events.showTodoError,
                        todoFieldError = events.todoError
                    )
                }


            }

        }
    }


    suspend fun eventsOnChannel(event: updateTodoScreenChannel){
        _channel_events.send(event)
    }


}