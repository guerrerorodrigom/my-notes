package com.rodrigoguerrero.mynotes.models.mappers

import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.mynotes.models.uimodels.Note

fun Note.toDomainModel() = NoteModel(
    id = id,
    title = title,
    content = content,
    modified = editedDate,
    created = createdDate,
    color = color?.value,
    isPinned = isPinned
)

fun NoteModel.toUiModel() = Note(
    id = id,
    title = title,
    content = content,
    editedDate = modified,
    createdDate = created,
    color = color?.let { Color(it) },
    isPinned = isPinned
)