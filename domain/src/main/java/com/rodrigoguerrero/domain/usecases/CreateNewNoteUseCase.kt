package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDto
import com.rodrigoguerrero.domain.models.CreateNoteResult
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject

class CreateNewNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: NoteModel): CreateNoteResult {
        if (note.content.isNullOrEmpty() && note.title.isNullOrEmpty()) {
            return CreateNoteResult(note = note, isError = true)
        }

        val id = repository.addNote(note.mapToDto())
        return if (id >= 0) {
            CreateNoteResult(note, isError = false)
        } else {
            CreateNoteResult(note, isError = true)
        }
    }
}
