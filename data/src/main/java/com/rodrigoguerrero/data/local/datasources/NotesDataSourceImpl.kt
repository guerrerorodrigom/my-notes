package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.NotesDatabase
import com.rodrigoguerrero.data.local.entities.NoteEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class NotesDataSourceImpl @Inject constructor(
    private val database: NotesDatabase
) : NotesDataSource {

    override suspend fun insertNote(note: NoteEntity): Long {
        return database.notesDao().insert(note)
    }

    override suspend fun update(note: NoteEntity) {
        database.notesDao().update(note)
    }

    override suspend fun getAllNotes(): Flow<List<NoteEntity>> {
        return database.notesDao().getAllNotes()
    }

    override suspend fun getPinnedNotes(): Flow<List<NoteEntity>> {
        return database.notesDao().getPinnedNotes()
    }

    override suspend fun getNote(id: Int): NoteEntity {
        return database.notesDao().getNote(id)
    }

    override suspend fun delete(id: Int) {
        database.notesDao().delete(id)
    }
}
