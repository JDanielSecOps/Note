package com.app.note.navigation

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.app.note.screens.trashScreen.ui.trashScreenRoot
import com.app.note.screens.updateTodoScreen.ui.ui.updateNoteScreenRoot
import com.app.note.screens.updateTodoScreen.ui.ui.updateTodoScreenRoot
import com.app.note.screens.viewTrashDataScreen.ui.viewTrashDataScreenRoot
import com.app.note.viewModels.addTodoScreenViewModel
import com.app.note.viewModels.updateTodoViewModel
import com.app.note.viewModels.viewTodoScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun updateTodoNav(
    Id : Int,
    backToRoot :()-> Unit,
){
    val updateNavBackstack = rememberNavBackStack(Routes.todo.update.updateTodoDetails(Id))

    val viewmodel = koinViewModel<updateTodoViewModel>(
        parameters = {parametersOf(Id)}
    )

    NavDisplay(
        backStack =updateNavBackstack ,
        onBack = {
            updateNavBackstack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {

            entry<Routes.todo.update.updateTodoDetails>{
                updateTodoScreenRoot(
                    viewModel = viewmodel,
                    navtoNote = {
                        updateNavBackstack.add(Routes.todo.update.updateNote)
                    },
                    navtoHome ={
                        backToRoot()
                    }
                )
            }
            entry<Routes.todo.update.updateNote>{
                updateNoteScreenRoot(
                    viewModel = viewmodel,
                    navToUpdateTodo = {
                        if(updateNavBackstack.size > 1){
                            updateNavBackstack.removeLastOrNull()
                        }
                    },
                    navToRoot = {
                        backToRoot()
                    },
                )
            }

        }

    )
}