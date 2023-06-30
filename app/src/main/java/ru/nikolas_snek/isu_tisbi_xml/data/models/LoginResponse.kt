package ru.nikolas_snek.isu_tisbi_xml.data.models

import com.squareup.moshi.Json

data class LoginResponse(@Json(name = "token") val token: String)