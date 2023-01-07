package com.rodrigoguerrero.mynotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.usecases.CreateNewNoteUseCase
import com.rodrigoguerrero.domain.usecases.RetrieveAllNotesUseCase
import com.rodrigoguerrero.mynotes.models.statemodels.NotesListState
import com.rodrigoguerrero.mynotes.models.uimodels.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val createNewNoteUseCase: CreateNewNoteUseCase,
    private val retrieveAllNotesUseCase: RetrieveAllNotesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NotesListState())
    val state: StateFlow<NotesListState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = retrieveAllNotesUseCase()
            _state.update { state ->
                state.copy(
                    notes = result.notes
                        .filterNot { it.id == null }
                        .map { Note(it.id!!, it.title, it.content) },
                    isLoading = false
                )
            }
        }
    }
}
