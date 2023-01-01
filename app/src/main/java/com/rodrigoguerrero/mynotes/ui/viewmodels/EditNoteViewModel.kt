package com.rodrigoguerrero.mynotes.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.mynotes.domain.models.NoteModel
import com.rodrigoguerrero.mynotes.domain.usecases.CreateNewNoteUseCase
import com.rodrigoguerrero.mynotes.ui.models.statemodels.NoteState
import com.rodrigoguerrero.mynotes.ui.models.uimodels.Note
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
    private val createNewNoteUseCase: CreateNewNoteUseCase
) : ViewModel() {

    private val noteId: Int? = savedStateHandle["id"]

    private val _state = MutableStateFlow(NoteState())
    val state: StateFlow<NoteState> = _state

    init {

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
                        created = "",
                        isPinned = false,
                        modified = ""
                    )
                )
            } else {

            }
        }
    }
}
