package com.app.note.repository

import com.app.note.source.roomDatabase.TodoTable
import com.app.note.source.roomDatabase.todoDatabase
import kotlinx.coroutines.flow.Flow

class todoRepo(val todoDatabase: todoDatabase) {

    private val todoDatabseInstance =todoDatabase.todoDao()

    val allTodos: Flow<List<TodoTable>> =todoDatabseInstance.getAllTodo(false)
    val getdeletionListOFTodos : Flow<List<TodoTable>> =todoDatabseInstance.getAllDeleted(true)
    fun getTodoesById(id : Int) : Flow<List<TodoTable>> =todoDatabseInstance.getTodobyId(id)

    suspend fun deleteTodo(todo : TodoTable){
        todoDatabseInstance.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: TodoTable){
        todoDatabseInstance.updateTodo(todo)
    }

    suspend fun insertTodo(todo: TodoTable){
        todoDatabseInstance.insertTodo(todo)
    }

    suspend fun updateTempDeleteStatus(id : Int , status : Boolean){
        todoDatabseInstance.updateTempDeleteStatus(id,status)
    }

    suspend fun deleteListOfTodo(todoList : List<TodoTable>){
        todoDatabseInstance.deleteListOfTodo(todoList)
    }

}