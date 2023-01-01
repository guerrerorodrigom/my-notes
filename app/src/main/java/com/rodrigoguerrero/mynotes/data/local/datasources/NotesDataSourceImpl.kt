package com.rodrigoguerrero.mynotes.data.local.datasources

import com.rodrigoguerrero.mynotes.data.local.NotesDatabase
import com.rodrigoguerrero.mynotes.data.local.entities.NoteEntity
import javax.inject.Inject

class NotesDataSourceImpl @Inject constructor(
    private val database: NotesDatabase
) : NotesDataSource {

    override suspend fun insertNote(note: NoteEntity) {
        database.notesDao().insert(note)
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return database.notesDao().getAllNotes()
    }
}
