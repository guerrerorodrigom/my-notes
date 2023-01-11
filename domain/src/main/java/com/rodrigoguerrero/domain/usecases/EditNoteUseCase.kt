package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDto
import com.rodrigoguerrero.domain.models.CreateNoteResult
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: NoteModel): CreateNoteResult {
        repository.updateNote(note.mapToDto().copy(id = note.id))
        return CreateNoteResult(note, isError = false)
    }
}
