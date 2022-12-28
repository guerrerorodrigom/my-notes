package com.rodrigoguerrero.mynotes.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.ui.components.BottomBar
import com.rodrigoguerrero.mynotes.ui.components.SearchField
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            SearchField(
                onMenuClicked = {},
                modifier = Modifier.padding(MyNotesTheme.padding.m)
            )
        },
        bottomBar = {
            BottomBar(
                onCheckboxClicked = { },
                onDrawingClicked = { },
                onMicrophoneClicked = { },
                onImageClicked = { },
                onAddClicked = { }
            )
        }
    ) { padding ->
        Column(modifier = modifier.padding(padding)) {

        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewNotesListScreen() {
    MyNotesTheme {
        NotesListScreen()
    }
}
