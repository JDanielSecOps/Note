package com.app.note.components.globalComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier



@Composable
fun timePickerTextBox(label : String,
                      showTimePickerModal:() -> Unit,
                      resetTime:(()-> Unit)? =null,
                      timerequired: Boolean,
                      modifier: Modifier= Modifier,
                      selectedTime : String){

    OutlinedTextField(
        value =selectedTime,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        placeholder = {Text("HH:MM")},
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Row {
                IconButton(
                    onClick = {showTimePickerModal()}
                ) {
                    Icon(Icons.Outlined.Alarm,null)
                }
                if(!timerequired){
                    IconButton(
                        onClick = {
                            resetTime?.invoke()
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Clear,null)
                    }
                }
            }
        }

    )
}