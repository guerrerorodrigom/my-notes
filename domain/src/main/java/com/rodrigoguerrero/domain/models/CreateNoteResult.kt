package com.rodrigoguerrero.domain.models

data class CreateNoteResult(
    val note: NoteModel?,
    val isError: Boolean
)
