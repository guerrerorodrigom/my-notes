package com.rodrigoguerrero.data.repositories

import com.rodrigoguerrero.data.dtos.NoteDto
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun addNote(note: NoteDto): Int

    suspend fun updateNote(note: NoteDto)

    suspend fun getAllNotes(): Flow<List<NoteDto>>

    suspend fun getPinnedNotes(): Flow<List<NoteDto>>

    suspend fun getNote(id: Int): NoteDto

    suspend fun deleteNote(id: Int)
}
