package com.rodrigoguerrero.mynotes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.models.drawerNavigationItems
import com.rodrigoguerrero.mynotes.ui.navigation.Destinations
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun DrawerMenu(
    modifier: Modifier = Modifier,
    onItemSelected: (Destinations) -> Unit,
    currentDestination: Destinations?
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(color = MyNotesTheme.color.surface)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(all = MyNotesTheme.padding.ml),
            style = MyNotesTheme.typography.headlineMedium,
            color = MyNotesTheme.color.onSurface
        )
        drawerNavigationItems.forEach { item ->
            DrawerItem(
                item = item,
                isSelected = item.destination == currentDestination,
                onClicked = onItemSelected
            )
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewDrawerMenu() {
    MyNotesTheme {
        DrawerMenu(onItemSelected = { }, currentDestination = Destinations.NOTES_LIST)
    }
}
