package com.app.note.screens.addTodoScreen.channel

import com.app.note.screens.addTodoScreen.addTodoScreenIntent

sealed class addTodoScreenChannel{

    data object navigateToTodoListScreen : addTodoScreenChannel()
}