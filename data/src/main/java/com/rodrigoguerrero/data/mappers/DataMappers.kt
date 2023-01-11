package com.rodrigoguerrero.data.mappers

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.data.local.entities.NoteEntity

internal fun NoteDto.mapToEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        createdDate = createdTime,
        modifiedDate = modifiedTime,
        isPinned = isPinned
    )
}

internal fun NoteEntity.mapToDto(): NoteDto {
    return NoteDto(
        id = id,
        title = title,
        content = content,
        modifiedTime = modifiedDate ?: 0,
        createdTime = createdDate ?: 0,
        isPinned = isPinned ?: false
    )
}
