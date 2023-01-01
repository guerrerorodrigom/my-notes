package com.rodrigoguerrero.mynotes.ui.models.uimodels

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun EmptyNotes(modifier: Modifier = Modifier) {
    EmptyComponent(
        icon = Icons.Outlined.Lightbulb,
        modifier = modifier,
        textId = R.string.empty_notes
    )
}

@Composable
fun EmptyReminders(modifier: Modifier = Modifier) {
    EmptyComponent(
        icon = Icons.Outlined.Notifications,
        modifier = modifier,
        textId = R.string.empty_reminders
    )
}

@Composable
fun EmptyArchive(modifier: Modifier = Modifier) {
    EmptyComponent(
        icon = Icons.Outlined.Archive,
        modifier = modifier,
        textId = R.string.empty_archive
    )
}

@Composable
fun EmptyBin(modifier: Modifier = Modifier) {
    EmptyComponent(
        icon = Icons.Outlined.Delete,
        modifier = modifier,
        textId = R.string.empty_deleted
    )
}

@Composable
private fun EmptyComponent(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes textId: Int
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MyNotesTheme.color.primary,
            modifier = Modifier.size(dimensionResource(id = R.dimen.empty_icon_size))
        )
        Text(
            text = stringResource(id = textId),
            modifier = Modifier.padding(top = MyNotesTheme.padding.m),
            color = MyNotesTheme.color.onBackground,
            style = MyNotesTheme.typography.bodyLarge
        )
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewEmptyComponent() {
    MyNotesTheme {
        EmptyComponent(icon = Icons.Outlined.Lightbulb, textId = R.string.empty_notes)
    }
}
