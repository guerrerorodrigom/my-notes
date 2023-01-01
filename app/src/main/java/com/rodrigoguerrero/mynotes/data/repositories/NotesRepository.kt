package com.rodrigoguerrero.mynotes.data.repositories

import com.rodrigoguerrero.mynotes.data.dtos.NoteDto

interface NotesRepository {

    suspend fun addNote(note: NoteDto): Int

    suspend fun getAllNotes(): List<NoteDto>
}
