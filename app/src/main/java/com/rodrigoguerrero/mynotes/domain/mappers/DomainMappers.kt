package com.rodrigoguerrero.mynotes.domain.mappers

import com.rodrigoguerrero.mynotes.data.dtos.NoteDto
import com.rodrigoguerrero.mynotes.domain.models.NoteModel

internal fun NoteModel.mapToDto(): NoteDto {
    val currentTime = System.currentTimeMillis()
    return NoteDto(
        id = id,
        content = content,
        title = title,
        createdTime = currentTime,
        modifiedTime = currentTime
    )
}

internal fun NoteDto.mapToDomain(): NoteModel {
    return NoteModel(
        id = id,
        content = content,
        title = title,
        created = "",
        isPinned = false,
        modified = ""
    )
}
