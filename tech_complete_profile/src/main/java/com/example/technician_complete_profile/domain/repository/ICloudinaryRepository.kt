package com.example.technician_complete_profile.domain.repository

import java.io.File

interface ICloudinaryRepository {

    fun uploadImage(filepath:File, onResult: (String?) -> Unit)
}