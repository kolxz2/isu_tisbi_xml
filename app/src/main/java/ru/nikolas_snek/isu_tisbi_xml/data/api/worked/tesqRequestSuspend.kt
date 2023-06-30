package ru.nikolas_snek.isu_tisbi_xml.data.api.worked

import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.api.repository.UserRepositoryImpl


fun main() {
    val apiService = createApiService()
    val userRepository = UserRepositoryImpl(apiService)

    val result = runBlocking { userRepository.login("НСучёв", "kolxzchek") }

    when (result) {
        is ResultRequest.Success -> {
            val token: String = result.data
            println("Login success. Token: $token")
        }
        is ResultRequest.Error -> {
            val error: ResponseBody? = result.message
            println("Login error: $error")
        }
    }
}

private fun createApiService(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://isu.tisbi.ru/api/")
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()

    return retrofit.create(ApiService::class.java)
}

