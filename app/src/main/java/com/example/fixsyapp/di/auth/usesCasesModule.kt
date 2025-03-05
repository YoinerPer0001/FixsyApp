package com.example.fixsyapp.di.auth

import android.content.Context
import com.example.technician_complete_profile.data.repository.UsersRepository
import com.example.technician_complete_profile.domain.repository.ICloudinaryRepository
import com.example.technician_complete_profile.domain.repository.ISpecialtiesRepository
import com.example.technician_complete_profile.domain.repository.ITechSpecialtiesRepository
import com.example.technician_complete_profile.domain.usecases.implementations.GetTechSpecialties
import com.example.technician_complete_profile.domain.usecases.interfaces.IGetTechSpecialties
import com.example.users.domain.repository.IUsersAuth
import com.example.users.domain.usecases.implementations.ClientRegister
import com.example.technician_complete_profile.domain.usecases.implementations.GetAllSpecialities
import com.example.technician_complete_profile.domain.usecases.implementations.SaveTechSpecialtyDb
import com.example.technician_complete_profile.domain.usecases.implementations.UpdatePhotoUser
import com.example.technician_complete_profile.domain.usecases.implementations.UploadImagecloudinary
import com.example.users.domain.usecases.implementations.Userlogin
import com.example.users.domain.usecases.interfaces.IClientRegister
import com.example.technician_complete_profile.domain.usecases.interfaces.IGetAllSpecialities
import com.example.technician_complete_profile.domain.usecases.interfaces.ISaveTechSpecialtyDb
import com.example.technician_complete_profile.domain.usecases.interfaces.IUpdatePhotoUser
import com.example.technician_complete_profile.domain.usecases.interfaces.IUploadImagecloudinary
import com.example.users.domain.usecases.interfaces.ILogin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object usesCasesModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: IUsersAuth) : ILogin {
        return Userlogin(repository)
    }

    @Provides
    @Singleton
    fun provideClientRegisterUseCase(repository: IUsersAuth): IClientRegister {
        return ClientRegister(repository)
    }

    @Provides
    @Singleton
    fun provideTechSpecialtiesUseCase(repository: ITechSpecialtiesRepository): IGetTechSpecialties {
        return GetTechSpecialties(repository)
    }


    @Provides
    @Singleton
    fun provideGetAllSpecialitiesUseCase(repository: ISpecialtiesRepository): IGetAllSpecialities {
        return GetAllSpecialities(repository)
    }

    @Provides
    @Singleton
    fun provideUploadImagecloudinaryUseCase(repository: ICloudinaryRepository, @ApplicationContext context: Context): IUploadImagecloudinary {
        return UploadImagecloudinary (repository, context )
    }

    @Provides
    @Singleton
    fun provideUpdatePhotoUserUseCase(repository: UsersRepository): IUpdatePhotoUser {
        return UpdatePhotoUser  (repository)
    }

    @Provides
    @Singleton
    fun provideSaveTechSpecialtyDbUseCase(repository: ITechSpecialtiesRepository): ISaveTechSpecialtyDb {
        return SaveTechSpecialtyDb(repository)
    }
}