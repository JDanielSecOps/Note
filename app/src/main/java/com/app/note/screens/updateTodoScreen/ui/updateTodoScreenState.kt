package com.app.note.screens.updateTodoScreen.ui

data class updateTodoScreenState(


    val showCreatedDateModal : Boolean =false,
    val showDeadlineDateModal : Boolean =false,

    val title : String ="",
    val todo : String ="",

    val createdDateString : String?="",
    val createdDateLong : Long?=0,

    val deadlineDateString : String? =null,
    val deadlineDateLong : Long?=null,

    val showCreatedTimeModal : Boolean =false,
    val showDeadlineTimeModal : Boolean =false,

    val createdHour : Int =0,
    val createdMinute : Int =0,

    val createdTimeString: String ="",

    val deadlineHour : Int? =null,
    val deadlineMinute : Int? =null,
    val deadlineTimeString: String? ="",

    val showTitleFieldError : Boolean =false,
    val titleFieldError : String="",

    val showTodoFieldError : Boolean = false,
    val todoFieldError : String=""

)