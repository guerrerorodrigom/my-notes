package com.rodrigoguerrero.domain.models

sealed class EditNoteResult {
    data class Saved(val note: NoteModel): EditNoteResult()
    object SaveNotNeeded : EditNoteResult()
}
