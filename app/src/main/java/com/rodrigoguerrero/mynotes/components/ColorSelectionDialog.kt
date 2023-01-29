package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.models.uimodels.NoteColor
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun ColorSelectionDialog(
    modifier: Modifier = Modifier,
    selectedColor: Color?,
    onColorSelected: (Color?) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MyNotesTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .background(color = MyNotesTheme.color.surface)
                    .padding(MyNotesTheme.padding.l),
                verticalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m)
            ) {
                Text(
                    text = stringResource(id = R.string.note_color_dialog_title),
                    style = MyNotesTheme.typography.headlineMedium.copy(color = MyNotesTheme.color.onSurface)
                )
                LazyVerticalGrid(
                    modifier = modifier,
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(horizontal = MyNotesTheme.padding.m),
                    horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m),
                    verticalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m)
                ) {
                    item {
                        ColorSelector(
                            color = Color.Transparent,
                            isSelected = Color.Transparent == selectedColor,
                            onColorClicked = {
                                onColorSelected(it)
                                onDismissRequest()
                            },
                            isEmpty = Color.Transparent != selectedColor
                        )
                    }

                    items(NoteColor.values()) { noteColor ->
                        ColorSelector(
                            color = noteColor.color,
                            isSelected = noteColor.color == selectedColor,
                            onColorClicked = {
                                onColorSelected(it)
                                onDismissRequest()
                            },
                            isEmpty = false
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewColorSelectionDialog() {
    MyNotesTheme {
        ColorSelectionDialog(
            selectedColor = NoteColor.DARK_BLUE.color,
            onColorSelected = { },
            onDismissRequest = { }
        )
    }
}