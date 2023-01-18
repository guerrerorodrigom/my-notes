package com.rodrigoguerrero.mynotes.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.domain.usecases.CreateNewNoteUseCase
import com.rodrigoguerrero.domain.usecases.DeleteNoteUseCase
import com.rodrigoguerrero.domain.usecases.EditNoteUseCase
import com.rodrigoguerrero.domain.usecases.RetrieveNoteUseCase
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState
import com.rodrigoguerrero.mynotes.models.statemodels.EditNoteState.ContentState
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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
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

    private val updateNote = _state
        .filterIsInstance<ContentState>()
        .flatMapLatest {
            flowOf(NoteModel(title = it.title, content = it.content, id = it.id))
        }
        .distinctUntilChanged()

    init {
        init()
        viewModelScope.launch {
            updateNote
                .debounce(500)
                .collectLatest { note ->
                    editNoteUseCase(note)
                }
        }
    }

    private fun init() {
        if (noteId == null) {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                    val result = createNewNoteUseCase(NoteModel())
                    result.note?.let { note ->
                        ContentState(
                            title = note.title.orEmpty(),
                            content = note.content.orEmpty(),
                            editedDate = note.modified.orEmpty(),
                            id = note.id ?: 0
                        )
                    } ?: EditNoteState.ErrorState
                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                    val result = retrieveNoteUseCase(noteId)
                    with (result.note) {
                        ContentState(
                            title = title.orEmpty(),
                            content = content.orEmpty(),
                            editedDate = modified.orEmpty(),
                            id = id ?: 0
                        )
                    }
                }
            }
        }
    }

    fun updateTitle(value: String) {
        _state.update {
            if (it is ContentState) {
                it.copy(title = value)
            } else {
                it
            }
        }
    }

    fun updateContent(value: String) {
        _state.update {
            if (it is ContentState) {
                it.copy(content = value)
            } else {
                it
            }
        }
    }

    fun saveNote() {
        val currentState = _state.value
        if (currentState is ContentState) {
            val note = NoteModel(
                id = currentState.id,
                content = currentState.content,
                title = currentState.title
            )
            viewModelScope.launch {
                if (note.isEmpty()) {
                    deleteNoteUseCase(note.id ?: -1)
                } else {
                    editNoteUseCase(note)
                }
            }
        }
    }
}
