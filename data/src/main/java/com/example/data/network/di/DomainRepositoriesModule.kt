package com.example.data.network.di

import com.example.data.network.repositories.DefaultBookRepository
import com.example.domain.repositories.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainRepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindBookRepository(repository: DefaultBookRepository): BookRepository
}
