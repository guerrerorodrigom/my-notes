package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDomain
import com.rodrigoguerrero.domain.models.RetrieveSingleNoteResult
import javax.inject.Inject

class RetrieveNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(noteId: Int): RetrieveSingleNoteResult {
        val note = repository.getNote(noteId).mapToDomain()
        return RetrieveSingleNoteResult(note)
    }
}
