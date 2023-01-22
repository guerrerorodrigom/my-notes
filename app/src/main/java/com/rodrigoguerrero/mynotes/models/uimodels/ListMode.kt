package com.rodrigoguerrero.mynotes.models.uimodels

import com.rodrigoguerrero.mynotes.navigation.MainScreenDestinations

enum class ListMode {
    LIST, GRID;

    companion object {
        infix fun from(modeOrdinal: Int?): ListMode? = values().firstOrNull { it.ordinal == modeOrdinal }
    }
}
