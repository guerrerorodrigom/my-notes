package com.rodrigoguerrero.mynotes.data.local.datasources

import com.rodrigoguerrero.mynotes.data.local.entities.NoteEntity

interface NotesDataSource {
    suspend fun insertNote(note: NoteEntity)

    suspend fun getAllNotes(): List<NoteEntity>
}
