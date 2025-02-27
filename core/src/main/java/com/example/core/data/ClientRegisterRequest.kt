package com.example.core.data

data class ClientRegisterRequest(
    val name: String,
    val email: String,
    val id_number: Long,
    val phone: Long,
    val password: String,
    val address: String,
    val id_num_type: String,
    var type: String
) {
    fun validate(): Boolean {
        return name.isNotEmpty() || email.isNotEmpty() || email.contains("@") || id_number.toString()
            .isNotEmpty() || phone.toString().isNotEmpty() || password.isNotEmpty() || address.isNotEmpty() ||
            id_num_type.isNotEmpty()
    }
}
