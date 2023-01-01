package com.rodrigoguerrero.mynotes.domain.models

data class CreateNoteResult(
    val note: NoteModel?,
    val isError: Boolean
)
