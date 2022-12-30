package com.rodrigoguerrero.mynotes.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.models.EmptyBin
import com.rodrigoguerrero.mynotes.ui.navigation.Destinations
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun DeletedScreen(
    modifier: Modifier = Modifier,
    currentDestination: Destinations?,
    navigateTo: (Destinations) -> Unit,
) {
    TopAppBarDrawerScreen(
        navigateTo = navigateTo,
        currentDestination = currentDestination,
        titleId = R.string.title_deleted,
        actions = { }
    ) { padding ->
        EmptyBin(modifier = modifier.padding(padding))
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewDeletedScreen() {
    MyNotesTheme {
        DeletedScreen(currentDestination = null, navigateTo = { })
    }
}
