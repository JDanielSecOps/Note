package com.app.note.viewModels

import androidx.compose.ui.input.key.KeyEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel

import com.app.note.repository.settingsDataStore
import com.app.note.screens.themesScreen.themesScreenIntent
import com.app.note.screens.themesScreen.themesScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class themesScreenViewModel (val dataStore : settingsDataStore): ViewModel() {

    private val _state = MutableStateFlow(themesScreenState())


    val preferences =dataStore.readValue("Themes")

    val state = combine(_state,preferences){states,preferences->

        states.copy(
            selected = preferences ?: "Dark"
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), themesScreenState())

    fun onAction(event: themesScreenIntent){

        when(event){
            is themesScreenIntent.setTheme -> {

                _state.update {it->
                    it.copy(
                        selected = event.theme
                    )
                }

                viewModelScope.launch {

                    dataStore.storeKeyValue("Themes",event.theme)
                }

            }
        }

    }

}