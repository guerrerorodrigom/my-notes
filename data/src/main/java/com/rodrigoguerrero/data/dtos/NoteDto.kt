package com.rodrigoguerrero.data.dtos

data class NoteDto(
    val id: Int?,
    val title: String?,
    val content: String?,
    val createdTime: String,
    val modifiedTime: String,
    val isPinned: Boolean
)
