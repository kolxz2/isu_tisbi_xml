package ru.nikolas_snek.isu_tisbi_xml.data.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.nikolas_snek.isu_tisbi_xml.BuildConfig

class RemoteDataSource {
    companion object {
        private const val BASE_URL = "https://isu.tisbi.ru/api/"
    }

    fun <API> buildTokenAPI(api: Class<API>, authToken: String? = null): API {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .also { client ->
                        if (BuildConfig.DEBUG) {
                            val login = HttpLoggingInterceptor()
                            login.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(login)
                        }
                    }.build()
            )
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
            .create(api)
    }

}