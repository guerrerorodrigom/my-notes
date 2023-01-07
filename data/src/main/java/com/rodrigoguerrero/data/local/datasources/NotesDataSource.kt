package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.entities.NoteEntity

internal interface NotesDataSource {
    suspend fun insertNote(note: NoteEntity)

    suspend fun getAllNotes(): List<NoteEntity>
}
