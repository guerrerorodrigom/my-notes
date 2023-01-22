package com.rodrigoguerrero.domain.usecases

import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDomain
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class RetrieveAllNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(): Flow<List<NoteModel>> {
        return repository.getAllNotes()
            .combine(repository.getPinnedNotes()) { notes, pinned ->
                pinned + notes
            }
            .map { notes -> notes.map { it.mapToDomain() } }
    }
}
