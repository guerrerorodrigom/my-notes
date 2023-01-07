package com.rodrigoguerrero.mynotes.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rodrigoguerrero.mynotes.theme.colors.LocalMyNotesColors
import com.rodrigoguerrero.mynotes.theme.colors.darkColors
import com.rodrigoguerrero.mynotes.theme.colors.lightColors

object MyNotesTheme {
    val color: ColorScheme
        @Composable
        get() = LocalMyNotesColors.current

    val padding: MyNotesPadding
        @Composable
        get() = LocalMyNotesPadding.current

    val typography: Typography
        @Composable
        get() = LocalMyNotesTypography.current

    val shapes: Shapes
        @Composable
        get() = LocalMyNotesShapes.current
}

@Composable
fun MyNotesTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        lightColors()
    } else {
        darkColors()
    }

    val systemUiController = rememberSystemUiController()
    val padding = MyNotesPadding()
    val typography = typography()

    CompositionLocalProvider(
        LocalMyNotesColors provides colors,
        LocalMyNotesPadding provides padding,
        LocalMyNotesTypography provides typography,
        LocalMyNotesShapes provides Shapes
    ) {
        systemUiController.setStatusBarColor(
            color = MyNotesTheme.color.background,
            darkIcons = !isSystemInDarkTheme()
        )
        MaterialTheme(
            colorScheme = colors,
            shapes = Shapes,
            typography = typography
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                content()
            }
        }
    }
}
