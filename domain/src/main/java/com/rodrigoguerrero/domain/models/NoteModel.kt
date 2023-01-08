package com.rodrigoguerrero.domain.models

data class NoteModel(
    val id: Int? = null,
    val title: String?,
    val content: String?,
    val created: String? = null,
    val modified: String? = null,
    val isPinned: Boolean
)
