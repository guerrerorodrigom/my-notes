package com.rodrigoguerrero.data.di

import android.content.Context
import androidx.room.Room
import com.rodrigoguerrero.data.local.NotesDatabase
import com.rodrigoguerrero.data.local.daos.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataProvidesModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, "my-notes-db").build()

    @Provides
    @Singleton
    internal fun providesNotesDao(database: NotesDatabase): NotesDao = database.notesDao()
}
