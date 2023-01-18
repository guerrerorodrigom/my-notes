package com.rodrigoguerrero.mynotes.models.statemodels

sealed class EditNoteState {
    data class ContentState(
        val title: String,
        val content: String,
        val editedDate: String,
        val id: Int
    ) : EditNoteState()

    object LoadingState : EditNoteState()
    object ErrorState : EditNoteState()
}
