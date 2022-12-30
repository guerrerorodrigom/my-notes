package com.rodrigoguerrero.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.mynotes.ui.navigation.Destinations
import com.rodrigoguerrero.mynotes.ui.navigation.popUpToTop
import com.rodrigoguerrero.mynotes.ui.screens.ArchiveScreen
import com.rodrigoguerrero.mynotes.ui.screens.DeletedScreen
import com.rodrigoguerrero.mynotes.ui.screens.EditNoteScreen
import com.rodrigoguerrero.mynotes.ui.screens.NotesListScreen
import com.rodrigoguerrero.mynotes.ui.screens.RemindersScreen
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyNotesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destinations.NOTES_LIST.route
                ) {
                    composable(route = Destinations.NOTES_LIST.route) {
                        NotesListScreen(
                            currentDestination = Destinations.from(it.destination.route.orEmpty()),
                            onAddNote = { navController.navigate(Destinations.NEW_NOTE.route) },
                            navigateTo = { destination ->
                                navController.navigate(destination.route) { popUpToTop(navController) }
                            }
                        )
                    }
                    composable(route = Destinations.NEW_NOTE.route) {
                        EditNoteScreen(
                            onBackClicked = { navController.popBackStack() },
                            onPinClicked = { },
                            onAddReminder = { },
                            onArchive = { }
                        )
                    }
                    composable(route = Destinations.REMINDERS.route) {
                        RemindersScreen(
                            currentDestination = Destinations.from(it.destination.route.orEmpty()),
                            navigateTo = { destination ->
                                navController.navigate(destination.route) { popUpToTop(navController) }
                            },
                            onSearchClicked = { }
                        )
                    }
                    composable(route = Destinations.ARCHIVE.route) {
                        ArchiveScreen(
                            currentDestination = Destinations.from(it.destination.route.orEmpty()),
                            navigateTo = { destination ->
                                navController.navigate(destination.route) { popUpToTop(navController) }
                            },
                            onSearchClicked = { }
                        )
                    }
                    composable(route = Destinations.DELETED.route) {
                        DeletedScreen(
                            currentDestination = Destinations.from(it.destination.route.orEmpty()),
                            navigateTo = { destination ->
                                navController.navigate(destination.route) { popUpToTop(navController) }
                            }
                        )
                    }
                }
            }
        }
    }
}
