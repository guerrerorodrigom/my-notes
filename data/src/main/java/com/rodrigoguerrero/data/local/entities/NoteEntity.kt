package com.rodrigoguerrero.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
internal data class NoteEntity(
    @PrimaryKey val id: Int? = null,
    val title: String? = null,
    val content: String? = null,
    @ColumnInfo(name = "created_date") val createdDate: String? = null,
    @ColumnInfo(name = "modified_date") val modifiedDate: String? = null,
    val color: Int? = null,
    val isPinned: Boolean? = null
)
