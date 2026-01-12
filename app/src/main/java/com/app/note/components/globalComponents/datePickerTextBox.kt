package com.app.note.components.globalComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Required


@Composable
fun datePickerTextBox(label : String,
                      showDatePickerModal:() -> Unit,
                      resetDate:()-> Unit,
                      modifier: Modifier= Modifier,
                      selectedDate : String,
                      required : Boolean){

    OutlinedTextField(
        value =selectedDate,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        placeholder = {Text("DD/MM/YYYY")},
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Row {
                IconButton(
                    onClick = {showDatePickerModal()}
                ) {
                    Icon(Icons.Filled.CalendarToday,null)
                }
                if(!required){
                    IconButton(
                        onClick = {resetDate()}
                    ) {
                        Icon(imageVector = Icons.Filled.Clear,null)
                    }
                }
            }
        }

    )
}