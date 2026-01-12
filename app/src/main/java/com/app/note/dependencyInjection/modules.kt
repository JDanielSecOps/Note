package com.app.note.dependencyInjection


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.app.note.repository.settingsDataStore
import com.app.note.repository.todoRepo
import com.app.note.source.roomDatabase.todoDatabase
import com.app.note.viewModels.addTodoScreenViewModel
import com.app.note.viewModels.themesScreenViewModel
import com.app.note.viewModels.todoListScreenViewmodel
import com.app.note.viewModels.trashScreenViewModel
import com.app.note.viewModels.updateTodoViewModel
import com.app.note.viewModels.viewTodoScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = "Settings")

val appModule = module {

    single<DataStore<Preferences>>{
        androidContext().datastore
    }

    single {

        Room.databaseBuilder(
            context = androidApplication(),
            name = "todoDatabase",
            klass = todoDatabase::class.java
        ).build()

    }


    single {
        settingsDataStore(get())
    }

    single {
        todoRepo(get())
    }

    viewModel {
        addTodoScreenViewModel(get())
    }

    viewModel {
        todoListScreenViewmodel(get())
    }

    viewModel {parameters ->
        viewTodoScreenViewModel(
            id =parameters.get(),
            todoRepo = get()
        )
    }

    viewModel {
        trashScreenViewModel(get())
    }

    viewModel {parameters ->
        updateTodoViewModel(parameters.get(),get())
    }

    viewModel {
        themesScreenViewModel(get())
    }
}