package com.rodrigoguerrero.mynotes.models.uimodels

sealed class EditNoteBottomSheet {
    object Options : EditNoteBottomSheet()
    object More : EditNoteBottomSheet()
    object Colors : EditNoteBottomSheet()
}
