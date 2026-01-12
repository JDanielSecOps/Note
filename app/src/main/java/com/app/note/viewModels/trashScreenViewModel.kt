package com.app.note.viewModels

import androidx.compose.ui.input.key.KeyEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.note.repository.todoRepo
import com.app.note.screens.trashScreen.trashScreenIntent
import com.app.note.screens.trashScreen.trashScreenState
import com.app.note.source.roomDatabase.TodoTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class trashScreenViewModel(
    val todoRepo: todoRepo
): ViewModel() {


    val _state = MutableStateFlow(trashScreenState())

    val deletedTodoList = todoRepo.getdeletionListOFTodos.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        emptyList<TodoTable>()
    )

    val state = combine(_state,deletedTodoList){states,deletedTodoLists->
        states.copy(
            deleteList = deletedTodoLists
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        trashScreenState()
    )


    fun onAction(event: trashScreenIntent){
        when(event){
            trashScreenIntent.delete ->{

                val list = state.value.deleteList

                viewModelScope.launch {
                    todoRepo.deleteListOfTodo(list)
                }
            }

            is trashScreenIntent.deleteSingle -> {

                viewModelScope.launch {
                    todoRepo.deleteTodo(event.todo)

                }
            }
            is trashScreenIntent.restore ->{

                viewModelScope.launch {
                    todoRepo.updateTempDeleteStatus(event.Id,event.status)
                }
            }
        }
    }


}