package com.rodrigoguerrero.data.di

import com.rodrigoguerrero.data.local.datasources.NotesDataSource
import com.rodrigoguerrero.data.local.datasources.NotesDataSourceImpl
import com.rodrigoguerrero.data.repositories.NotesRepository
import com.rodrigoguerrero.data.repositories.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindsModule {

    @Binds
    @Singleton
    internal abstract fun bindNotesDataSource(dataSource: NotesDataSourceImpl): NotesDataSource

    @Binds
    @Singleton
    internal abstract fun bindNotesRepository(notesRepository: NotesRepositoryImpl): NotesRepository
}
