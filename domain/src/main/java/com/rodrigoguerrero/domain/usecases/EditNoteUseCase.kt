package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDomain
import com.rodrigoguerrero.domain.mappers.mapToDto
import com.rodrigoguerrero.domain.models.EditNoteResult
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject
import kotlinx.datetime.Clock

class EditNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: NoteModel): EditNoteResult {
        val savedNote = repository.getNote(note.id).mapToDomain()

        if (savedNote != note) {
            val currentTime = Clock.System.now()
            note
                .mapToDto(
                    createdTime = note.created,
                    modifiedTime = currentTime.toString()
                )
                .copy(id = note.id)
                .also { repository.updateNote(it) }

            val updatedNote = repository.getNote(note.id)

            return EditNoteResult.Saved(note = updatedNote.mapToDomain())
        } else {
            return EditNoteResult.SaveNotNeeded
        }
    }
}
