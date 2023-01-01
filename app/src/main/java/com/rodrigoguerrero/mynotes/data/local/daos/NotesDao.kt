package com.rodrigoguerrero.mynotes.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rodrigoguerrero.mynotes.data.local.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY modified_date DESC")
    fun getAllNotes(): Flow<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteEntity: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)
}
