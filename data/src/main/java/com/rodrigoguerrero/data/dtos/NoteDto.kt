package com.rodrigoguerrero.data.dtos

data class NoteDto(
    val id: Int?,
    val title: String?,
    val content: String?,
    val createdTime: Long,
    val modifiedTime: Long,
    val isPinned: Boolean
)
