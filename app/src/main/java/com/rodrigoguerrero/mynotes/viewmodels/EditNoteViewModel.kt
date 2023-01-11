package com.rodrigoguerrero.mynotes.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.domain.usecases.CreateNewNoteUseCase
import com.rodrigoguerrero.domain.usecases.EditNoteUseCase
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
    private val retrieveNoteUseCase: RetrieveNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {

    private val noteId: Int? = savedStateHandle["id"]

    private val _state = MutableStateFlow(NoteState())
    val state: StateFlow<NoteState> = _state

    init {
        init()
    }

    private fun init() {
        if (noteId == null) {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                    val result = createNewNoteUseCase(NoteModel())
                    it.copy(
                        title = result.note?.title.orEmpty(),
                        content = result.note?.content.orEmpty(),
                        editedDate = result.note?.modified.orEmpty(),
                        id = result.note?.id
                    )
                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                    val result = retrieveNoteUseCase(noteId)
                    it.copy(
                        title = result.note.title.orEmpty(),
                        content = result.note.content.orEmpty(),
                        editedDate = result.note.modified.orEmpty(),
                        id = result.note.id
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
        viewModelScope.launch {
            editNoteUseCase(
                NoteModel(
                    id = _state.value.id,
                    content = _state.value.content,
                    title = _state.value.title
                )
            )
        }
    }
}
