package com.example.technician_complete_profile.presentation.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.PreferencesManager
import com.example.core.navigation.HomeScreen
import com.example.core.navigation.Login
import com.example.core.navigation.TechnicianHome
import com.example.technician_complete_profile.domain.model.SpecialtiesMN
import com.example.technician_complete_profile.domain.model.TechSpMN
import com.example.technician_complete_profile.domain.repository.ITechSpecialtiesRepository
import com.example.technician_complete_profile.domain.usecases.interfaces.IGetAllSpecialities
import com.example.technician_complete_profile.domain.usecases.interfaces.IGetTechSpecialties
import com.example.technician_complete_profile.domain.usecases.interfaces.ISaveTechSpecialtyDb
import com.example.technician_complete_profile.domain.usecases.interfaces.IUpdatePhotoUser
import com.example.technician_complete_profile.domain.usecases.interfaces.IUploadImagecloudinary
import com.example.technician_complete_profile.presentation.ui.screens.SendState
import com.example.technician_complete_profile.presentation.ui.screens.TechState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormCompletePerfilVM @Inject constructor(
    private val SpecialtiesUseCase: IGetAllSpecialities,
    private val uploadImageUseCase: IUploadImagecloudinary,
    private val updatePhoto: IUpdatePhotoUser,
    private val userPreferences: PreferencesManager,
    private val useCaseTechEsp: IGetTechSpecialties,
    private val saveEspecialty: ISaveTechSpecialtyDb,
    private val updateState : ITechSpecialtiesRepository
) : ViewModel() {

    private val _formNumber = MutableStateFlow(0)
    val formNumber: StateFlow<Int> = _formNumber


    private val _especialties = MutableStateFlow<List<SpecialtiesMN>?>(emptyList())
    val especialties: StateFlow<List<SpecialtiesMN>?> = _especialties

    private val _url_perfil = MutableStateFlow<String?>(null)
    val url_perfil: StateFlow<String?> = _url_perfil

    private val _photoUpdated = MutableStateFlow<Boolean?>(null)
    val photoUpdated: StateFlow<Boolean?> = _photoUpdated

    private val _techState = MutableSharedFlow<TechState>(replay = 1)
    val techState: SharedFlow<TechState> = _techState

    //form Values

    private val _sendState= MutableSharedFlow<SendState>(replay = 1) //id, valor
    val sendState: SharedFlow<SendState> = _sendState


    private val _formUserEsp= MutableStateFlow<String>("") //id, valor
    val formUserEsp: StateFlow<String> = _formUserEsp


    fun getSpecialties() {
        viewModelScope.launch {

            try {

                val response = SpecialtiesUseCase()
                _especialties.value = response

            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }

    fun uploadImage(path: Uri) {

        viewModelScope.launch {

            _techState.emit(TechState.Loading)
            try {

                val response = uploadImageUseCase(path) //upload to cloud
                _url_perfil.value = response


                delay(50)
                _techState.emit(TechState.Success)

            } catch (e: Exception) {

                _techState.emit(TechState.Error(e.message.toString()))
            }
        }
    }

    fun saveUserEsp (id:String) {

        viewModelScope.launch {

            _formUserEsp.emit(id)
        }
    }

    fun sendToDbEsp (){
        viewModelScope.launch {

            _sendState.emit(SendState.Loading)
            try {
                val id_tech = userPreferences.getUserBasicInfo().id_tech
                val response = saveEspecialty(id_tech, _formUserEsp.value )
                if(!response.first){
                    _sendState.emit(SendState.Error("Error al enviar, intente nuevamente"))
                }

                delay(50)
                _sendState.emit(SendState.Success)

            }catch (e:Exception){
                delay(50)
                _sendState.emit(SendState.Error(e.message.toString()))
            }
        }
    }


    fun updateImagePerfil(url: String, navigate: (destination: Any) -> Unit) {

        viewModelScope.launch {

            try {
                delay(50)
                _sendState.emit(SendState.Loading)
                //upload to db
                val userID = userPreferences.getUserId()
                Log.d("USER ID", userID.toString())
                Log.d("RESPONSE", url)


                val updated = updatePhoto(userID!!, url)
                _photoUpdated.value = !(updated == null || updated == false)

                userPreferences.saveImgPerfil(url)

            } catch (e: Exception) {
                Log.d("Error al enviar a db", e.message.toString())
                _sendState.emit(SendState.Error(e.message.toString()))
            } finally {
                delay(50)
                _sendState.emit(SendState.Success)
                navigate(TechnicianHome)
            }

        }
    }

    fun userDataVerify(navigate: (destination:Any)-> Unit) {

        viewModelScope.launch {
            val userInfo = userPreferences.getUserBasicInfo()
            try {
                val specialties: List<TechSpMN>? = useCaseTechEsp(userInfo.id_tech)
                Log.d("ESPECIALTIES", specialties.toString())
                if (userInfo.state == "pending") { // if technician state is pending send to complete information
                    Log.d("ENTRO", "1")
                    if (userInfo.perfil_photo.isNullOrEmpty()) {
                        Log.d("ENTRO", "2")
                        _techState.emit(TechState.Success)// if photo not found
                        _formNumber.value = 1
                    } else if (userInfo.experience.isNullOrEmpty()) {
                        Log.d("ENTRO", "3")
                        _techState.emit(TechState.Success)
                        _formNumber.value = 2
                    } else if (specialties.isNullOrEmpty()) { // technician don't have specialties
                        Log.d("ENTRO", "4")
                        _techState.emit(TechState.Success)
                        _formNumber.value = 3
                    } else {
                        Log.d("ENTRO", "5")
                        //update state to active
                        val updated = updateState.updateState(userPreferences.getUserBasicInfo().id_tech, "active")
                        if(!updated!!){
                            _techState.emit(TechState.Error("Error al actualizar estado, intente nuevamente"))
                        }
                        navigate(HomeScreen)
                    }

                } else if (userInfo.state == "active") {
                    // send to tech Home and send to Home
                    Log.d("ENTRO", "6")
                    navigate(HomeScreen)
                }

            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }

    }
}