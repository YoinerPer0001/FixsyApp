package com.example.fixsyapp.di.auth

import android.content.Context
import com.cloudinary.android.MediaManager
import com.example.technician_complete_profile.data.remote.SpecialtiesService
import com.example.technician_complete_profile.data.remote.UpdateUserPhotoService
import com.example.technician_complete_profile.data.remote.TechniciansService
import com.example.users.data.remote.AuthUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Properties
import javax.inject.Singleton

fun loadSecrets(context: Context): Properties {
    val properties = Properties()
    try {
        val inputStream = context.assets.open("secrets.properties")
        properties.load(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return properties
}


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.20.128:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCloudinary(@ApplicationContext context: Context): MediaManager {

        try {
            return MediaManager.get()
        } catch (e: Exception) {
            val config = mapOf(
                "cloud_name" to "difzcajot",
                "api_key" to "671342517551838",
                "api_secret" to "cBygZGTia0SA33rOwtdJtjzrsww"
            )


            MediaManager.init(context, config)

            return MediaManager.get()
        }

    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): AuthUserService {
        return retrofit.create(AuthUserService::class.java)
    }

    @Provides
    @Singleton
    fun provideTechnicianService(retrofit: Retrofit): TechniciansService {
        return retrofit.create(TechniciansService::class.java)
    }

    @Provides
    @Singleton
    fun provideSpecialtiesService(retrofit: Retrofit): SpecialtiesService {
        return retrofit.create(SpecialtiesService::class.java)
    }

    @Provides
    @Singleton
    fun provideUpdateUserPhotoService(retrofit: Retrofit): UpdateUserPhotoService {
        return retrofit.create(UpdateUserPhotoService::class.java)
    }




}