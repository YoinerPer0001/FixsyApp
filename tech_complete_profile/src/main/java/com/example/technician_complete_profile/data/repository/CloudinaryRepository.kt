package com.example.technician_complete_profile.data.repository

import android.util.Log
import com.cloudinary.Cloudinary
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.technician_complete_profile.domain.repository.ICloudinaryRepository
import java.io.File
import java.util.UUID
import javax.inject.Inject
import kotlin.uuid.Uuid

class CloudinaryRepository @Inject constructor(
    private val mediaManager: MediaManager
) : ICloudinaryRepository {
    override fun uploadImage(filepath: File, onResult: (String?) -> Unit){
        try {

            val response = mediaManager.upload(filepath.path)
                .option("folder", "uploads")  // Carpeta donde se guardará la imagen
                .callback(object : UploadCallback {
                    override fun onStart(requestId: String?) {
                        // Se inició la carga
                        Log.d("step 1", "Carga iniciada")
                    }

                    override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                        // Progreso de carga
                        Log.d("step 2", "Cargandoooo...imagen")
                    }

                    override fun onSuccess(requestId: String?, result: Map<*, *>?) {
                        onResult(result?.get("secure_url") as? String)
                    }

                    override fun onError(requestId: String?, error: ErrorInfo?) {
                        onResult(null)
                    }

                    override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                        onResult(null)
                    }
                }).dispatch()


        }catch (e : Exception){
            Log.d("Error to upload", e.message.toString())
        }
    }
}