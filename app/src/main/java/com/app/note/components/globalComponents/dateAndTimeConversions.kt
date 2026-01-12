package com.app.note.components.globalComponents

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun millistoDate(millis : Long?) : String?{

    if(millis is Long){
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }else{
        return null
    }
}


fun numtoTime(Hour: Int? , Min : Int?) : String{

    if(Hour is Int && Min is Int){
        val localtime = LocalTime.of(Hour,Min)
        val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault())
        return localtime.format(formatter)
    }
    else  return  ""
}