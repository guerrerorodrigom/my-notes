package com.rodrigoguerrero.mynotes.models.uimodels

import androidx.compose.ui.graphics.Color

data class Note(
    val id: Int,
    val title: String?,
    val content: String?,
    val color: Color?,
    val isPinned: Boolean,
    val isSelected: Boolean = false,
    val editedDate: String,
    val createdDate: String,
)
