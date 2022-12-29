package com.rodrigoguerrero.mynotes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteTopAppBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onPinClicked: () -> Unit,
    onAddReminder: () -> Unit,
    onArchive: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onPinClicked) {
                Icon(imageVector = Icons.Outlined.PushPin, contentDescription = null)
            }
            IconButton(onClick = onAddReminder) {
                Icon(imageVector = Icons.Outlined.AddAlert, contentDescription = null)
            }
            IconButton(onClick = onArchive) {
                Icon(imageVector = Icons.Outlined.Archive, contentDescription = null)
            }
        }
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewEditNoteTopeAppBar() {
    MyNotesTheme {
        EditNoteTopAppBar(
            onArchive = { },
            onBackClicked = { },
            onAddReminder = { },
            onPinClicked = { }
        )
    }
}
