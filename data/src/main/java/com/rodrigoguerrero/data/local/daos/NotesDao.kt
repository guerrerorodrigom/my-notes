package com.rodrigoguerrero.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rodrigoguerrero.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface NotesDao {

    @Query("SELECT * FROM notes WHERE isPinned = 0 ORDER BY modified_date DESC")
    fun getUnpinnedNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE isPinned = 1 ORDER BY modified_date DESC")
    fun getPinnedNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNote(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity): Long

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("UPDATE notes SET isPinned = :isPinned, modified_date = :modifiedTime WHERE id IN (:ids)")
    suspend fun update(isPinned: Boolean, ids: List<Int>, modifiedTime: String)
}
