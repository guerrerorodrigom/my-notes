package com.rodrigoguerrero.mynotes.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.models.EmptyReminders
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun RemindersScreen(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    onMenuClicked: () -> Unit
) {
    var listView by remember { mutableStateOf(false) }

    TopAppBarDrawerScreen(
        titleId = R.string.title_reminders,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
            IconButton(onClick = { listView = !listView }) {
                Icon(
                    imageVector = if (listView) Icons.Default.GridView else Icons.Outlined.ViewAgenda,
                    contentDescription = null
                )
            }
        },
        onMenuClicked = onMenuClicked
    ) { padding ->
        EmptyReminders(modifier = modifier.padding(padding))
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewRemindersScreen() {
    MyNotesTheme {
        RemindersScreen(onSearchClicked = { }, onMenuClicked = { })
    }
}
