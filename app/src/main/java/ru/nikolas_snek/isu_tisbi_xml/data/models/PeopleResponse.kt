package ru.nikolas_snek.isu_tisbi_xml.data.models

import com.squareup.moshi.Json

// Определение модели данных
data class PeopleRoleIdResponse(
    val allowChangePassword: Boolean,
    val list: List<PeopleRoleId>
)

data class PeopleRoleId(
    val ARMName: String,
    val ARMTitleName: String,
    val assistantName: String,
    val departmentId: Int,
    val peopleRoleId: Int,
)