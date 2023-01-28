package com.rodrigoguerrero.mynotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.usecases.RetrieveAllNotesUseCase
import com.rodrigoguerrero.mynotes.models.statemodels.NotesListState
import com.rodrigoguerrero.mynotes.models.statemodels.updateListMode
import com.rodrigoguerrero.mynotes.models.statemodels.updateSelectNote
import com.rodrigoguerrero.mynotes.models.statemodels.updateUnselectAll
import com.rodrigoguerrero.mynotes.models.statemodels.updateWithNotes
import com.rodrigoguerrero.mynotes.settings.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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
                _state.updateListMode(listMode)
            }
        }
    }

    fun loadNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            retrieveAllNotesUseCase().collectLatest { notes ->
                _state.updateWithNotes(notes)
            }
        }
    }

    fun toggleListMode() {
        viewModelScope.launch(Dispatchers.IO) {
            appSettings.toggleListType()
        }
    }

    fun closeEditBar() {
        _state.updateUnselectAll()
    }

    fun toggleNoteSelected(id: Int) {
        _state.updateSelectNote(id)
    }
}
