package com.app.note.screens.themesScreen

import com.app.note.components.themeScreenComponents.Theme

sealed interface themesScreenIntent {

    data class setTheme(val theme : String) : themesScreenIntent
}