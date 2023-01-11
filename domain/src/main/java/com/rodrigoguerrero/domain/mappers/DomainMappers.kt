package com.rodrigoguerrero.domain.mappers

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.domain.models.NoteModel
import java.util.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun NoteModel.mapToDto(): NoteDto {
    val currentTime = Clock.System.now()
    return NoteDto(
        id = id,
        content = content,
        title = title,
        createdTime = currentTime.epochSeconds,
        modifiedTime = currentTime.epochSeconds,
        isPinned = isPinned ?: false
    )
}

internal fun NoteDto.mapToDomain(): NoteModel {
    val createdInstant = Instant.fromEpochMilliseconds(createdTime)
    val modifiedInstant = Instant.fromEpochMilliseconds(modifiedTime)

    return NoteModel(
        id = id,
        content = content,
        title = title,
        created = createdInstant.toString(),
        isPinned = isPinned,
        modified = modifiedInstant.toString()
    )
}
