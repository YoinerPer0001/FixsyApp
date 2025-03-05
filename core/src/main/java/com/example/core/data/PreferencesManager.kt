package com.example.core.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.core.data.models.User

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val KEY_USER_ID = "id_user"
        private const val KEY_URL_PERFIL = "user_img_perfil"
        private const val PREFS_ID_NUMBER = "id_number"
        private const val PREFS_USER_NAME = "name"
        private const val KEY_USER_EMAIL = "email"
        private const val KEY_USER_PHONE = "phone"
        private const val PREFS_USER_EXPERIENCE = "experience"
        private const val KEY_USER_CERTIFICATES = "certificates"
        private const val KEY_USER_QUALIFICATION = "qualification"
        private const val KEY_USER_STATE = "state"
        private const val KEY_USER_ID_TECH = "id_tech"
    }

    @SuppressLint("CommitPrefEdits")
    fun saveUserBasicInfo(user: User) {
        sharedPreferences.edit().putString(PREFS_ID_NUMBER, user.id).apply()
        sharedPreferences.edit().putString(PREFS_USER_NAME, user.name).apply()
        sharedPreferences.edit().putString(KEY_URL_PERFIL, user.perfil_photo).apply()
        sharedPreferences.edit().putString(KEY_USER_EMAIL, user.email).apply()
        sharedPreferences.edit().putString(KEY_USER_PHONE, user.phone).apply()
        sharedPreferences.edit().putString(PREFS_USER_EXPERIENCE, user.experience).apply()
        sharedPreferences.edit().putString(KEY_USER_CERTIFICATES, user.experience).apply()
        sharedPreferences.edit().putString(KEY_USER_QUALIFICATION, user.qualification.toString())
            .apply()
        sharedPreferences.edit().putString(KEY_USER_STATE, user.state).apply()
        sharedPreferences.edit().putString(KEY_USER_ID_TECH, user.id_tech).apply()
    }

    fun getUserBasicInfo(): User {
        return User(
            id = sharedPreferences.getString(KEY_USER_ID, "")!!,
            id_tech = sharedPreferences.getString(KEY_USER_ID_TECH, "")!!,
            id_number = sharedPreferences.getString(PREFS_ID_NUMBER, "")!!,
            name = sharedPreferences.getString(PREFS_USER_NAME, "")!!,
            email = sharedPreferences.getString(KEY_USER_EMAIL, "")!!,
            phone = sharedPreferences.getString(KEY_USER_PHONE, "")!!,
            perfil_photo =sharedPreferences.getString(KEY_URL_PERFIL, ""),
            experience = sharedPreferences.getString(PREFS_USER_EXPERIENCE, "")!!,
            certificates = sharedPreferences.getString(KEY_USER_CERTIFICATES, ""),
            qualification = sharedPreferences.getString(KEY_USER_QUALIFICATION, "0")?.toFloat(),
            state =sharedPreferences.getString(KEY_USER_STATE, "")
        )

    }


    fun saveUserId(id: String) {
        sharedPreferences.edit().putString(KEY_USER_ID, id).apply()
    }

    fun saveImgPerfil(url: String) {
        sharedPreferences.edit().putString(KEY_URL_PERFIL, url).apply()
    }

    fun getImgPerfil(): String? {
        return sharedPreferences.getString(KEY_URL_PERFIL, null)
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}