package ru.nikolas_snek.isu_tisbi_xml.data.repository

import android.util.Log
import androidx.datastore.core.IOException
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.ResponseBody
import ru.nikolas_snek.isu_tisbi_xml.data.TempUserApiData
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserDataStore
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginRequest
import ru.nikolas_snek.isu_tisbi_xml.data.models.PeopleRoleIdRequest
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.UserRepository
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile

/**
 *  Репозиторий отвечающий за всю работу с API и Data Store
 *
 * */
class UserRepositoryImpl(
    private val apiAuthService: ApiAuthService,
    private val apiStudentService: ApiStudentService,
    private val dataStore: UserDataStore,
) : BaseRepository(), UserRepository {
    suspend fun login(login: String, password: String): ResultRequest<String> {
        //todo можно удалить. Главное убрать зависимости далее
        val tempToken = obtainTempToken(login, password)

        val tempTokenValue = checkSuccess(obtainTempToken(login, password))
        val regIdValue = checkSuccess(obtainRegId(tempTokenValue))
        val personalTokenValue = checkSuccess(obtainPersonalToken(tempTokenValue, regIdValue))
        val studentHashValue = checkSuccess(obtainStudentHash(personalTokenValue))
        TempUserApiData.personalAuthToken = personalTokenValue
        TempUserApiData.firstAuthToken = tempTokenValue
        dataStore.apply {
            saveStudentHash(studentHashValue)
            saveLogin(login)
            savePassword(password)
            savePeopleRole(regIdValue)
        }
        return tempToken
    }


    suspend fun refreshData(): String {
        val login: String = dataStore.loginStudent.firstOrNull()!!
        val password: String = dataStore.passwordStudent.firstOrNull()!!
        val regIdValue: Int = dataStore.peopleRole.firstOrNull()!!

        val tempTokenValue = checkSuccess(obtainTempToken(login, password))
        val personalTokenValue = checkSuccess(obtainPersonalToken(tempTokenValue, regIdValue))
        TempUserApiData.personalAuthToken = personalTokenValue
        TempUserApiData.firstAuthToken = tempTokenValue
        return personalTokenValue
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

    private suspend fun obtainTrainingGroupList(personalTokenValue: String): ResultRequest<List<StudentRatingProfile>> {
        val studentHash: String = dataStore.studentHash.firstOrNull()!!
        val temp = safeApiCall {
            val response =
                apiStudentService.trainingGroupListGet(
                    token = personalTokenValue,
                    studentHash = studentHash
                ).execute().body()
            Log.d("List", response.toString())
            response
                ?: throw IllegalStateException("Login response is null or token is missing")
        }
        return temp
    }

    override suspend fun getTrainingGroupList(): List<StudentRatingProfile>{
        val temp = TempUserApiData.personalAuthToken!!
        val studentHash: String = dataStore.studentHash.firstOrNull()!!
        Log.d("List", studentHash.toString())
        val temp2 = obtainTrainingGroupList(temp)
        Log.d("List", temp2.toString())
        val temp3 = checkSuccess(temp2)
        return temp3
    }



}




