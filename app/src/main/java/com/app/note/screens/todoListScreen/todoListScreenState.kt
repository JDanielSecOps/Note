package com.app.note.screens.todoListScreen

import com.app.note.source.roomDatabase.TodoTable


data class todoListScreenState(

    val todolist: List<TodoTable> =listOf<TodoTable>(),
    val isLoading : Boolean =false
)