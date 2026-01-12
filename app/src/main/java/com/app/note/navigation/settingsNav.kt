package com.app.note.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.app.note.screens.settingsScreen.ui.settingsScreenRoot
import com.app.note.screens.themesScreen.ui.themesScreenRoot
import com.app.note.screens.trashScreen.ui.trashScreenRoot

@Composable
fun setttingsNav(

){
        val settingsNavBackstack = rememberNavBackStack(Routes.Settings.settingsScreen)



        NavDisplay(
            backStack =settingsNavBackstack ,
            onBack = {
                settingsNavBackstack.removeLastOrNull()
            },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),

            entryProvider = entryProvider {

                entry<Routes.Settings.settingsScreen>{

                    settingsScreenRoot(
                        navToThemes ={
                            settingsNavBackstack.add(Routes.Settings.ThemesScreen)
                        },
                        navToTrash={
                            settingsNavBackstack.add(Routes.Settings.trash)
                        }
                    )

                }
                entry<Routes.Settings.ThemesScreen>{

                    themesScreenRoot(navToSettings={
                        if(settingsNavBackstack.size>1){
                            settingsNavBackstack.removeLastOrNull()
                        }
                    })

                }
                entry<Routes.Settings.trash>{
                    trashNav(
                        navToSettings = {
                            if(settingsNavBackstack.size>1){
                                settingsNavBackstack.removeLastOrNull()
                            }
                        }
                    )
                }
            }
        )
        
        



  
}