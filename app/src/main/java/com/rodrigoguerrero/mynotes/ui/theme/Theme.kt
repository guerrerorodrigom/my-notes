package com.rodrigoguerrero.mynotes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.rodrigoguerrero.mynotes.ui.theme.colors.LocalMyNotesColors
import com.rodrigoguerrero.mynotes.ui.theme.colors.darkColors
import com.rodrigoguerrero.mynotes.ui.theme.colors.lightColors

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

    val padding = MyNotesPadding()
    val typography = typography()

    CompositionLocalProvider(
        LocalMyNotesColors provides colors,
        LocalMyNotesPadding provides padding,
        LocalMyNotesTypography provides typography,
        LocalMyNotesShapes provides Shapes
    ) {
        MaterialTheme(
            colorScheme = colors,
            shapes = Shapes,
            typography = typography,
            content = content
        )
    }
}
