package com.rodrigoguerrero.data.repositories

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.data.local.daos.NotesDao
import com.rodrigoguerrero.data.local.datasources.NotesDataSource
import com.rodrigoguerrero.data.mappers.mapToDto
import com.rodrigoguerrero.data.mappers.mapToEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class NotesRepositoryImpl @Inject constructor(
    private val notesDataSource: NotesDataSource
) : NotesRepository {
    override suspend fun addNote(note: NoteDto): Int {
        val id = notesDataSource.insertNote(note.mapToEntity())

        return if (id >= 0) {
            id.toInt()
        } else {
            -1
        }
    }

    override suspend fun updateNote(note: NoteDto) {
        notesDataSource.update(note.mapToEntity())
    }

    override suspend fun getAllNotes(): Flow<List<NoteDto>> {
        return notesDataSource.getAllNotes().map { notes ->
            notes.map { note -> note.mapToDto() }
        }
    }

    override suspend fun getPinnedNotes(): Flow<List<NoteDto>> {
        return notesDataSource.getPinnedNotes().map { notes ->
            notes.map { note -> note.mapToDto() }
        }
    }

    override suspend fun getNote(id: Int): NoteDto {
        return notesDataSource.getNote(id).mapToDto()
    }

    override suspend fun deleteNote(id: Int) {
        notesDataSource.delete(id)
    }
}
