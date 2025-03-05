package com.example.technician_complete_profile.domain.usecases.implementations

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.example.technician_complete_profile.data.mappers.getFileFromUri
import com.example.technician_complete_profile.domain.repository.ICloudinaryRepository
import com.example.technician_complete_profile.domain.usecases.interfaces.IUploadImagecloudinary
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UploadImagecloudinary @Inject constructor(
    private val repository : ICloudinaryRepository,
    private val context: Context
) : IUploadImagecloudinary {
    override suspend fun invoke(filepath: Uri): String? {

        val file = getFileFromUri(context, filepath) ?: return null

        return suspendCoroutine { continuation->
            repository.uploadImage(filepath = file){ url->
                continuation.resume(url)
            }
        }

    }
}