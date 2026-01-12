package com.app.note.screens.viewTodoScreen

sealed interface viewTodoScreenIntent {

    data object Delete : viewTodoScreenIntent
    data object restore : viewTodoScreenIntent
    data object PermaDelete : viewTodoScreenIntent
}