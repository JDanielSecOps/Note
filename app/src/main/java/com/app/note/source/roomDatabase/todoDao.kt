package com.app.note.source.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface todoDao{

    @Query("SELECT * FROM todotable WHERE deleted = :deleted")
    fun getAllTodo(deleted : Boolean) : Flow<List<TodoTable>>

    @Query("SELECT * FROM todotable WHERE deleted = :deleted")
    fun getAllDeleted(deleted : Boolean) : Flow<List<TodoTable>>

    @Query("SELECT * FROM todotable WHERE id = :id")
    fun getTodobyId(id : Int) : Flow<List<TodoTable>>

    @Query("UPDATE todotable SET deleted = :status WHERE id = :id")
    suspend fun updateTempDeleteStatus(id: Int , status : Boolean)
    @Insert
    suspend fun insertTodo(Todo : TodoTable)

    @Update
    suspend fun updateTodo(Todo: TodoTable)

    @Delete
    suspend fun deleteTodo(Todo: TodoTable)

    @Delete
    suspend fun deleteListOfTodo(todoList : List<TodoTable>)
}