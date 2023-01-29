package com.rodrigoguerrero.mynotes.screens.edit

import android.content.Context
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.components.EditNoteBottomBar
import com.rodrigoguerrero.mynotes.components.EditNoteTopAppBar
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.ContentState
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.ErrorState
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.LoadingState
import com.rodrigoguerrero.mynotes.models.uimodels.EditNoteBottomSheet
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme
import com.rodrigoguerrero.mynotes.utils.DateUtils
import com.rodrigoguerrero.mynotes.viewmodels.EditNoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: EditNoteViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    onAddReminder: () -> Unit,
    onArchive: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var bottomSheetType: EditNoteBottomSheet by remember { mutableStateOf(EditNoteBottomSheet.Colors) }
    val systemUiController = rememberSystemUiController()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val bottomSheetState = rememberModalBottomSheetState(initialValue = Hidden)

    BackHandler(bottomSheetState.isVisible) {
        coroutineScope.launch { bottomSheetState.hide() }
    }

    BackHandler(!bottomSheetState.isVisible) {
        viewModel.saveNote()
        onBackClicked()
    }

    val currentState = state
    val backgroundColor = if (currentState is ContentState) {
        currentState.note.color
    } else {
        null
    }

    systemUiController.setStatusBarColor(color = backgroundColor ?: MyNotesTheme.color.surface)
    ModalBottomSheetLayout(
        modifier = modifier.navigationBarsPadding(),
        sheetContent = {
            EditNoteBottomSheetSelection(
                bottomSheetType = bottomSheetType,
                onColorSelected = {
                    viewModel.updateColor(it)
                    coroutineScope.launch { bottomSheetState.hide() }
                },
                selectedColor = if (currentState is ContentState) {
                    currentState.note.color
                } else {
                    null
                }
            )
        },
        sheetState = bottomSheetState
    ) {
        Scaffold(
            backgroundColor = backgroundColor ?: Color.Transparent,
            modifier = Modifier.imePadding(),
            topBar = {
                EditNoteTopAppBar(
                    onAddReminder = onAddReminder,
                    onPinClicked = viewModel::toggleIsPinned,
                    onBackClicked = {
                        viewModel.saveNote()
                        onBackClicked()
                    },
                    onArchive = onArchive,
                    backgroundColor = backgroundColor ?: Color.Transparent,
                    isPinned = (state as? ContentState)?.note?.isPinned ?: false
                )
            },
            bottomBar = {
                EditNoteBottomBar(
                    onShowOptions = {
                        bottomSheetType = EditNoteBottomSheet.Options
                        showBottomSheet(coroutineScope, bottomSheetState, keyboardController)
                    },
                    onShowColors = {
                        bottomSheetType = EditNoteBottomSheet.Colors
                        showBottomSheet(coroutineScope, bottomSheetState, keyboardController)
                    },
                    onShowMenu = {
                        bottomSheetType = EditNoteBottomSheet.More
                        showBottomSheet(coroutineScope, bottomSheetState, keyboardController)
                    },
                    time = if (currentState is ContentState) {
                        formatDate(currentState.note.editedDate, LocalContext.current)
                    } else {
                        ""
                    },
                    backgroundColor = backgroundColor ?: Color.Transparent
                )
            }
        ) { padding ->

            when (currentState) {
                LoadingState -> {}
                ErrorState -> {}
                is ContentState -> EditNoteContent(
                    currentState = currentState,
                    padding = padding,
                    viewModel = viewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
private fun showBottomSheet(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    keyboardController: SoftwareKeyboardController?
) {
    coroutineScope.launch {
        keyboardController?.hide()
        bottomSheetState.show()
    }
}

private fun formatDate(date: String, context: Context): String {
    return try {
        val instant = Instant.parse(date)
        with(instant.toLocalDateTime(TimeZone.currentSystemDefault())) {
            return when {
                DateUtils.isBeforeThisYear(date) -> context.getString(
                    R.string.edited_more_than_a_year_ago,
                    month.name.lowercase().replaceFirstChar { it.uppercase() }.substring(0, 3),
                    dayOfMonth.toString(),
                    year.toString()
                )
                DateUtils.isToday(date) -> context.getString(
                    R.string.edited_today,
                    time.hour.toString(),
                    DateUtils.formatTimeUnitWithTwoDigits(time.minute)
                )
                DateUtils.isYesterday(date) -> context.getString(
                    R.string.edited_yesterday,
                    time.hour.toString(),
                    DateUtils.formatTimeUnitWithTwoDigits(time.minute)
                )
                else -> context.getString(
                    R.string.edited_month_day,
                    month.name.lowercase().replaceFirstChar { it.uppercase() }.substring(0, 3),
                    dayOfMonth.toString()
                )
            }
        }
    } catch (exception: Exception) {
        ""
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
            onAddReminder = { }
        )
    }
}
