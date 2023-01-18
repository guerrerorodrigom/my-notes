package com.rodrigoguerrero.mynotes.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.mynotes.components.MainBottomBar
import com.rodrigoguerrero.mynotes.components.NoteCard
import com.rodrigoguerrero.mynotes.components.SearchField
import com.rodrigoguerrero.mynotes.models.uimodels.EmptyNotes
import com.rodrigoguerrero.mynotes.models.uimodels.ListMode
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme
import com.rodrigoguerrero.mynotes.viewmodels.NotesListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesListViewModel = hiltViewModel(),
    onAddNote: () -> Unit,
    onMenuClicked: () -> Unit,
    onNoteSelected: (Int) -> Unit
) {
    var columns by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            SearchField(
                onMenuClicked = onMenuClicked,
                modifier = Modifier.padding(horizontal = MyNotesTheme.padding.m),
                onModeChanged = { mode ->
                    columns = if (mode == ListMode.LIST) {
                        1
                    } else {
                        2
                    }
                }
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

        val state by viewModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.loadNotes()
        }

        when {
            state.notes.isEmpty() -> EmptyNotes(modifier = modifier.padding(padding))
            else -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(columns),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m),
                    horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m),
                    contentPadding = PaddingValues(all = MyNotesTheme.padding.m)
                ) {
                    items(state.notes.count()) { index ->
                        NoteCard(note = state.notes[index], onSelected = onNoteSelected)
                    }
                }
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewNotesListScreen() {
    MyNotesTheme {
        NotesListScreen(onAddNote = { }, onMenuClicked = { }, onNoteSelected = { })
    }
}
