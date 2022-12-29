package com.rodrigoguerrero.mynotes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun EditNoteBottomBar(
    modifier: Modifier = Modifier,
    onShowOptions: () -> Unit,
    onShowColors: () -> Unit,
    onShowMenu: () -> Unit
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onShowOptions) {
            Icon(imageVector = Icons.Outlined.AddBox, contentDescription = null)
        }

        IconButton(onClick = onShowColors) {
            Icon(imageVector = Icons.Outlined.Palette, contentDescription = null)
        }

        Text(text = "Edited 10:35", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        IconButton(onClick = onShowMenu) {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewEditNoteBottomBar() {
    MyNotesTheme {
        EditNoteBottomBar(
            onShowOptions = { },
            onShowColors = { },
            onShowMenu = { }
        )
    }
}
