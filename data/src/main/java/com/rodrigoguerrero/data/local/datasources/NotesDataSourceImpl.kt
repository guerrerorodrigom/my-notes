package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.NotesDatabase
import com.rodrigoguerrero.data.local.entities.NoteEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class NotesDataSourceImpl @Inject constructor(
    private val database: NotesDatabase
) : NotesDataSource {

    override suspend fun insertNote(note: NoteEntity) {
        database.notesDao().insert(note)
    }

    override fun getAllNotes(): Flow<List<NoteEntity>> = database.notesDao().getAllNotes()
}
