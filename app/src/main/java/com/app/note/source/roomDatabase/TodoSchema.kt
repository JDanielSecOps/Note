package com.app.note.source.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTable(

    @PrimaryKey(autoGenerate = true) val id : Int=0,

    @ColumnInfo("title")val title: String,
    @ColumnInfo(name = "Todo") val Todo : String,

    @ColumnInfo(name="created_date") val createdDate : Long,

    @ColumnInfo(name ="created_Hour") val createdTimeHour : Int,
    @ColumnInfo(name ="created_Minute") val createdTimeMinute : Int,



    @ColumnInfo(name = "deadline_date") val deadlineDate: Long?,

    @ColumnInfo(name ="deadline_Hour") val deadlineTimeHour : Int?,
    @ColumnInfo(name ="deadline_Minute") val deadlineTimeMinute : Int?,


    @ColumnInfo(name="deleted") val deleted : Boolean

)
