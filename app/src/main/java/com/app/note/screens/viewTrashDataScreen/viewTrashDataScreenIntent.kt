package com.app.note.screens.viewTrashDataScreen

sealed interface viewTrashDataScreenIntent {

    data object Delete : viewTrashDataScreenIntent
    data object ResStore : viewTrashDataScreenIntent
}