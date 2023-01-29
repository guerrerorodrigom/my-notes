package com.rodrigoguerrero.mynotes.models.statemodels

import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.mynotes.models.uimodels.ListMode
import com.rodrigoguerrero.mynotes.models.uimodels.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class NotesListState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = true,
    val listMode: ListMode = ListMode.LIST,
    val isMultipleSelectionEnabled: Boolean = false,
    val selectedNotesArePinned: Boolean = false,
    val selectedNotesColor: Color? = null
)

fun MutableStateFlow<NotesListState>.updateWithNotes(notes: List<NoteModel>) {
    update { state ->
        state.copy(
            notes = notes.map { model ->
                Note(
                    id = model.id,
                    title = model.title,
                    content = model.content,
                    color = model.color?.let { color -> Color(color) },
                    isPinned = model.isPinned,
                    editedDate = model.modified,
                    createdDate = model.created
                )
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
        val selectedNotes = updatedNotes.filter { it.isSelected }
        state.copy(
            notes = updatedNotes,
            isMultipleSelectionEnabled = selectedNotes.isNotEmpty(),
            selectedNotesArePinned = selectedNotes.all { it.isPinned },
            selectedNotesColor = if (selectedNotes.distinctBy { it.color }.size == 1) {
                selectedNotes[0].color ?: Color.Transparent
            } else {
                null
            }
        )
    }
}

fun MutableStateFlow<NotesListState>.updateListMode(listMode: ListMode) {
    update { state -> state.copy(listMode = listMode) }
}