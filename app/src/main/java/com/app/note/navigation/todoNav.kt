package com.app.note.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

import com.app.note.screens.todoListScreen.ui.todolistScreenRoot
import com.app.note.screens.viewTodoScreen.ui.viewTodoScreenRoot
import com.app.note.viewModels.viewTodoScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun todoNav(
){

        val todoNavBackstack = rememberNavBackStack(Routes.todo.todoListScreen)


        NavDisplay(
            backStack =todoNavBackstack,
            onBack = {
                todoNavBackstack.removeLastOrNull()
            },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {

                entry<Routes.todo.todoListScreen>{routes->
                    todolistScreenRoot(
                        navToAddTodo = {
                            todoNavBackstack.add(Routes.todo.addTodoScreen)
                        },
                        navtoseeTodo = { id ->
                            todoNavBackstack.add(Routes.todo.seeTodo(id))
                        },
                        navtoUpdateTodo ={id->
                            todoNavBackstack.add(Routes.todo.update(id))
                        }
                    )

                }
                entry<Routes.todo.addTodoScreen>{
                    addTodoNav(navToTodoListScreen={
                        todoNavBackstack.remove(Routes.todo.addTodoScreen)
                    })
                }
                entry<Routes.todo.seeTodo>{routesTodoSeeTodo ->
                    seeTodoNav(
                        routesTodoSeeTodo.id,
                        navTodoListScreen ={
                            todoNavBackstack.remove(routesTodoSeeTodo)
                        }
                    )
                }
                entry<Routes.todo.update>{routesTodoUpdate ->
                    updateTodoNav(
                        Id = routesTodoUpdate.id,
                        backToRoot ={
                            todoNavBackstack.remove(routesTodoUpdate)
                        }
                    )
                }
            }
        )



}