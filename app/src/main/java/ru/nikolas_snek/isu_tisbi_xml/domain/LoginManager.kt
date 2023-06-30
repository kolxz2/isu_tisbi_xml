/*
package ru.nikolas_snek.isu_tisbi_xml.domain

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.api.UserRepositoryImpl

class LoginManager {
    private val userRepository: UserRepository

    init {
        val apiService = Retrofit.Builder()
            .baseUrl("https://isu.tisbi.ru/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        userRepository = UserRepositoryImpl(apiService)
    }

    suspend fun login(username: String, password: String): ResultRequest<String> {
        return userRepository.login(username, password)
    }
}*/
