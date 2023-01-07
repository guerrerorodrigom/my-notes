package com.rodrigoguerrero.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigoguerrero.data.local.daos.NotesDao
import com.rodrigoguerrero.data.local.entities.NoteEntity

private const val DATABASE_VERSION = 1

@Database(entities = [NoteEntity::class], version = DATABASE_VERSION)
abstract class NotesDatabase : RoomDatabase() {
    internal abstract fun notesDao(): NotesDao
}
