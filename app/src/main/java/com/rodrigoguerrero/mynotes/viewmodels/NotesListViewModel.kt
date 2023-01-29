package com.rodrigoguerrero.mynotes.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.usecases.RetrieveAllNotesUseCase
import com.rodrigoguerrero.domain.usecases.UpdateColorUseCase
import com.rodrigoguerrero.domain.usecases.UpdatePinnedUseCase
import com.rodrigoguerrero.mynotes.models.mappers.toDomainModel
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
    private val updatePinnedUseCase: UpdatePinnedUseCase,
    private val updateColorUseCase: UpdateColorUseCase,
    private val appSettings: AppSettings
) : ViewModel() {

    private val _state = MutableStateFlow(NotesListState())
    val state: StateFlow<NotesListState> = _state

    init {
        subscribeToListModeChanges()
        subscribeToNotesChanges()
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

    fun updatePinnedNotes() {
        viewModelScope.launch {
            val selectedNotes = _state.value.notes
                .filter { it.isSelected }
                .map { it.toDomainModel() }
            updatePinnedUseCase(selectedNotes)
            _state.updateUnselectAll()
        }
    }

    fun updateColorInSelectedNotes(color: Color?) {
        viewModelScope.launch {
            val selectedNotes = _state.value.notes
                .filter { it.isSelected }
                .map { it.toDomainModel() }

            updateColorUseCase(
                color = if (color == Color.Transparent) null else color?.value?.toLong(),
                notes = selectedNotes
            )
            _state.updateUnselectAll()
        }
    }

    private fun subscribeToListModeChanges() {
        viewModelScope.launch(Dispatchers.IO) {
            appSettings.listMode.collectLatest { listMode ->
                _state.updateListMode(listMode)
            }
        }
    }

    private fun subscribeToNotesChanges() {
        viewModelScope.launch(Dispatchers.IO) {
            retrieveAllNotesUseCase.notes.collect { notes ->
                _state.updateWithNotes(notes)
            }
        }
    }
}
