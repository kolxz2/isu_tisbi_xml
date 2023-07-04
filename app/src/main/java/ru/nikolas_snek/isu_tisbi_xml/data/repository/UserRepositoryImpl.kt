package ru.nikolas_snek.isu_tisbi_xml.data.repository

import androidx.datastore.core.IOException
import kotlinx.coroutines.flow.first
import okhttp3.ResponseBody
import ru.nikolas_snek.isu_tisbi_xml.data.TempUserApi
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserPreferences
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest
import ru.nikolas_snek.isu_tisbi_xml.data.models.PeopleRoleIdRequest

/**
 *  Репозиторий отвечающий за всю работу с API и Data Store
 *
 * */
class UserRepositoryImpl(
    private val apiAuthService: ApiAuthService,
    private val preferences: UserPreferences,
) : BaseRepository() {

    suspend fun refreshData(): String {
        val login = preferences.loginStudent.first()!!
        val password = preferences.passwordStudent.first()!!
        val peopleRoleId = preferences.peopleRole.first()!!
        val tokenValue = checkSuccess(obtainTempToken(login, password))
        val personalTokenValue = checkSuccess(obtainPersonalToken(tokenValue, peopleRoleId))
        TempUserApi.personalAuthToken = personalTokenValue
        return personalTokenValue
    }

    suspend fun login(login: String, password: String): ResultRequest<String> {
        // todo почистить
        val tokenValue = checkSuccess(obtainTempToken(login, password))
        preferences.saveToken(tokenValue)
        val peopleRoleId = checkSuccess(obtainRegId(tokenValue))
        val personalTokenValue = checkSuccess(obtainPersonalToken(tokenValue, peopleRoleId))
        val studentHashValue = checkSuccess(obtainStudentHash(personalTokenValue))
        TempUserApi.personalAuthToken = personalTokenValue
        preferences.saveStudentHash(studentHashValue)
        preferences.saveLogin(login)
        preferences.savePassword(password)
        preferences.savePeopleRole(peopleRoleId)
        /*        Log.d("NET", "tokenValue $tokenValue")
                Log.d("NET", "regIdValue $regIdValue")
                Log.d("NET", "personalTokenValue $personalTokenValue")
                Log.d("NET", "studentHashValue $studentHashValue")*/
        return safeApiCall {
            val tokenResponse =
                apiAuthService.loginPost(LoginRequest(login, password)).execute().body()
            tokenResponse?.token
                ?: throw IllegalStateException("Login response is null or token is missing")
        }
    }

    private suspend fun obtainTempToken(username: String, password: String): ResultRequest<String> {
        return safeApiCall {
            val response =
                apiAuthService.loginPost(LoginRequest(username, password)).execute().body()
            response?.token
                ?: throw IllegalStateException("Login response is null or token is missing")
        }

    }

    private suspend fun obtainRegId(tokenValue: String): ResultRequest<Int> {
        return safeApiCall {
            val response =
                apiAuthService.roleListGet(
                    tokenValue
                ).execute().body()
            response?.list?.firstOrNull()?.peopleRoleId
                ?: throw IllegalStateException("Login response is null or token is missing")
        }

    }

    private suspend fun obtainPersonalToken(
        tokenValue: String,
        regIdValue: Int,
    ): ResultRequest<String> {
        return safeApiCall {
            val response =
                apiAuthService.roleUsePost(
                    tokenValue,
                    PeopleRoleIdRequest(regIdValue)
                ).execute().body()
            response?.token
                ?: throw IllegalStateException("Login response is null or token is missing")
        }
    }


    private suspend fun obtainStudentHash(personalTokenValue: String): ResultRequest<String> {
        return safeApiCall {
            val response =
                apiAuthService.studentSashGet(
                    personalTokenValue
                ).execute().body()
            response?.studentHash
                ?: throw IllegalStateException("Login response is null or token is missing")
        }
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveToken(token)
    }

    private fun <T> checkSuccess(response: ResultRequest<T>): T =
        when (response) {
            is ResultRequest.Success -> {
                response.data
            }

            is ResultRequest.Error -> {
                val error: ResponseBody? = response.message
                println("Login error: $error")
                throw IOException("Ошибка при выполнении запроса в сеть")
            }


        }
}




