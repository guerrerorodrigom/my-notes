package com.rodrigoguerrero.mynotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.usecases.RetrieveAllNotesUseCase
import com.rodrigoguerrero.mynotes.models.statemodels.NotesListState
import com.rodrigoguerrero.mynotes.models.uimodels.Note
import com.rodrigoguerrero.mynotes.settings.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val retrieveAllNotesUseCase: RetrieveAllNotesUseCase,
    private val appSettings: AppSettings
) : ViewModel() {

    private val _state = MutableStateFlow(NotesListState())
    val state: StateFlow<NotesListState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            appSettings.listMode.collectLatest { listMode ->
                _state.update { state -> state.copy(listMode = listMode) }
            }
        }
    }

    fun loadNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            retrieveAllNotesUseCase().collectLatest { notes ->
                _state.update { state ->
                    state.copy(
                        notes = notes.map { Note(it.id, it.title, it.content, it.color) },
                        isLoading = false
                    )
                }
            }
        }
    }

    fun toggleListMode() {
        viewModelScope.launch(Dispatchers.IO) {
            appSettings.toggleListType()
        }
    }
}
