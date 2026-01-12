package com.app.note.screens.trashScreen

import com.app.note.source.roomDatabase.TodoTable

data class trashScreenState(

    val deleteList  : List<TodoTable> =emptyList<TodoTable>()
)