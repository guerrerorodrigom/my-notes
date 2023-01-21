package com.rodrigoguerrero.mynotes.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme
import com.rodrigoguerrero.mynotes.viewmodels.EditNoteViewModel

@Composable
fun EditNoteContent(
    modifier: Modifier = Modifier,
    currentState: EditNoteState.ContentState,
    padding: PaddingValues,
    viewModel: EditNoteViewModel
) {
    val focusRequester = remember { FocusRequester() }
    var contentTextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = currentState.content,
                selection = if (currentState.content.isNotEmpty()) {
                    TextRange(currentState.content.length)
                } else {
                    TextRange.Zero
                }
            )
        )
    }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentState.title,
            onValueChange = viewModel::updateTitle,
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
                backgroundColor = currentState.color ?: Color.Transparent,
                textColor = MyNotesTheme.color.onSurface,
                cursorColor = MyNotesTheme.color.onSurface
            )
        )
        OutlinedTextField(
            value = contentTextFieldValue,
            onValueChange = {
                contentTextFieldValue = it
                viewModel.updateContent(it.text)
            },
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
                backgroundColor = currentState.color ?: Color.Transparent,
                textColor = MyNotesTheme.color.onSurface,
                cursorColor = MyNotesTheme.color.onSurface
            )
        )
    }
}
