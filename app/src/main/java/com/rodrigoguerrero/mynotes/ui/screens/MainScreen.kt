package com.rodrigoguerrero.mynotes.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.mynotes.ui.components.DrawerMenu
import com.rodrigoguerrero.mynotes.ui.navigation.MainScreenDestinations.ARCHIVE
import com.rodrigoguerrero.mynotes.ui.navigation.MainScreenDestinations.DELETED
import com.rodrigoguerrero.mynotes.ui.navigation.MainScreenDestinations.NOTES_LIST
import com.rodrigoguerrero.mynotes.ui.navigation.MainScreenDestinations.REMINDERS
import com.rodrigoguerrero.mynotes.ui.navigation.popUpToTop
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onAddNote: () -> Unit
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerMenu(
                onItemSelected = { destination ->
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                    navController.navigate(destination.route) { popUpToTop(navController) }
                }
            )
        },
        backgroundColor = MyNotesTheme.color.background
    ) { padding ->

        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = NOTES_LIST.route
        ) {
            composable(route = NOTES_LIST.route) {
                NotesListScreen(
                    onAddNote = onAddNote,
                    onMenuClicked = openDrawer(coroutineScope, scaffoldState)
                )
            }
            composable(route = REMINDERS.route) {
                RemindersScreen(
                    onSearchClicked = { },
                    onMenuClicked = openDrawer(coroutineScope, scaffoldState)
                )
            }
            composable(route = ARCHIVE.route) {
                ArchiveScreen(
                    onSearchClicked = { },
                    onMenuClicked = openDrawer(coroutineScope, scaffoldState)
                )
            }
            composable(route = DELETED.route) {
                DeletedScreen(onMenuClicked = openDrawer(coroutineScope, scaffoldState))
            }
        }
    }
}

@Composable
private fun openDrawer(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
): () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.open() } }
