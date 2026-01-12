package com.app.note.source.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoTable::class]
, version = 1)

abstract class todoDatabase : RoomDatabase() {

    abstract  fun todoDao() : todoDao
}