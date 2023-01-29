package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject
import kotlinx.datetime.Clock

class UpdatePinnedUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(notes: List<NoteModel>) {
        val currentTime = Clock.System.now()
        val isPinned = if (notes.all { it.isPinned }) {
            false
        } else {
            true
        }

        repository.updateNote(
            isPinned = isPinned,
            ids = notes.map { it.id },
            modifiedTime = currentTime.toString()
        )
    }
}
