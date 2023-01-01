package com.rodrigoguerrero.mynotes.data.dtos

data class NoteDto(
    val id: Int?,
    val title: String?,
    val content: String?,
    val createdTime: Long,
    val modifiedTime: Long
)
