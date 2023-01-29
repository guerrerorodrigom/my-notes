package com.rodrigoguerrero.data.local.datasources

import com.rodrigoguerrero.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

internal interface NotesDataSource {
    val unpinnedNotes: Flow<List<NoteEntity>>
    val pinnedNotes: Flow<List<NoteEntity>>

    suspend fun insertNote(note: NoteEntity): Long

    suspend fun update(note: NoteEntity)

    suspend fun getNote(id: Int): NoteEntity

    suspend fun delete(id: Int)

    suspend fun update(isPinned: Boolean, ids: List<Int>, modifiedTime: String)

    suspend fun update(color: Long?, ids: List<Int>, modifiedTime: String)
}
