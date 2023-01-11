package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

internal interface NotesDataSource {
    suspend fun insertNote(note: NoteEntity)

    fun getAllNotes(): Flow<List<NoteEntity>>
}
