package com.app.note.components.globalComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.app.note.navigation.Routes


data class location(

    val title : String,
    val Icons : ImageVector,
    val Route : NavKey
)

val locations =listOf<location>(
    location("Notes", Icons.AutoMirrored.Filled.Note, Routes.todo),
    location("Settings", Icons.Filled.Settings, Routes.Settings)
)


@Composable
fun NavBar(
    selected : NavKey,
    onselect :(NavKey)-> Unit
){

    NavigationBar{
    locations.forEach { location ->
        NavigationBarItem(
            selected = location.Route==selected,
            onClick ={onselect(location.Route)},
            icon ={ Icon(imageVector = location.Icons, contentDescription =location.title) },
            label = { Text(location.title) }
        )
    }
    }
}