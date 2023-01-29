package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun EditTopBar(
    modifier: Modifier = Modifier,
    isPinned: Boolean,
    selectedColor: Color?,
    totalSelectedNotes: Int,
    onClose: () -> Unit,
    updatePinnedNotes: () -> Unit,
    onColorSelected: (Color?) -> Unit
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var showColorSelectionDialog by remember { mutableStateOf(false) }

    if (showColorSelectionDialog) {
        ColorSelectionDialog(
            selectedColor = selectedColor,
            onColorSelected = onColorSelected,
            onDismissRequest = { showColorSelectionDialog = false }
        )
    }

    TopAppBar(
        backgroundColor = MyNotesTheme.color.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = MyNotesTheme.padding.m),
        elevation = dimensionResource(id = R.dimen.top_bar_elevation),
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.s)
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
                Text(
                    text = totalSelectedNotes.toString(),
                    style = MyNotesTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = MyNotesTheme.padding.xxs)
                )
            }
        },
        actions = {
            IconButton(onClick = updatePinnedNotes) {
                Icon(
                    imageVector = if (isPinned) {
                        Icons.Filled.PushPin
                    } else {
                        Icons.Outlined.PushPin
                    },
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.AddAlert,
                    contentDescription = null
                )
            }
            IconButton(onClick = {
                showColorSelectionDialog = true
            }) {
                Icon(
                    imageVector = Icons.Outlined.Palette,
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Label,
                    contentDescription = null
                )
            }
            Box {
                IconButton(onClick = { isMenuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = null
                    )
                }
                EditBarMenu(
                    isExpanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false }
                )
            }
        },
        title = { }
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewEditTopBar() {
    MyNotesTheme {
        EditTopBar(
            isPinned = true,
            selectedColor = Color.Blue,
            totalSelectedNotes = 3,
            onClose = { },
            updatePinnedNotes = { },
            onColorSelected = { }
        )
    }
}
