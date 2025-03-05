package com.example.fixsyapp.di.auth

import android.content.Context
import com.cloudinary.android.MediaManager
import com.example.core.data.PreferencesManager
import com.example.technician_complete_profile.data.remote.SpecialtiesService
import com.example.technician_complete_profile.data.repository.CloudinaryRepository
import com.example.technician_complete_profile.data.repository.SpecialtiesRepository
import com.example.technician_complete_profile.domain.repository.ICloudinaryRepository
import com.example.technician_complete_profile.domain.repository.ISpecialtiesRepository
import com.example.technician_complete_profile.data.remote.TechniciansService
import com.example.technician_complete_profile.data.repository.TechSpecialtiesRepository
import com.example.technician_complete_profile.domain.repository.ITechSpecialtiesRepository
import com.example.users.data.remote.AuthUserService
import com.example.users.data.repository.UserAuthImplementation
import com.example.users.domain.repository.IUsersAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideRepositories {

    @Provides
    @Singleton
    fun provideUserRepository(userService: AuthUserService): IUsersAuth {
        return UserAuthImplementation(userService)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }


    @Provides
    @Singleton
    fun provideTechSpecialtiesRepository(techService: TechniciansService): ITechSpecialtiesRepository {
        return TechSpecialtiesRepository(techService)
    }

    @Provides
    @Singleton
    fun provideSpecialtiesRepository(SpService: SpecialtiesService): ISpecialtiesRepository {
        return SpecialtiesRepository(SpService)
    }

    @Provides
    @Singleton
    fun provideCloudinaryRepository(SpService: MediaManager): ICloudinaryRepository {
        return CloudinaryRepository(SpService)
    }
}