package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDomain
import com.rodrigoguerrero.domain.models.RetrieveNotesResult
import javax.inject.Inject

class RetrieveAllNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(): RetrieveNotesResult {
        val notes = repository.getAllNotes().map { it.mapToDomain() }
        return RetrieveNotesResult(notes)
    }
}
