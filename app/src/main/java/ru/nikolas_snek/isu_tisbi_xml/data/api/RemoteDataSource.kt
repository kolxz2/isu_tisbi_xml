package ru.nikolas_snek.isu_tisbi_xml.data.api

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSource {
    companion object{
        private const val BASE_URL = "https://isu.tisbi.ru/api/"
    }

    suspend fun <API> buildAPI(api :Class<API>): API{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
            .create(api)
    }
}