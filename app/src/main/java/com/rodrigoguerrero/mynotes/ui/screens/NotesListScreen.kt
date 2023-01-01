package com.rodrigoguerrero.mynotes.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.ui.components.MainBottomBar
import com.rodrigoguerrero.mynotes.ui.components.SearchField
import com.rodrigoguerrero.mynotes.ui.models.EmptyNotes
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
    onAddNote: () -> Unit,
    onMenuClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            SearchField(
                onMenuClicked = onMenuClicked,
                modifier = Modifier.padding(MyNotesTheme.padding.m)
            )
        },
        bottomBar = {
            MainBottomBar(
                onCheckboxClicked = { },
                onDrawingClicked = { },
                onMicrophoneClicked = { },
                onImageClicked = { },
                onAddClicked = onAddNote
            )
        },
        backgroundColor = MyNotesTheme.color.background
    ) { padding ->
        EmptyNotes(modifier = modifier.padding(padding))
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewNotesListScreen() {
    MyNotesTheme {
        NotesListScreen(onAddNote = { }, onMenuClicked = { })
    }
}
