package com.app.note.navigation



import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.app.note.screens.addTodoScreen.ui.addNoteScreenRoot
import com.app.note.screens.addTodoScreen.ui.addTodoScreenRoot
import com.app.note.viewModels.addTodoScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun addTodoNav(
    navToTodoListScreen : ()-> Unit
){
    val addTodoNavBackstack = rememberNavBackStack(Routes.todo.addTodoScreen.addTodoDetails)

    val viewmodel = koinViewModel<addTodoScreenViewModel>()

    NavDisplay(
        backStack =addTodoNavBackstack ,
        onBack = {
            addTodoNavBackstack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {
            entry<Routes.todo.addTodoScreen.addTodoDetails>{
                addTodoScreenRoot(
                    viewModel = viewmodel,
                    navtoNoteScreen = {
                        addTodoNavBackstack.add(Routes.todo.addTodoScreen.addNote)
                    },
                    navtoTodoListScreen ={
                        navToTodoListScreen()
                    }
                )
            }
             entry<Routes.todo.addTodoScreen.addNote>{
                 addNoteScreenRoot(
                     viewModel = viewmodel,
                     navtoaddTodoScreen ={
                         if(addTodoNavBackstack.size>1){
                             addTodoNavBackstack.removeLastOrNull()
                         }
                     },
                     navtoTodoListScreen ={
                        if(addTodoNavBackstack.size>1){
                            navToTodoListScreen()
                        }
                     }
                 )
             }
            
        }
    )






}