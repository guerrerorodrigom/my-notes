package com.rodrigoguerrero.mynotes.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.components.EditTopBar
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
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(color = MyNotesTheme.color.surface)
    Scaffold(
        topBar = {
            if (state.isMultipleSelectionEnabled) {
                systemUiController.setStatusBarColor(color = MyNotesTheme.color.surfaceVariant)
                EditTopBar(
                    onClose = viewModel::closeEditBar,
                    updatePinnedNotes = viewModel::updatePinnedNotes,
                    isPinned = state.isPinned
                )
            } else {
                systemUiController.setStatusBarColor(color = MyNotesTheme.color.surface)
                SearchField(
                    onMenuClicked = onMenuClicked,
                    modifier = Modifier.padding(
                        start = MyNotesTheme.padding.m,
                        end = MyNotesTheme.padding.m,
                        bottom = MyNotesTheme.padding.m
                    ),
                    onModeChanged = viewModel::toggleListMode,
                    listMode = state.listMode
                )
            }
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

        when {
            state.notes.isEmpty() -> EmptyNotes(modifier = modifier.padding(padding))
            else -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(
                        if (state.listMode == ListMode.LIST) {
                            1
                        } else {
                            2
                        }
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m),
                    horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m),
                    contentPadding = PaddingValues(all = MyNotesTheme.padding.m)
                ) {
                    items(state.notes.count()) { index ->
                        NoteCard(
                            note = state.notes[index],
                            onCardSelected = { id ->
                                if (state.isMultipleSelectionEnabled) {
                                    viewModel.toggleNoteSelected(id)
                                } else {
                                    onNoteSelected(id)
                                }
                            },
                            onLongPress = viewModel::toggleNoteSelected
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
private fun PreviewNotesListScreen() {
    MyNotesTheme {
        NotesListScreen(onAddNote = { }, onMenuClicked = { }, onNoteSelected = { })
    }
}
