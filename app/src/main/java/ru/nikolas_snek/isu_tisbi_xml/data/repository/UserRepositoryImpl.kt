package ru.nikolas_snek.isu_tisbi_xml.data.repository

import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiService
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest

class UserRepositoryImpl(
    private val apiService: ApiService,
) : BaseRepository() {
    suspend fun login(username: String, password: String) =
        safeApiCall {
            val response = apiService.login(LoginRequest(username, password)).execute().body()
            response?.token
                ?: throw IllegalStateException("Login response is null or token is missing")
        }
}
