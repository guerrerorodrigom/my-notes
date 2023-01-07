package com.rodrigoguerrero.data.mappers

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.data.local.entities.NoteEntity

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
        content = content,
        modifiedTime = 0L,
        createdTime = 0L
    )
}
