package com.rodrigoguerrero.mynotes.data.di

import com.rodrigoguerrero.mynotes.data.local.datasources.NotesDataSource
import com.rodrigoguerrero.mynotes.data.local.datasources.NotesDataSourceImpl
import com.rodrigoguerrero.mynotes.data.repositories.NotesRepository
import com.rodrigoguerrero.mynotes.data.repositories.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataBindsModule {

    @Binds
    abstract fun bindNotesDataSource(dataSource: NotesDataSourceImpl): NotesDataSource

    @Binds
    abstract fun bindNotesRepository(notesRepository: NotesRepositoryImpl): NotesRepository
}
