package com.example.fixsyapp.di

import com.example.users.data.remote.UserService
import com.example.users.data.repository.UserImplementation
import com.example.users.domain.repository.IUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideRepositories {

    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService): IUsers {
        return UserImplementation(userService)
    }
}