package com.rodrigoguerrero.mynotes.ui.screens

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.components.BottomSheetColorSelector
import com.rodrigoguerrero.mynotes.ui.components.BottomSheetNoteMenu
import com.rodrigoguerrero.mynotes.ui.components.BottomSheetNoteMoreMenu
import com.rodrigoguerrero.mynotes.ui.components.EditNoteBottomBar
import com.rodrigoguerrero.mynotes.ui.components.EditNoteTopAppBar
import com.rodrigoguerrero.mynotes.ui.models.EditNoteBottomSheet
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onPinClicked: () -> Unit,
    onAddReminder: () -> Unit,
    onArchive: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var bottomSheetType: EditNoteBottomSheet by remember { mutableStateOf(EditNoteBottomSheet.Colors) }

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = Hidden)

    BackHandler(bottomSheetState.isVisible) {
        coroutineScope.launch { bottomSheetState.hide() }
    }

    ModalBottomSheetLayout(
        modifier = modifier.navigationBarsPadding(),
        sheetContent = {
            when (bottomSheetType) {
                EditNoteBottomSheet.Colors -> BottomSheetColorSelector(
                    onColorSelected = { },
                    selectedColor = Color.Transparent
                )
                EditNoteBottomSheet.More -> BottomSheetNoteMoreMenu(
                    onOptionSelected = { }
                )
                EditNoteBottomSheet.Options -> BottomSheetNoteMenu(
                    onOptionSelected = { }
                )
            }
        },
        sheetState = bottomSheetState
    ) {
        Scaffold(
            topBar = {
                EditNoteTopAppBar(
                    onAddReminder = onAddReminder,
                    onPinClicked = onPinClicked,
                    onBackClicked = onBackClicked,
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
            val focusRequester = remember { FocusRequester() }

            LaunchedEffect(key1 = Unit) {
                focusRequester.requestFocus()
            }

            Column(
                modifier = Modifier
                    .background(color = MyNotesTheme.color.background)
                    .fillMaxSize()
                    .padding(padding)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { title = it },
                    textStyle = MyNotesTheme.typography.headlineSmall,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { focusRequester.requestFocus() }),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.hint_title),
                            style = MyNotesTheme.typography.headlineSmall,
                            color = MyNotesTheme.color.onBackground
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = MyNotesTheme.color.surface,
                        textColor = MyNotesTheme.color.onSurface,
                        cursorColor = MyNotesTheme.color.onSurface
                    )
                )
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    textStyle = MyNotesTheme.typography.bodyLarge,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxSize(),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.hint_content),
                            style = MyNotesTheme.typography.bodyLarge,
                            color = MyNotesTheme.color.onBackground
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = MyNotesTheme.color.surface,
                        textColor = MyNotesTheme.color.onSurface,
                        cursorColor = MyNotesTheme.color.onSurface
                    )
                )
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
