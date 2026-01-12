package com.app.note.components.globalComponents

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import kotlinx.serialization.Required
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun timePickerDialog(
    Hour: Int?,
    Minute : Int?,
    onConfirm : (TimePickerState)-> Unit,
    onDismiss : ()-> Unit,
){

    val currentTime = LocalDateTime.now()

    val timePickerState =rememberTimePickerState(
        initialHour = Hour ?: currentTime.hour,
        initialMinute =Minute ?:currentTime.minute,
        is24Hour = false,
    )

    BasicAlertDialog(
        onDismissRequest ={onDismiss()},
    ) {
        Surface(shape = RoundedCornerShape(16.dp)){
            Column(Modifier.padding(16.dp)){
                TimeInput(timePickerState)
                Row(modifier = Modifier.align(Alignment.End)){
                    TextButton(
                        onClick = {onDismiss()}
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = {onConfirm(timePickerState)}
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}