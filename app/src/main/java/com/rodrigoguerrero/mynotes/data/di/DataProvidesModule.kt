package com.rodrigoguerrero.mynotes.data.di

import android.content.Context
import androidx.room.Room
import com.rodrigoguerrero.mynotes.data.local.NotesDatabase
import com.rodrigoguerrero.mynotes.data.local.daos.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataProvidesModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, "my-notes-db").build()

    @Provides
    fun providesNotesDao(database: NotesDatabase): NotesDao = database.notesDao()
}
