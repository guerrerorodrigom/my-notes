package com.rodrigoguerrero.mynotes.screens.edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.mynotes.components.BottomSheetColorSelector
import com.rodrigoguerrero.mynotes.components.BottomSheetNoteMenu
import com.rodrigoguerrero.mynotes.components.BottomSheetNoteMoreMenu
import com.rodrigoguerrero.mynotes.models.uimodels.EditNoteBottomSheet

@Composable
fun EditNoteBottomSheetSelection(
    bottomSheetType: EditNoteBottomSheet,
    onColorSelected: (Color) -> Unit
) {
    when (bottomSheetType) {
        EditNoteBottomSheet.Colors -> BottomSheetColorSelector(
            onColorSelected = onColorSelected,
            selectedColor = Color.Transparent
        )
        EditNoteBottomSheet.More -> BottomSheetNoteMoreMenu(
            onOptionSelected = { }
        )
        EditNoteBottomSheet.Options -> BottomSheetNoteMenu(
            onOptionSelected = { }
        )
    }
}
