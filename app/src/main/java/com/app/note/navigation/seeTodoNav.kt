package com.app.note.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.app.note.screens.settingsScreen.ui.settingsScreenRoot
import com.app.note.screens.themesScreen.ui.themesScreenRoot
import com.app.note.screens.viewTodoScreen.ui.viewTodoScreenRoot
import com.app.note.viewModels.viewTodoScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun seeTodoNav(
    Id : Int,
    navTodoListScreen : ()-> Unit
){

    val seeTodoNavBackStack = rememberNavBackStack(Routes.todo.seeTodo.seeTodoScreen(id = Id))



    NavDisplay(
        backStack =seeTodoNavBackStack  ,
        onBack = {
            seeTodoNavBackStack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {

           entry<Routes.todo.seeTodo.seeTodoScreen>{routes->

               viewTodoScreenRoot(
                   navToTodoListScreen = {
                       navTodoListScreen()
                   },
                   viewModel = koinViewModel<viewTodoScreenViewModel>(
                       parameters = {
                           parametersOf(routes.id)
                       }
                   ),
                   navToUpdate ={id->
                       seeTodoNavBackStack.add(Routes.todo.seeTodo.updateTodo(id))
                   }
               )


           }

            entry<Routes.todo.seeTodo.updateTodo>{routesTodoSeeTodoUpdateTodo ->
                updateTodoNav(
                    Id = routesTodoSeeTodoUpdateTodo.Id,
                    backToRoot ={
                        seeTodoNavBackStack.remove(routesTodoSeeTodoUpdateTodo)
                    }
                )
            }

        }
    )






}