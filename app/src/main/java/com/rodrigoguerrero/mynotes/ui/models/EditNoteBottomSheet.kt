package com.rodrigoguerrero.mynotes.ui.models

sealed class EditNoteBottomSheet {
    object Options : EditNoteBottomSheet()
    object More : EditNoteBottomSheet()
    object Colors : EditNoteBottomSheet()
}
