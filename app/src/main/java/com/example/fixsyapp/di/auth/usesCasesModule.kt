package com.example.fixsyapp.di.auth

import com.example.users.domain.repository.IUsers
import com.example.users.domain.usecases.implementations.ClientRegister
import com.example.users.domain.usecases.implementations.Userlogin
import com.example.users.domain.usecases.interfaces.IClientRegister
import com.example.users.domain.usecases.interfaces.ILogin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object usesCasesModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: IUsers) : ILogin {
        return Userlogin(repository)
    }

    @Provides
    @Singleton
    fun provideClientRegisterUseCase(repository: IUsers): IClientRegister {
        return ClientRegister(repository)
    }
}