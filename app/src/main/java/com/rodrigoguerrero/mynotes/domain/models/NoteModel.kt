package com.rodrigoguerrero.mynotes.domain.models

data class NoteModel(
    val id: Int? = null,
    val title: String?,
    val content: String?,
    val created: String,
    val modified: String,
    val isPinned: Boolean
)
