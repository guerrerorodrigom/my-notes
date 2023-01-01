package com.rodrigoguerrero.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.mynotes.ui.navigation.Destinations
import com.rodrigoguerrero.mynotes.ui.screens.EditNoteScreen
import com.rodrigoguerrero.mynotes.ui.screens.MainScreen
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyNotesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destinations.MAIN_SCREEN.route
                ) {
                    composable(route = Destinations.MAIN_SCREEN.route) {
                        MainScreen(
                            onAddNote = { navController.navigate(Destinations.NEW_NOTE.route) }
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
                }
            }
        }
    }
}
