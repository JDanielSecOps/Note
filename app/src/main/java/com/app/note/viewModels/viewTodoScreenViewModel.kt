package com.app.note.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.note.repository.todoRepo
import com.app.note.screens.viewTodoScreen.viewTodoScreenIntent
import com.app.note.screens.viewTodoScreen.viewTodoScreenState
import com.app.note.source.roomDatabase.TodoTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class viewTodoScreenViewModel(
    val id : Int,
    val todoRepo: todoRepo,
) : ViewModel() {

    val _state = MutableStateFlow(viewTodoScreenState())


    val todo =todoRepo.getTodoesById(id)

    val state = combine(_state,todo){states,todos->

        val title = todos.firstOrNull()?.title

        states.copy(
            todoList = todos,
            id = id,
            title = title ?: "Note"
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        viewTodoScreenState()
    )

    fun onAction(events : viewTodoScreenIntent){
        when(events){
            viewTodoScreenIntent.Delete ->{

                viewModelScope.launch {
                    todoRepo.updateTempDeleteStatus(id,true)
                }
            }

            viewTodoScreenIntent.restore ->{

                viewModelScope.launch {
                    todoRepo.updateTempDeleteStatus(id,false)
                }

            }

            viewTodoScreenIntent.PermaDelete ->{

                val todo : TodoTable =state.value.todoList.first()

                viewModelScope.launch {
                    todoRepo.deleteTodo(todo)
                }
            }
        }
    }
}