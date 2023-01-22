package com.rodrigoguerrero.mynotes.settings

import com.rodrigoguerrero.mynotes.models.uimodels.ListMode
import kotlinx.coroutines.flow.Flow

interface AppSettings {

    val listMode: Flow<ListMode>

    suspend fun toggleListType()
}