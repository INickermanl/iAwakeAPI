package com.nickerman.test3.di.modules

import com.example.data.interactor.media_service.MediaServiceUseCaseImpl
import com.example.domain.interactor.MediaServiceUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideMediaServiceUseCase(mediaServiceUseCase: MediaServiceUseCaseImpl): MediaServiceUseCase = mediaServiceUseCase
}