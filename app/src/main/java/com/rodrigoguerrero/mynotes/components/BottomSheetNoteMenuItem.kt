package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.models.uimodels.NoteOption
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun BottomSheetNoteMenuItem(
    modifier: Modifier = Modifier,
    noteOption: NoteOption,
    onClicked: (NoteOption) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClicked(noteOption) }
            .fillMaxWidth()
            .padding(all = MyNotesTheme.padding.m),
        horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.l),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = noteOption.icon,
            contentDescription = null,
            tint = MyNotesTheme.color.onSurface
        )
        Text(text = stringResource(id = noteOption.text), color = MyNotesTheme.color.onSurface)
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewBottomSheetNoteMenuItem() {
    MyNotesTheme {
        BottomSheetNoteMenuItem(noteOption = NoteOption.TakePhoto, onClicked = { })
    }
}
