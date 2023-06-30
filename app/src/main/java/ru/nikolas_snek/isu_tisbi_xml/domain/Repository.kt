package ru.nikolas_snek.isu_tisbi_xml.domain

import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest

interface UserRepository {
    suspend fun login(username: String, password: String): ResultRequest<String>
}