package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDomain
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.domain.models.RetrieveNotesResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RetrieveAllNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(): Flow<List<NoteModel>> {
        return repository.getAllNotes().map { notes -> notes.map { note -> note.mapToDomain() } }
    }
}
