package com.doseyenc.todo.di


import com.doseyenc.todo.data.ToDoDao
import com.doseyenc.todo.data.repositories.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideToDoRepository(
        toDoDao: ToDoDao
    ): ToDoRepository {
        return ToDoRepository(toDoDao)
    }
}