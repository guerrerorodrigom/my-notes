package com.rodrigoguerrero.mynotes.domain.usecases

import com.rodrigoguerrero.mynotes.data.repositories.NotesRepository
import com.rodrigoguerrero.mynotes.domain.mappers.mapToDomain
import com.rodrigoguerrero.mynotes.domain.mappers.mapToDto
import com.rodrigoguerrero.mynotes.domain.models.NoteModel
import com.rodrigoguerrero.mynotes.domain.models.RetrieveNotesResult
import javax.inject.Inject

class RetrieveAllNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(): RetrieveNotesResult {
        val notes = repository.getAllNotes().map { it.mapToDomain() }
        return RetrieveNotesResult(notes)
    }
}
