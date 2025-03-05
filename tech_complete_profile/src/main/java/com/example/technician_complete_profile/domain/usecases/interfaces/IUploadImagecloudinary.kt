package com.example.technician_complete_profile.domain.usecases.interfaces

import android.net.Uri

interface IUploadImagecloudinary {
    suspend operator fun invoke (filepath:Uri) : String?
}