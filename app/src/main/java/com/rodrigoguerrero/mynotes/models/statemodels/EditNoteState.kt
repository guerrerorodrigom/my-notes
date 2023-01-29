package com.rodrigoguerrero.mynotes.models.statemodels

import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.mynotes.models.mappers.toUiModel
import com.rodrigoguerrero.mynotes.models.uimodels.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

sealed class EditNoteState {
    data class ContentState(val note: Note) : EditNoteState()
    object LoadingState : EditNoteState()
    object ErrorState : EditNoteState()
}

fun MutableStateFlow<EditNoteState>.updateWithNote(note: NoteModel) {
    update { EditNoteState.ContentState(note = note.toUiModel()) }
}

fun MutableStateFlow<EditNoteState>.updateTitle(title: String) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(note = it.note.copy(title = title))
        } else {
            it
        }
    }
}

fun MutableStateFlow<EditNoteState>.updateContent(value: String) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(note = it.note.copy(content = value))
        } else {
            it
        }
    }
}

fun MutableStateFlow<EditNoteState>.updateNoteColor(value: Color?) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(note = it.note.copy(color = value))
        } else {
            it
        }
    }
}

fun MutableStateFlow<EditNoteState>.toggleIsPinned() {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(note = it.note.copy(isPinned = !it.note.isPinned))
        } else {
            it
        }
    }
}