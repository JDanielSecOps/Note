package com.app.note.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.note.repository.todoRepo
import com.app.note.screens.todoListScreen.todoListScreenIntent
import com.app.note.screens.todoListScreen.todoListScreenState
import com.app.note.source.roomDatabase.TodoTable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class todoListScreenViewmodel(val todorepo : todoRepo) : ViewModel(){


    private val _state = MutableStateFlow(todoListScreenState())
    private val todolist = todorepo.allTodos


    val state  = combine(_state,todolist){state,todolist->
        state.copy(
            todolist = todolist
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), todoListScreenState(
        isLoading = true
    ))


    fun onAction(events : todoListScreenIntent){

        when(events){
            is todoListScreenIntent.deleteSingle ->{
                viewModelScope.launch {
                    todorepo.updateTempDeleteStatus(events.id,true)
                }
            }
        }
    }
}