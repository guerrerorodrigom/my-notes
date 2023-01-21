package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.models.uimodels.NoteOption
import com.rodrigoguerrero.mynotes.models.uimodels.moreMenuOptions
import com.rodrigoguerrero.mynotes.models.uimodels.noteMenuOptions
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun BottomSheetNoteMenu(
    modifier: Modifier = Modifier,
    onOptionSelected: (NoteOption) -> Unit
) {
    BottomSheetMenu(
        modifier = modifier,
        options = noteMenuOptions,
        onOptionSelected = onOptionSelected
    )
}

@Composable
fun BottomSheetNoteMoreMenu(
    modifier: Modifier = Modifier,
    onOptionSelected: (NoteOption) -> Unit
) {
    BottomSheetMenu(
        modifier = modifier,
        options = moreMenuOptions,
        onOptionSelected = onOptionSelected
    )
}

@Composable
private fun BottomSheetMenu(
    modifier: Modifier = Modifier,
    options: List<NoteOption>,
    onOptionSelected: (NoteOption) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MyNotesTheme.padding.m)
    ) {
        options.forEach { option ->
            BottomSheetNoteMenuItem(noteOption = option, onClicked = onOptionSelected)
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewBottomSheet() {
    MyNotesTheme {
        BottomSheetNoteMenu(onOptionSelected = {})
    }
}
