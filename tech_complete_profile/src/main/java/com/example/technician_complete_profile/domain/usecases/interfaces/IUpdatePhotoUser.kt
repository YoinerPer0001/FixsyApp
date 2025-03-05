package com.example.technician_complete_profile.domain.usecases.interfaces

interface IUpdatePhotoUser {
    suspend operator fun invoke(id:String, url:String):Boolean?
}