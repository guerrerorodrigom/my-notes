package com.rodrigoguerrero.mynotes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onCheckboxClicked: () -> Unit,
    onDrawingClicked: () -> Unit,
    onMicrophoneClicked: () -> Unit,
    onImageClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        actions = {
            IconButton(onClick = onCheckboxClicked) {
                Icon(imageVector = Icons.Outlined.CheckBox, contentDescription = null)
            }
            IconButton(onClick = onDrawingClicked) {
                Icon(imageVector = Icons.Outlined.Brush, contentDescription = null)
            }
            IconButton(onClick = onMicrophoneClicked) {
                Icon(imageVector = Icons.Outlined.Mic, contentDescription = null)
            }
            IconButton(onClick = onImageClicked) {
                Icon(imageVector = Icons.Outlined.Image, contentDescription = null)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClicked,
                containerColor = MyNotesTheme.color.surfaceVariant,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewBottomBar() {
    MyNotesTheme {
        Surface(modifier = Modifier.padding(MyNotesTheme.padding.s)) {
            BottomBar(
                onCheckboxClicked = { },
                onDrawingClicked = { },
                onMicrophoneClicked = { },
                onImageClicked = { },
                onAddClicked = { }
            )
        }
    }
}
