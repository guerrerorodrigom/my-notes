package com.rodrigoguerrero.mynotes.ui.models.uimodels

sealed class EditNoteBottomSheet {
    object Options : EditNoteBottomSheet()
    object More : EditNoteBottomSheet()
    object Colors : EditNoteBottomSheet()
}
