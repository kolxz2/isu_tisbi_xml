package ru.nikolas_snek.isu_tisbi_xml.data.api


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginResponse

/**
* Формируем запрос
* */
interface ApiService {
    @POST("security/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
