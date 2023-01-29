package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject
import kotlinx.datetime.Clock

class UpdateColorUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(notes: List<NoteModel>, color: Long?) {
        val currentTime = Clock.System.now()

        repository.updateNote(
            color = color,
            ids = notes.map { it.id },
            modifiedTime = currentTime.toString()
        )
    }
}
