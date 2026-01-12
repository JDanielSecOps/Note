package com.app.note.screens.settingsScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.app.note.components.globalComponents.generalScreenScaffoldPadding
import com.app.note.components.settingScreenComponents.settingsCard
import com.app.note.components.settingScreenComponents.settingsScreenTopAppBar


data class setting(
    val title : String,
    val icon : ImageVector,
    val onClick:()-> Unit
)

@Composable
fun settingsScreenRoot(
    navToThemes:()-> Unit,
    navToTrash:()-> Unit
){
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = { settingsScreenTopAppBar() }
    ) {paddingValues ->
        settingsScreen(paddingValues,layoutDirection,navToThemes,navToTrash)
    }

}


@Composable
fun settingsScreen(paddingValues: PaddingValues,
                   layoutDirection: LayoutDirection,
                   navToThemes:()-> Unit,
                   navToTrash:()-> Unit,
){



    val settings =listOf<setting>(
        setting("Themes", Icons.Filled.Palette,navToThemes),
        setting("Trash",Icons.Filled.Delete,navToTrash)
    )

    Box(modifier = generalScreenScaffoldPadding(paddingValues,layoutDirection).fillMaxSize()){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxHeight()
            .padding(8.dp,8.dp)
            .verticalScroll(rememberScrollState())){
            settings.forEach { setting ->
                settingsCard(setting.title,setting.icon,setting.onClick)
            }
        }
    }
}