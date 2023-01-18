package com.rodrigoguerrero.data.repositories

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.data.local.daos.NotesDao
import com.rodrigoguerrero.data.mappers.mapToDto
import com.rodrigoguerrero.data.mappers.mapToEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesRepository {
    override suspend fun addNote(note: NoteDto): Int {
        val id = notesDao.insert(note.mapToEntity())

        return if (id >= 0) {
            id.toInt()
        } else {
            -1
        }
    }

    override suspend fun updateNote(note: NoteDto) {
        notesDao.update(note.mapToEntity())
    }

    override suspend fun getAllNotes(): Flow<List<NoteDto>> {
        return notesDao.getAllNotes().map { notes ->
            notes.map { note -> note.mapToDto() }
        }
    }

    override suspend fun getNote(id: Int): NoteDto {
        return notesDao.getNote(id).mapToDto()
    }

    override suspend fun deleteNote(id: Int) {
        notesDao.delete(id)
    }
}
