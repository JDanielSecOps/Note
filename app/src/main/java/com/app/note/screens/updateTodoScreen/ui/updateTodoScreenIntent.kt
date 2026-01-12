@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.note.screens.updateTodoScreen.ui

import androidx.compose.material3.ExperimentalMaterial3Api

sealed interface updateTodoScreenIntent {

    data class setTitle(val title : String) : updateTodoScreenIntent

    data class setCreatedDate(val createdDate: Long?)   : updateTodoScreenIntent
    data class setDeadlineDate(val deadlineDate: Long?)  : updateTodoScreenIntent
    data class showCreatedDateDialog(val showCreatedDateDialog : Boolean)   : updateTodoScreenIntent
    data class showDeadlineDateDialog(val showCreatedDateDialog : Boolean)  : updateTodoScreenIntent

    data object Update  : updateTodoScreenIntent

    data class showCreatedTimeDialog(val showCreatedDateDialog : Boolean)   : updateTodoScreenIntent
    data class showDeadlineTimeDialog(val showCreatedDateDialog : Boolean)  : updateTodoScreenIntent

    data class setCreatedTime(val hour : Int , val minute : Int): updateTodoScreenIntent

    data class setDeadlineTime(val hour : Int? , val minute : Int?): updateTodoScreenIntent

    data class setTitleError(val showTitleError : Boolean,val titleError : String) : updateTodoScreenIntent

    data class setTodoError(val showTodoError : Boolean,val todoError : String) : updateTodoScreenIntent

}