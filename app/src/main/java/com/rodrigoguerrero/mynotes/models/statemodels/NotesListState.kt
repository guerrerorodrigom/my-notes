package com.rodrigoguerrero.mynotes.models.statemodels

import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.mynotes.models.uimodels.ListMode
import com.rodrigoguerrero.mynotes.models.uimodels.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class NotesListState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = true,
    val listMode: ListMode = ListMode.LIST,
    val isMultipleSelectionEnabled: Boolean = false
)

fun MutableStateFlow<NotesListState>.updateWithNotes(notes: List<NoteModel>) {
    update { state ->
        state.copy(
            notes = notes.map {
                Note(it.id, it.title, it.content, it.color, it.isPinned)
            },
            isLoading = false
        )
    }
}

fun MutableStateFlow<NotesListState>.updateUnselectAll() {
    update { state ->
        val updatedNotes = state.notes.map { it.copy(isSelected = false) }
        state.copy(isMultipleSelectionEnabled = false, notes = updatedNotes)
    }
}

fun MutableStateFlow<NotesListState>.updateSelectNote(id: Int) {
    update { state ->
        val updatedNotes = state.notes.map {
            if (it.id == id) {
                it.copy(isSelected = !it.isSelected)
            } else {
                it
            }
        }
        state.copy(
            notes = updatedNotes,
            isMultipleSelectionEnabled = updatedNotes.any { it.isSelected },
        )
    }
}

fun MutableStateFlow<NotesListState>.updateListMode(listMode: ListMode) {
    update { state -> state.copy(listMode = listMode) }
}