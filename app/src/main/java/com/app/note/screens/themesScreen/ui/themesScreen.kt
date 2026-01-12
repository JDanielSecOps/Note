package com.app.note.screens.themesScreen.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.themeScreenComponents.Theme
import com.app.note.components.themeScreenComponents.themesScreenCard
import com.app.note.components.themeScreenComponents.themesScreentopAppBar
import com.app.note.screens.themesScreen.themesScreenIntent
import com.app.note.screens.themesScreen.themesScreenState
import com.app.note.viewModels.themesScreenViewModel
import org.koin.compose.viewmodel.koinViewModel


val themes = listOf<Theme>(
    Theme("Light", Icons.Filled.WbSunny),
    Theme("Dark", Icons.Filled.DarkMode),
    Theme("System",Icons.Filled.PhoneAndroid)
)

@Composable
fun themesScreenRoot(navToSettings : ()-> Unit,
                     viewModel: themesScreenViewModel = koinViewModel<themesScreenViewModel>()
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    val layoutDirection = LocalLayoutDirection.current





    Scaffold(topBar = {
        themesScreentopAppBar(navToSettings)
    }){paddingValues ->
        themesScreen(paddingValues,layoutDirection,state, viewModel::onAction)
    }




}


@Composable
fun themesScreen(paddingValues: PaddingValues, layoutDirection: LayoutDirection,
state : themesScreenState, onAction : (themesScreenIntent)-> Unit){



    Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize()){
        Column(verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize().padding(vertical = 8.dp, horizontal = 16.dp)){
            themes.forEach {theme ->
                themesScreenCard(theme.title,theme.Icon,state.selected,{
                    onAction(themesScreenIntent.setTheme(theme.title))
                })
            }
        }
    }
}