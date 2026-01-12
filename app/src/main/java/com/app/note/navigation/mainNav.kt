package com.app.note.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.app.note.components.globalComponents.NavBar
import com.app.note.components.globalComponents.scaffoldPaddingForBottomBar


@Composable
fun mainNavigation(){

        val rootbackstack = rememberNavBackStack(Routes.todo)


        Scaffold(
            bottomBar = {

                    NavBar(selected = rootbackstack.last(),
                        onselect = {location->
                            rootbackstack.clear()
                            rootbackstack.add(location)
                        })

            }
        )
        {paddingValues ->

            NavDisplay(
                modifier = scaffoldPaddingForBottomBar(paddingValues),
                backStack = rootbackstack,
                onBack = {
                    rootbackstack.removeLastOrNull()
                },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    entry<Routes.todo>{
                        todoNav()
                    }
                    entry<Routes.Settings>{
                        setttingsNav()
                    }

                }
            )
        }




}