package com.rodrigoguerrero.mynotes.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: Int? = null,
    val title: String? = null,
    val content: String? = null,
    @ColumnInfo(name = "created_date") val createdDate: Long? = null,
    @ColumnInfo(name = "modified_date") val modifiedDate: Long? = null,
    val color: Int? = null,
    val isPinned: Boolean? = null
)
