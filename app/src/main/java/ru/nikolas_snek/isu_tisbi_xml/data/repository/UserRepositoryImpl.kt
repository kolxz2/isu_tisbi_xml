package ru.nikolas_snek.isu_tisbi_xml.data.repository

import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserPreferences
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest

class UserRepositoryImpl(
    private val apiAuthService: ApiAuthService,
    private val preferences: UserPreferences
) : BaseRepository() {
    suspend fun login(username: String, password: String) =
        safeApiCall {
            val response = apiAuthService.login(LoginRequest(username, password)).execute().body()
            response?.token
                ?: throw IllegalStateException("Login response is null or token is missing")
        }

    suspend fun saveAuthToken(token: String){
        preferences.saveToken(token)
    }
}
