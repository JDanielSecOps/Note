package com.app.note.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class settingsDataStore(val dataStore: DataStore<Preferences>) {

    suspend fun storeKeyValue(key : String, value : String){

        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit {Settings->
            Settings[dataStoreKey] =value
        }
    }

    fun readValue(key: String) : Flow<String?>{

        val dataStoreKey = stringPreferencesKey(key)
        return dataStore.data.map {Settings ->
            Settings[dataStoreKey]
        }

    }

}