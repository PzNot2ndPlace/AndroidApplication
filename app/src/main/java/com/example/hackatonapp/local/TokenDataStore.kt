package com.example.hackatonapp.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "auth_prefs"
private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class TokenDataStore(private val context: Context) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    val tokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[TOKEN_KEY] }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}