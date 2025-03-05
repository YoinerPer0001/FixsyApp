package com.example.technician_complete_profile.data.mappers

import com.example.technician_complete_profile.data.dto.SpecialtiesDTO
import com.example.technician_complete_profile.data.dto.TechDto
import com.example.technician_complete_profile.data.dto.TechspDTO
import com.example.technician_complete_profile.domain.model.SpecialtiesMN
import com.example.technician_complete_profile.domain.model.TechMN
import com.example.technician_complete_profile.domain.model.TechSpMN

fun List<SpecialtiesDTO>.toSpecialtiesMN() : List<SpecialtiesMN>{
    val list = mutableListOf<SpecialtiesMN>()

    this.forEach { item ->
        val newItem = SpecialtiesMN(
            name = item.name,
            id = item.id
        )
        list.add(newItem)
    }

    return list

}

fun List<TechspDTO>.toTechSpMN () : List<TechSpMN>{
    val list = mutableListOf<TechSpMN>()
    this.forEach { parameter->
        val newItem = TechSpMN(
            id = parameter.id,
            tec_info = parameter.tec_info.toTechMN(),
            esp_info = parameter.esp_info.toSpecialtiesMN()
        )
        list.add(newItem)
    }
    return list
}

fun TechDto.toTechMN () : TechMN {
    return TechMN(
        id = this.id,
        user_id = this.user_id,
        experience = this.experience,
        certificates = this.certificates,
        state = this.state,
        qualification = this.qualification
    )
}

fun SpecialtiesDTO.toSpecialtiesMN () : SpecialtiesMN {
    return SpecialtiesMN(
        id = this.id,
        name = this.name
    )
}