package com.app.note.components.globalComponents

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import kotlinx.serialization.Required
import java.time.LocalDate



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DatePickerModal(
    date : Long?,
    onDateSelected : (Long?)-> Unit,
    onDismiss : ()-> Unit,
    required: Boolean
){



    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker
    , initialSelectedDateMillis = date)



    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        modifier = Modifier.scale(0.9f)
    ) {
        DatePicker(datePickerState, showModeToggle = false)
    }
}