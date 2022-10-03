package com.fictivestudios.demoarcitectureapp.di

import android.content.Context
import com.fictivestudios.demoarcitectureapp.utils.db.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object PrefsModule {

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context):UserPreferences =
        UserPreferences(context)
}