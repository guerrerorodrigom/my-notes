package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun EditTopBar(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    TopAppBar(
        backgroundColor = MyNotesTheme.color.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = MyNotesTheme.padding.m),
        elevation = dimensionResource(id = R.dimen.top_bar_elevation),
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.PushPin,
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.AddAlert,
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
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
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = null
                )
            }
        },
        title = { }
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewEditTopBar() {
    MyNotesTheme {
        EditTopBar(onClose = { })
    }
}
