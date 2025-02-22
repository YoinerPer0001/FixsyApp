package com.example.users.data.mappers

import com.example.users.data.dto.UserBasic
import com.example.users.domain.models.BasicUserBM

fun UserBasic.toUserBM () : BasicUserBM {
    return BasicUserBM(
        id = this.id,
        name= this.email,
        email = this.email,
        id_number = this.id_number,
        phone = this.phone
    )
}