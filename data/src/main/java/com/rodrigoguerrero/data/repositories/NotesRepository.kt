package com.rodrigoguerrero.data.repositories

import com.rodrigoguerrero.data.dtos.NoteDto

interface NotesRepository {

    suspend fun addNote(note: NoteDto): Int

    suspend fun getAllNotes(): List<NoteDto>
}
