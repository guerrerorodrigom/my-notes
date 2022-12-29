package com.rodrigoguerrero.mynotes.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.ui.components.DrawerMenu
import com.rodrigoguerrero.mynotes.ui.components.MainBottomBar
import com.rodrigoguerrero.mynotes.ui.components.SearchField
import com.rodrigoguerrero.mynotes.ui.navigation.Destinations
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme
import kotlinx.coroutines.launch

@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
    currentDestination: Destinations?,
    onAddNote: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchField(
                onMenuClicked = {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                },
                modifier = Modifier.padding(MyNotesTheme.padding.m)
            )
        },
        bottomBar = {
            MainBottomBar(
                onCheckboxClicked = { },
                onDrawingClicked = { },
                onMicrophoneClicked = { },
                onImageClicked = { },
                onAddClicked = onAddNote
            )
        },
        drawerContent = {
            DrawerMenu(onItemSelected = {}, currentDestination = currentDestination)
        },
        backgroundColor = MyNotesTheme.color.background
    ) { padding ->
        Column(modifier = modifier.padding(padding)) {

        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewNotesListScreen() {
    MyNotesTheme {
        NotesListScreen(currentDestination = Destinations.NOTES_LIST, onAddNote = { })
    }
}
