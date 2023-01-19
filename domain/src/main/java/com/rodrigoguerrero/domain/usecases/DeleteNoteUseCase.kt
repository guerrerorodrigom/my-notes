package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteNote(id)
    }
}
