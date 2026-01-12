@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.note.screens.addTodoScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState

sealed interface addTodoScreenIntent {

    data class setTitle(val title : String) : addTodoScreenIntent

    data class setCreatedDate(val createdDate: Long?)   : addTodoScreenIntent
    data class setDeadlineDate(val deadlineDate: Long?)  : addTodoScreenIntent
    data class showCreatedDateDialog(val showCreatedDateDialog : Boolean)   : addTodoScreenIntent
    data class showDeadlineDateDialog(val showCreatedDateDialog : Boolean)  : addTodoScreenIntent

    data object Submit  : addTodoScreenIntent

    data class showCreatedTimeDialog(val showCreatedDateDialog : Boolean)   : addTodoScreenIntent
    data class showDeadlineTimeDialog(val showCreatedDateDialog : Boolean)  : addTodoScreenIntent

    data class setCreatedTime(val hour : Int , val minute : Int): addTodoScreenIntent

    data class setDeadlineTime(val hour : Int? , val minute : Int?): addTodoScreenIntent

    data class setTitleError(val showTitleError : Boolean,val titleError : String) : addTodoScreenIntent

    data class setTodoError(val showTodoError : Boolean,val todoError : String) : addTodoScreenIntent

}