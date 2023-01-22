package com.rodrigoguerrero.mynotes.models.uimodels

data class Note(
    val id: Int,
    val title: String?,
    val content: String?,
    val color: ULong?,
    val isPinned: Boolean
)
