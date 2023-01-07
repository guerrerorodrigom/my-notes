package com.rodrigoguerrero.data.di

import com.rodrigoguerrero.data.local.datasources.NotesDataSource
import com.rodrigoguerrero.data.local.datasources.NotesDataSourceImpl
import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.data.repositories.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataBindsModule {

    @Binds
    internal abstract fun bindNotesDataSource(dataSource: NotesDataSourceImpl): NotesDataSource

    @Binds
    internal abstract fun bindNotesRepository(notesRepository: NotesRepositoryImpl): NotesRepository
}
