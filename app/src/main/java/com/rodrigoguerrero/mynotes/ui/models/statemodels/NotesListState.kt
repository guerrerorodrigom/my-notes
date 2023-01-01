package com.rodrigoguerrero.mynotes.ui.models.statemodels

import com.rodrigoguerrero.mynotes.ui.models.uimodels.Note

data class NotesListState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = true
)
