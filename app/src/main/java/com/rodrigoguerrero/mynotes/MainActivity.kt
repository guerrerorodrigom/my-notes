package com.rodrigoguerrero.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "notes") {
                    composable(route = "notes") {

                    }
                }
            }
        }
    }
}
