package com.rodrigoguerrero.mynotes.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rodrigoguerrero.mynotes.models.uimodels.ListMode
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AppSettingsImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppSettings {
    private val listModePref = intPreferencesKey("list_type")

    override val listMode: Flow<ListMode> = context.dataStore.data
        .map { preferences ->
            val currentValue = preferences[listModePref]
            ListMode.from(currentValue) ?: ListMode.LIST
        }

    override suspend fun toggleListType() {
        context.dataStore.edit { settings ->
            val currentType = settings[listModePref] ?: ListMode.LIST.ordinal
            settings[listModePref] = if (currentType == ListMode.LIST.ordinal) {
                ListMode.GRID.ordinal
            } else {
                ListMode.LIST.ordinal
            }
        }
    }
}
