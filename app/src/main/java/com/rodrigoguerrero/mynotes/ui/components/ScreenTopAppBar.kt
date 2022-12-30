package com.rodrigoguerrero.mynotes.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopAppBar(
    modifier: Modifier = Modifier,
    onMenuClicked: () -> Unit,
    @StringRes titleId: Int,
    actions: @Composable RowScope.() -> Unit
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onMenuClicked) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        title = {
            Text(
                text = stringResource(id = titleId),
                color = MyNotesTheme.color.onBackground,
                style = MyNotesTheme.typography.titleMedium
            )
        },
        actions = actions
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewScreenTopAppBar() {
    MyNotesTheme {
        ScreenTopAppBar(
            onMenuClicked = { },
            titleId = R.string.title_archive,
            actions = { }
        )
    }
}
