package com.rodrigoguerrero.mynotes.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.models.EditNoteResult
import com.rodrigoguerrero.domain.usecases.CreateNewNoteUseCase
import com.rodrigoguerrero.domain.usecases.DeleteNoteUseCase
import com.rodrigoguerrero.domain.usecases.EditNoteUseCase
import com.rodrigoguerrero.domain.usecases.RetrieveNoteUseCase
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.ContentState
import com.rodrigoguerrero.mynotes.models.statemodels.toDomainModel
import com.rodrigoguerrero.mynotes.models.statemodels.updateContent
import com.rodrigoguerrero.mynotes.models.statemodels.updateTitle
import com.rodrigoguerrero.mynotes.models.statemodels.updateWithNote
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@HiltViewModel
class EditNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createNewNoteUseCase: CreateNewNoteUseCase,
    private val retrieveNoteUseCase: RetrieveNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val noteId: Int? = savedStateHandle["id"]

    private val _state = MutableStateFlow<EditNoteState>(EditNoteState.LoadingState)
    val state: StateFlow<EditNoteState> = _state

    init {
        init()
        viewModelScope.launch {
            _state
                .filterIsInstance<ContentState>()
                .map { it.title to it.content }
                .distinctUntilChanged()
                .debounce(500)
                .collectLatest {
                    val currentState = _state.value
                    if (currentState is ContentState) {
                        editNoteUseCase(currentState.toDomainModel()).also { result ->
                            if (result is EditNoteResult.Saved) {
                                _state.updateWithNote(result.note)
                            }
                        }
                    }
                }
        }
    }

    private fun init() {
        if (noteId == null) {
            viewModelScope.launch(Dispatchers.IO) {
                createNewNoteUseCase().note?.also {
                    _state.updateWithNote(it)
                } ?: EditNoteState.ErrorState
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                retrieveNoteUseCase(noteId).also { _state.updateWithNote(it.note) }
            }
        }
    }

    fun updateTitle(value: String) {
        _state.updateTitle(value)
    }

    fun updateContent(value: String) {
        _state.updateContent(value)
    }

    fun saveNote() {
        val currentState = _state.value
        if (currentState is ContentState) {
            viewModelScope.launch {
                val note = currentState.toDomainModel()
                if (note.isEmpty()) {
                    deleteNoteUseCase(note.id)
                } else {
                    editNoteUseCase(note)
                }
            }
        }
    }
}
