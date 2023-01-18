package com.rodrigoguerrero.mynotes.screens.edit

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.mynotes.components.EditNoteBottomBar
import com.rodrigoguerrero.mynotes.components.EditNoteTopAppBar
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.ContentState
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.ErrorState
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.LoadingState
import com.rodrigoguerrero.mynotes.models.uimodels.EditNoteBottomSheet
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme
import com.rodrigoguerrero.mynotes.viewmodels.EditNoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: EditNoteViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    onPinClicked: () -> Unit,
    onAddReminder: () -> Unit,
    onArchive: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var bottomSheetType: EditNoteBottomSheet by remember { mutableStateOf(EditNoteBottomSheet.Colors) }

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = Hidden)

    BackHandler(bottomSheetState.isVisible) {
        coroutineScope.launch { bottomSheetState.hide() }
    }

    BackHandler(!bottomSheetState.isVisible) {
        viewModel.saveNote()
        onBackClicked()
    }

    ModalBottomSheetLayout(
        modifier = modifier.navigationBarsPadding(),
        sheetContent = {
            EditNoteBottomSheetSelection(bottomSheetType)
        },
        sheetState = bottomSheetState
    ) {
        Scaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                EditNoteTopAppBar(
                    onAddReminder = onAddReminder,
                    onPinClicked = onPinClicked,
                    onBackClicked = {
                        viewModel.saveNote()
                        onBackClicked()
                    },
                    onArchive = onArchive
                )
            },
            bottomBar = {
                EditNoteBottomBar(
                    onShowOptions = {
                        bottomSheetType = EditNoteBottomSheet.Options
                        coroutineScope.launch { bottomSheetState.show() }
                    },
                    onShowColors = {
                        bottomSheetType = EditNoteBottomSheet.Colors
                        coroutineScope.launch { bottomSheetState.show() }
                    },
                    onShowMenu = {
                        bottomSheetType = EditNoteBottomSheet.More
                        coroutineScope.launch { bottomSheetState.show() }
                    }
                )
            }
        ) { padding ->

            when (val currentState = state) {
                LoadingState -> {}
                ErrorState -> {}
                is ContentState -> EditNoteContent(currentState, padding, viewModel)
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewEditNoteScreen() {
    MyNotesTheme {
        EditNoteScreen(
            onArchive = { },
            onBackClicked = { },
            onAddReminder = { },
            onPinClicked = { }
        )
    }
}
