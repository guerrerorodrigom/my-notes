package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.NotesDatabase
import com.rodrigoguerrero.data.local.entities.NoteEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class NotesDataSourceImpl @Inject constructor(
    private val database: NotesDatabase
) : NotesDataSource {

    override val unpinnedNotes: Flow<List<NoteEntity>> = database.notesDao().getUnpinnedNotes()
    override val pinnedNotes: Flow<List<NoteEntity>> = database.notesDao().getPinnedNotes()

    override suspend fun insertNote(note: NoteEntity): Long {
        return database.notesDao().insert(note)
    }

    override suspend fun update(note: NoteEntity) {
        database.notesDao().update(note)
    }

    override suspend fun getNote(id: Int): NoteEntity {
        return database.notesDao().getNote(id)
    }

    override suspend fun delete(id: Int) {
        database.notesDao().delete(id)
    }

    override suspend fun update(isPinned: Boolean, ids: List<Int>, modifiedTime: String) {
        database.notesDao().update(isPinned, ids, modifiedTime)
    }
}
