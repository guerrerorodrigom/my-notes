package com.rodrigoguerrero.mynotes.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.mynotes.ui.components.DrawerMenu
import com.rodrigoguerrero.mynotes.ui.components.ScreenTopAppBar
import com.rodrigoguerrero.mynotes.ui.navigation.Destinations
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme
import kotlinx.coroutines.launch

@Composable
fun TopAppBarDrawerScreen(
    modifier: Modifier = Modifier,
    navigateTo: (Destinations) -> Unit,
    currentDestination: Destinations?,
    @StringRes titleId: Int,
    actions: @Composable RowScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            ScreenTopAppBar(
                onMenuClicked = { coroutineScope.launch { scaffoldState.drawerState.open() } },
                titleId = titleId,
                actions = actions
            )
        },
        drawerContent = {
            DrawerMenu(onItemSelected = navigateTo, currentDestination = currentDestination)
        },
        backgroundColor = MyNotesTheme.color.background
    ) { padding ->
        content(padding)
    }
}
