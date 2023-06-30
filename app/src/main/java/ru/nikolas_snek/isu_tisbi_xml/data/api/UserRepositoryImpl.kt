package ru.nikolas_snek.isu_tisbi_xml.data.api

import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest
import ru.nikolas_snek.isu_tisbi_xml.domain.UserRepository

/*class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun login(username: String, password: String): ResultRequest<String> {
        try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                val token = response.body()?.token
                if (token != null) {
                    return ResultRequest.Success(token)
                }
            }
            return ResultRequest.Error("Login failed")
        } catch (e: Exception) {
            return ResultRequest.Error(e.message ?: "An error occurred")
        }
    }
}*/
