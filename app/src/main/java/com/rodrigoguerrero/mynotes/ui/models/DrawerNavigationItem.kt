package com.rodrigoguerrero.mynotes.ui.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.models.DrawerNavigationItem.Archive
import com.rodrigoguerrero.mynotes.ui.models.DrawerNavigationItem.Deleted
import com.rodrigoguerrero.mynotes.ui.models.DrawerNavigationItem.Help
import com.rodrigoguerrero.mynotes.ui.models.DrawerNavigationItem.NewLabel
import com.rodrigoguerrero.mynotes.ui.models.DrawerNavigationItem.Notes
import com.rodrigoguerrero.mynotes.ui.models.DrawerNavigationItem.Reminders
import com.rodrigoguerrero.mynotes.ui.models.DrawerNavigationItem.Settings
import com.rodrigoguerrero.mynotes.ui.navigation.MainScreenDestinations

sealed class DrawerNavigationItem(
    val icon: ImageVector,
    @StringRes val title: Int,
    val destination: MainScreenDestinations
) {
    object Notes : DrawerNavigationItem(
        icon = Icons.Outlined.Lightbulb,
        title = R.string.menu_notes,
        destination = MainScreenDestinations.NOTES_LIST
    )

    object Reminders : DrawerNavigationItem(
        icon = Icons.Outlined.Notifications,
        title = R.string.menu_reminders,
        destination = MainScreenDestinations.REMINDERS
    )

    object NewLabel : DrawerNavigationItem(
        icon = Icons.Default.Add,
        title = R.string.menu_label,
        destination = MainScreenDestinations.NEW_LABEL
    )

    object Archive : DrawerNavigationItem(
        icon = Icons.Outlined.Archive,
        title = R.string.menu_archive,
        destination = MainScreenDestinations.ARCHIVE
    )

    object Deleted : DrawerNavigationItem(
        icon = Icons.Outlined.Delete,
        title = R.string.menu_deleted,
        destination = MainScreenDestinations.DELETED
    )

    object Settings : DrawerNavigationItem(
        icon = Icons.Outlined.Settings,
        title = R.string.menu_settings,
        destination = MainScreenDestinations.SETTINGS
    )

    object Help : DrawerNavigationItem(
        icon = Icons.Outlined.Help,
        title = R.string.menu_help,
        destination = MainScreenDestinations.HELP
    )
}

val drawerNavigationItems = listOf(Notes, Reminders, NewLabel, Archive, Deleted, Settings, Help)
