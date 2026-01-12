package com.app.note.components.settingScreenComponents

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun settingsCard(
    title : String,icon : ImageVector,onClick:()-> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically
        , horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.border(2.dp, MaterialTheme.colorScheme.secondary,
            RoundedCornerShape(16.dp))
            .padding(8.dp).clickable{onClick()}
            .fillMaxWidth()){

        Icon(icon,title, modifier = Modifier.padding(16.dp))
        Text(title, modifier = Modifier.padding(vertical =16.dp, horizontal = 8.dp))

    }
}