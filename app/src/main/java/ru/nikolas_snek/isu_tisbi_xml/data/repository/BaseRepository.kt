package ru.nikolas_snek.isu_tisbi_xml.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ResultRequest<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultRequest.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ResultRequest.Error(throwable.response()?.errorBody())
                    }

                    else -> {
                        Log.d("NET throwable ", throwable.message.toString() )
                        ResultRequest.Error(null)
                    }
                }

            }
        }

    }
}