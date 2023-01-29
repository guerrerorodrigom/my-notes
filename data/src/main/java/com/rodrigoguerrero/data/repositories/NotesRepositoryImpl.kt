package com.rodrigoguerrero.data.repositories

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.data.local.datasources.NotesDataSource
import com.rodrigoguerrero.data.mappers.mapToDto
import com.rodrigoguerrero.data.mappers.mapToEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class NotesRepositoryImpl @Inject constructor(
    private val notesDataSource: NotesDataSource
) : NotesRepository {

    override val unpinnedNotes: Flow<List<NoteDto>> = notesDataSource.unpinnedNotes.map { notes ->
        notes.map { note -> note.mapToDto() }
    }

    override val pinnedNotes: Flow<List<NoteDto>> = notesDataSource.pinnedNotes.map { notes ->
        notes.map { note -> note.mapToDto() }
    }

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

    override suspend fun getNote(id: Int): NoteDto {
        return notesDataSource.getNote(id).mapToDto()
    }

    override suspend fun deleteNote(id: Int) {
        notesDataSource.delete(id)
    }

    override suspend fun updateNote(isPinned: Boolean, ids: List<Int>, modifiedTime: String) {
        notesDataSource.update(isPinned, ids, modifiedTime)
    }

    override suspend fun updateNote(color: Long?, ids: List<Int>, modifiedTime: String) {
        notesDataSource.update(color, ids, modifiedTime)
    }
}
