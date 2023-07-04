package ru.nikolas_snek.isu_tisbi_xml.data.api.worked
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest
import ru.nikolas_snek.isu_tisbi_xml.data.models.TempTokenResponse

/*
data class LoginRequest(val login: String, val password: String)
data class LoginResponse(@Json(name = "token") val token: String)*/

/*interface ApiService {
    @POST("security/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}*/

 fun main() {
    val moshi = Moshi.Builder().build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://isu.tisbi.ru/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiAuthService = retrofit.create(ApiAuthService::class.java)

    val loginRequest = LoginRequest("НСучёв", "kolxzchek")
    val call = apiAuthService.loginPost(loginRequest)

    call.enqueue(object : Callback<TempTokenResponse> {
        override fun onResponse(call: Call<TempTokenResponse>, response: Response<TempTokenResponse>) {
            if (response.isSuccessful) {
                val loginResponse = response.body()
                val token = loginResponse?.token
                if (token != null) {
                    println("Token: $token")
                } else {
                    println("Token not found in the response.")
                }
            } else {
                println("Request failed with code: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<TempTokenResponse>, t: Throwable) {
            println("Request failed: ${t.message}")
        }
    })
}
