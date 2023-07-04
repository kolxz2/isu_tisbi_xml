package ru.nikolas_snek.isu_tisbi_xml.data.models

import com.squareup.moshi.Json

data class TempTokenResponse(@Json(name = "token") val token: String)

data class PersonalTokenResponse(@Json(name = "token") val token: String)

data class StudentHashResponse(@Json(name = "studentHash") val studentHash: String)