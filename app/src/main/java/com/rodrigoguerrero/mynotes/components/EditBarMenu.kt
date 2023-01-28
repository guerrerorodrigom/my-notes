package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun EditBarMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        modifier = modifier,
        expanded = isExpanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            text = { DropDownMenuItemText(textId = R.string.selected_note_menu_archive) },
            onClick = { }
        )
        DropdownMenuItem(
            text = { DropDownMenuItemText(textId = R.string.selected_note_menu_delete) },
            onClick = { }
        )
        DropdownMenuItem(
            text = { DropDownMenuItemText(textId = R.string.selected_note_menu_copy) },
            onClick = { }
        )
        DropdownMenuItem(
            text = { DropDownMenuItemText(textId = R.string.selected_note_menu_send) },
            onClick = { },
        )
    }
}

@Composable
private fun DropDownMenuItemText(@StringRes textId: Int) {
    Text(
        text = stringResource(id = textId),
        style = MyNotesTheme.typography.bodyLarge
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewEditBarMenu() {
    MyNotesTheme {
        EditBarMenu(isExpanded = true, onDismissRequest = { })
    }
}
