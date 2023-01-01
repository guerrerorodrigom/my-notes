package com.rodrigoguerrero.mynotes.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.mynotes.ui.components.ScreenTopAppBar
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun TopAppBarDrawerScreen(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    onMenuClicked: () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            ScreenTopAppBar(
                onMenuClicked = onMenuClicked,
                titleId = titleId,
                actions = actions
            )
        },
        backgroundColor = MyNotesTheme.color.background
    ) { padding ->
        content(padding)
    }
}
