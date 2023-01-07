package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.NotesDatabase
import com.rodrigoguerrero.data.local.entities.NoteEntity
import javax.inject.Inject

internal class NotesDataSourceImpl @Inject constructor(
    private val database: NotesDatabase
) : NotesDataSource {

    override suspend fun insertNote(note: NoteEntity) {
        database.notesDao().insert(note)
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return database.notesDao().getAllNotes()
    }
}
