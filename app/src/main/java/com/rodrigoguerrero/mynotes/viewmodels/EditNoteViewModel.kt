package com.rodrigoguerrero.mynotes.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.domain.usecases.CreateNewNoteUseCase
import com.rodrigoguerrero.domain.usecases.RetrieveNoteUseCase
import com.rodrigoguerrero.mynotes.models.statemodels.NoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createNewNoteUseCase: CreateNewNoteUseCase,
    private val retrieveNoteUseCase: RetrieveNoteUseCase
) : ViewModel() {

    private val noteId: Int? = savedStateHandle["id"]

    private val _state = MutableStateFlow(NoteState())
    val state: StateFlow<NoteState> = _state

    init {
        noteId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                    val result = retrieveNoteUseCase(noteId)
                    it.copy(
                        title = result.note.title.orEmpty(),
                        content = result.note.content.orEmpty(),
                        editedDate = result.note.modified.orEmpty()
                    )
                }
            }
        }
    }

    fun updateTitle(value: String) {
        _state.update { it.copy(title = value) }
    }

    fun updateContent(value: String) {
        _state.update { it.copy(content = value) }
    }

    fun saveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            if (noteId == null) {
                createNewNoteUseCase(
                    NoteModel(
                        title = state.value.title,
                        content = state.value.content,
                        isPinned = false,
                    )
                )
            } else {

            }
        }
    }
}
