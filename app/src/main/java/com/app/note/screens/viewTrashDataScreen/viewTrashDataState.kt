package com.app.note.screens.viewTrashDataScreen

import com.app.note.source.roomDatabase.TodoTable


data class viewTrashDataState(

    val todoList : List<TodoTable> =emptyList<TodoTable>(),
    val title : String="",
)