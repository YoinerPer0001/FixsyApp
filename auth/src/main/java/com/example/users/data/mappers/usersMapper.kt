package com.example.users.data.mappers

import com.example.core.data.models.User
import com.example.users.data.dto.UserBasic
import com.example.users.domain.models.BasicUserBM

fun UserBasic.toUserBM () : BasicUserBM {
    return BasicUserBM(
        id = this.id,
        name= this.email,
        email = this.email,
        id_number = this.id_number,
        phone = this.phone,
        perfil_photo = this.perfil_photo,
        experience = this.experience,
        certificates = this.certificates,
        qualification = this.qualification,
        state = this.state,
        id_tech = this.id_tech
    )
}

fun BasicUserBM.toUserCore(): User {
    return User(
        id = this.id,
        name= this.name,
        email = this.email,
        id_number = this.id_number,
        phone = this.phone,
        perfil_photo = this.perfil_photo ?: "",
        experience = this.experience ?: "",
        certificates = this.certificates ?: "",
        qualification = this.qualification ?: 0f,
        state = this.state ?: "",
        id_tech = this.id_tech

    )
}