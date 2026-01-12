package com.app.note.screens.trashScreen

import com.app.note.source.roomDatabase.TodoTable

sealed interface trashScreenIntent {

    data object delete : trashScreenIntent
    data class restore(val Id : Int , val status : Boolean) : trashScreenIntent
    data class deleteSingle(val todo : TodoTable) : trashScreenIntent

}