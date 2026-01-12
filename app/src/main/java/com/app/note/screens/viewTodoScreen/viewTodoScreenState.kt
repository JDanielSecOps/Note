package com.app.note.screens.viewTodoScreen

import com.app.note.screens.addTodoScreen.addTodoScreenIntent
import com.app.note.source.roomDatabase.TodoTable


data class viewTodoScreenState(

    val todoList : List<TodoTable> =emptyList<TodoTable>(),
    val title : String="",
    val id : Int =0
)