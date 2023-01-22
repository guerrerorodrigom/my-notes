package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

internal interface NotesDataSource {
    suspend fun insertNote(note: NoteEntity): Long

    suspend fun update(note: NoteEntity)

    suspend fun getAllNotes(): Flow<List<NoteEntity>>

    suspend fun getPinnedNotes(): Flow<List<NoteEntity>>

    suspend fun getNote(id: Int): NoteEntity

    suspend fun delete(id: Int)
}
