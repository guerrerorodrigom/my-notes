package com.rodrigoguerrero.mynotes.models.statemodels

data class NoteState(
    val title: String = "",
    val content: String = "",
    val editedDate: String = "",
    val id: Int? = null
)
