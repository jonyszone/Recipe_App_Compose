package com.shafi.conposetestapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favorites")

class FavoritesRepository(private val context: Context) {

    private val FAVORITE_IDS = stringSetPreferencesKey("favorite_ids")

    val favoriteIds: Flow<Set<Int>> = context.dataStore.data.map { prefs ->
        prefs[FAVORITE_IDS]?.map { it.toInt() }?.toSet() ?: emptySet()
    }

    suspend fun toggleFavorite(id: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[FAVORITE_IDS]?.toMutableSet() ?: mutableSetOf()
            if (current.contains(id.toString())) current.remove(id.toString())
            else current.add(id.toString())
            prefs[FAVORITE_IDS] = current
        }
    }
}
