package com.rodrigoguerrero.mynotes.screens.edit

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.mynotes.components.BottomSheetColorSelector
import com.rodrigoguerrero.mynotes.components.BottomSheetNoteMenu
import com.rodrigoguerrero.mynotes.components.BottomSheetNoteMoreMenu
import com.rodrigoguerrero.mynotes.models.uimodels.EditNoteBottomSheet
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun EditNoteBottomSheetSelection(
    bottomSheetType: EditNoteBottomSheet,
    selectedColor: Color?,
    onColorSelected: (Color?) -> Unit
) {
    when (bottomSheetType) {
        EditNoteBottomSheet.Colors -> BottomSheetColorSelector(
            onColorSelected = onColorSelected,
            selectedColor = selectedColor
        )
        EditNoteBottomSheet.More -> BottomSheetNoteMoreMenu(
            modifier = Modifier.background(color = selectedColor ?: MyNotesTheme.color.surface),
            onOptionSelected = { }
        )
        EditNoteBottomSheet.Options -> BottomSheetNoteMenu(
            modifier = Modifier.background(color = selectedColor ?: MyNotesTheme.color.surface),
            onOptionSelected = { }
        )
    }
}
