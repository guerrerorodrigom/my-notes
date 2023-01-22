package com.rodrigoguerrero.mynotes.models.statemodels

import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.domain.models.NoteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

sealed class EditNoteState {
    data class ContentState(
        val title: String,
        val content: String,
        val editedDate: String,
        val createdDate: String,
        val id: Int,
        val color: Color?,
        val isPinned: Boolean
    ) : EditNoteState()

    object LoadingState : EditNoteState()
    object ErrorState : EditNoteState()
}

fun EditNoteState.ContentState.toDomainModel() = NoteModel(
    id = id,
    title = title,
    content = content,
    modified = editedDate,
    created = createdDate,
    color = color?.value,
    isPinned = isPinned
)

fun MutableStateFlow<EditNoteState>.updateWithNote(note: NoteModel) {
    update {
        with(note) {
            EditNoteState.ContentState(
                title = title.orEmpty(),
                content = content.orEmpty(),
                editedDate = modified,
                createdDate = created,
                id = id,
                color = color?.let { Color(it) },
                isPinned = isPinned
            )
        }
    }
}

fun MutableStateFlow<EditNoteState>.updateTitle(title: String) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(title = title)
        } else {
            it
        }
    }
}

fun MutableStateFlow<EditNoteState>.updateContent(value: String) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(content = value)
        } else {
            it
        }
    }
}

fun MutableStateFlow<EditNoteState>.updateNoteColor(value: Color?) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(color = value)
        } else {
            it
        }
    }
}

fun MutableStateFlow<EditNoteState>.toggleIsPinned() {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(isPinned = !it.isPinned)
        } else {
            it
        }
    }
}