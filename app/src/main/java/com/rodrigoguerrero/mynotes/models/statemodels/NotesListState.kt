package com.rodrigoguerrero.mynotes.models.statemodels

import com.rodrigoguerrero.mynotes.models.uimodels.ListMode
import com.rodrigoguerrero.mynotes.models.uimodels.Note

data class NotesListState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = true,
    val listMode: ListMode = ListMode.LIST
)
