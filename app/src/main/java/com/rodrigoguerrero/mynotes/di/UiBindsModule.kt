package com.rodrigoguerrero.mynotes.di

import com.rodrigoguerrero.mynotes.settings.AppSettings
import com.rodrigoguerrero.mynotes.settings.AppSettingsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UiBindsModule {

    @Binds
    internal abstract fun bindAppSettings(appSettings: AppSettingsImpl): AppSettings
}
