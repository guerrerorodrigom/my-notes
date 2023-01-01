package com.rodrigoguerrero.mynotes.data.mappers

import com.rodrigoguerrero.mynotes.data.dtos.NoteDto
import com.rodrigoguerrero.mynotes.data.local.entities.NoteEntity

internal fun NoteDto.mapToEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content
    )
}

internal fun NoteEntity.mapToDto(): NoteDto {
    return NoteDto(
        id = id,
        title = title,
        content = content
    )
}
