package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.dtos.NoteDto
import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDomain
import com.rodrigoguerrero.domain.mappers.mapToDto
import com.rodrigoguerrero.domain.models.CreateNoteResult
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject
import kotlinx.datetime.Clock

class CreateNewNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(): CreateNoteResult {

        val currentTime = Clock.System.now()

        val id = repository.addNote(
            NoteDto(
                createdTime = currentTime.toString(),
                modifiedTime = currentTime.toString(),
                id = null,
                content = null,
                title = null,
                isPinned = false,
                color = null
            )
        )

        return if (id >= 0) {
            val note = repository.getNote(id).mapToDomain()
            CreateNoteResult(note, isError = false)
        } else {
            CreateNoteResult(note = null, isError = true)
        }
    }
}
