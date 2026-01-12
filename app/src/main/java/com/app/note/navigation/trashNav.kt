package com.app.note.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.app.note.screens.addTodoScreen.ui.addNoteScreenRoot
import com.app.note.screens.addTodoScreen.ui.addTodoScreenRoot
import com.app.note.screens.trashScreen.ui.trashScreenRoot
import com.app.note.screens.viewTrashDataScreen.ui.viewTrashDataScreenRoot
import com.app.note.viewModels.addTodoScreenViewModel
import com.app.note.viewModels.trashScreenViewModel
import com.app.note.viewModels.viewTodoScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun trashNav(

    navToSettings : ()-> Unit

){
    val trashNavBackstack = rememberNavBackStack(Routes.Settings.trash.trashScreen)

    val viewmodel = koinViewModel<addTodoScreenViewModel>()

    NavDisplay(
        backStack =trashNavBackstack ,
        onBack = {
            trashNavBackstack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {

            entry<Routes.Settings.trash.trashScreen>{routes->

                trashScreenRoot(
                    navToSettings ={
                        navToSettings()
                    },
                    navToTrashDataScreen = {Id->
                        trashNavBackstack.add(Routes.Settings.trash.seetrashDataScreen(id = Id))
                    }
                )

            }
            entry<Routes.Settings.trash.seetrashDataScreen>{routes->
                viewTrashDataScreenRoot(
                    navtoTrashScreen = {
                        if(trashNavBackstack.size >1){
                            trashNavBackstack.removeLastOrNull()
                        }
                    },
                    viewModel =koinViewModel<viewTodoScreenViewModel>(
                        parameters = {parametersOf(routes.id)}
                    )
                )
            }
        }

    )
}