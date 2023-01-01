package com.rodrigoguerrero.mynotes.data.repositories

import com.rodrigoguerrero.mynotes.data.dtos.NoteDto
import com.rodrigoguerrero.mynotes.data.local.daos.NotesDao
import com.rodrigoguerrero.mynotes.data.mappers.mapToDto
import com.rodrigoguerrero.mynotes.data.mappers.mapToEntity
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesRepository {
    override suspend fun addNote(note: NoteDto): Int {
        val id = notesDao.insert(note.mapToEntity())

        return if (id >= 0) { id.toInt() } else { -1 }
    }

    override suspend fun getAllNotes(): List<NoteDto> {
        return notesDao.getAllNotes().map { it.mapToDto() }
    }
}
