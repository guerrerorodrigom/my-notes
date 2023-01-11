package com.rodrigoguerrero.mynotes.navigation

enum class Destinations(val route: String) {
    MAIN_SCREEN("main-screen"),
    EDIT_NOTE("edit-note"),
    NEW_NOTE("new-note");
}

enum class MainScreenDestinations(val route: String) {
    NOTES_LIST("notes-list"),
    REMINDERS("reminders"),
    NEW_LABEL("new-label"),
    ARCHIVE("archive"),
    DELETED("deleted"),
    SETTINGS("settings"),
    HELP("help");

    companion object {
        infix fun from(route: String): MainScreenDestinations? = values().firstOrNull { it.route == route }
    }
}
