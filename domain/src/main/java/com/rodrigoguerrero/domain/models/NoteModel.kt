package com.rodrigoguerrero.domain.models

data class NoteModel(
    val id: Int? = null,
    val title: String? = null,
    val content: String? = null,
    val created: String? = null,
    val modified: String? = null,
    val isPinned: Boolean? = null
) {
    fun isEmpty() = title.isNullOrEmpty() && content.isNullOrEmpty()
}
