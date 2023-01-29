package com.rodrigoguerrero.data.repositories

import com.rodrigoguerrero.data.dtos.NoteDto
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    val unpinnedNotes: Flow<List<NoteDto>>
    val pinnedNotes: Flow<List<NoteDto>>

    suspend fun addNote(note: NoteDto): Int

    suspend fun updateNote(note: NoteDto)

    suspend fun getNote(id: Int): NoteDto

    suspend fun deleteNote(id: Int)

    suspend fun updateNote(isPinned: Boolean, ids: List<Int>, modifiedTime: String)
}
