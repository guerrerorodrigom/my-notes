package com.rodrigoguerrero.mynotes.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun EditNoteBottomBar(
    modifier: Modifier = Modifier,
    time: String,
    backgroundColor: Color = Color.Transparent,
    onShowOptions: () -> Unit,
    onShowColors: () -> Unit,
    onShowMenu: () -> Unit
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = backgroundColor
    ) {
        IconButton(onClick = onShowOptions) {
            Icon(imageVector = Icons.Outlined.AddBox, contentDescription = null)
        }

        IconButton(onClick = onShowColors) {
            Icon(imageVector = Icons.Outlined.Palette, contentDescription = null)
        }

        Text(
            text = stringResource(id = R.string.edited_time, time),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
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
            onShowMenu = { },
            time = "10:35"
        )
    }
}
