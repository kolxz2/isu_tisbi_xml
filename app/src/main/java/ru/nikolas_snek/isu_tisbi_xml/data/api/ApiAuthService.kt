package ru.nikolas_snek.isu_tisbi_xml.data.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest
import ru.nikolas_snek.isu_tisbi_xml.data.models.TempTokenResponse
import ru.nikolas_snek.isu_tisbi_xml.data.models.PeopleRoleIdRequest
import ru.nikolas_snek.isu_tisbi_xml.data.models.PeopleRoleIdResponse
import ru.nikolas_snek.isu_tisbi_xml.data.models.PersonalTokenResponse
import ru.nikolas_snek.isu_tisbi_xml.data.models.StudentHashResponse

/**
* Формируем запрос
* */
interface ApiAuthService {

    /** запрос на получение authToken*/
    @POST("security/login")
    fun loginPost(@Body request: LoginRequest): Call<TempTokenResponse>

    /** запрос на получение personalAuthToken*/
    @POST("security/role-use")
    fun roleUsePost(@Query("token") token: String, @Body request: PeopleRoleIdRequest): Call<PersonalTokenResponse>

    /** запрос на получение peopleRoleId*/
    @GET("security/role-list")
    fun roleListGet(@Query("token") token: String): Call<PeopleRoleIdResponse>

    /** запрос на получение studentHash*/
    @GET("area/user/student/")
    fun studentSashGet(@Query("token") personalToken: String): Call<StudentHashResponse>


}
