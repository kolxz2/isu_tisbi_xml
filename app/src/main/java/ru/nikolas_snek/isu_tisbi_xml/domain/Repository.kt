package ru.nikolas_snek.isu_tisbi_xml.domain

import retrofit2.Call
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginResponse

interface UserRepository {
    suspend fun login(username: String, password: String): ResultRequest<Call<LoginResponse>>
}