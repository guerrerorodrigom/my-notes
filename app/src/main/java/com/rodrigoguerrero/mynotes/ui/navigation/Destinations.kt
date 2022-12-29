package com.rodrigoguerrero.mynotes.ui.navigation

enum class Destinations(val route: String) {
    NOTES_LIST("notes-list"),
    REMINDERS("reminders"),
    NEW_LABEL("new-label"),
    ARCHIVE("archive"),
    DELETED("deleted"),
    SETTINGS("settings"),
    HELP("help"),
    NEW_NOTE("new-note");

    companion object {
        infix fun from(route: String): Destinations? = values().firstOrNull { it.route == route }
    }
}
