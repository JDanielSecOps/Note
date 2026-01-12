package com.app.note.components.themeScreenComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.app.note.screens.themesScreen.themesScreenIntent


data class Theme(
    val title: String,
    val Icon: ImageVector,
)

@Composable
fun themesScreenCard(title : String , Icon : ImageVector,selected : String,onclick : ()-> Unit){

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.secondary,
            RoundedCornerShape(16.dp)
        ).padding(8.dp)
    ){
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)){
            Icon(Icon,title)
            Text(title)
        }
        RadioButton(
            selected= title == selected,
            onClick = { onclick() }
        )
    }

}