package com.app.note.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes : NavKey{

    @Serializable
    data object todo : Routes{

        @Serializable
        data object todoListScreen : Routes

        @Serializable
        data object addTodoScreen : Routes{

            @Serializable
            data object addTodoDetails : Routes

            @Serializable
            data object addNote : Routes

        }

        @Serializable
        data class seeTodo(val id : Int): Routes{

            @Serializable
            data class seeTodoScreen(val id : Int) : Routes

            @Serializable
            data class updateTodo(val Id : Int) : Routes

        }

        @Serializable
        data class update(val id : Int): Routes{

            @Serializable
            data class updateTodoDetails(val id : Int): Routes

            @Serializable
            data object updateNote : Routes

        }

    }


    @Serializable
    data object Settings: Routes{


        @Serializable
        data object settingsScreen : Routes

        @Serializable
        data object ThemesScreen : Routes

        @Serializable
        data object trash : Routes{

            @Serializable
            data object trashScreen : Routes

            @Serializable
            data class seetrashDataScreen(val id : Int) : Routes

        }

    }
}