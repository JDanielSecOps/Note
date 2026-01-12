package com.app.note.components.settingScreenComponents

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun settingsScreenTopAppBar(){

    TopAppBar(
        title = { Text("Settings") }
    )
}