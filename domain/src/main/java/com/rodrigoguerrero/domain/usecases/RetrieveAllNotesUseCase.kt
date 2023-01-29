package com.rodrigoguerrero.domain.usecases

import android.util.Log
import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.domain.mappers.mapToDomain
import com.rodrigoguerrero.domain.models.NoteModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class RetrieveAllNotesUseCase @Inject constructor(repository: NotesRepository) {

    val notes: Flow<List<NoteModel>> = repository.unpinnedNotes
        .onEach { Log.d("vanessa", "unpinnedNotes") }
            .combine(repository.pinnedNotes) { notes, pinned ->
                pinned + notes
            }
        .onEach { Log.d("vanessa", "combined") }
            .map { notes -> notes.map { it.mapToDomain() } }
}
