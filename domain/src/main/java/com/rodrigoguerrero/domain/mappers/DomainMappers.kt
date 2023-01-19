package com.rodrigoguerrero.domain.mappers

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.domain.models.NoteModel
import kotlinx.datetime.Clock

internal fun NoteModel.mapToDto(
    createdTime: String?,
    modifiedTime: String?
): NoteDto {
    val currentTime = Clock.System.now()
    return NoteDto(
        id = id,
        content = content,
        title = title,
        createdTime = createdTime ?: currentTime.toString(),
        modifiedTime = modifiedTime ?: currentTime.toString(),
        isPinned = isPinned
    )
}

internal fun NoteDto.mapToDomain(): NoteModel {
    return NoteModel(
        id = id ?: 0,
        content = content,
        title = title,
        created = createdTime,
        isPinned = isPinned,
        modified = modifiedTime
    )
}
