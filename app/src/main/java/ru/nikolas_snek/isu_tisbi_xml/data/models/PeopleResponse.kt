package ru.nikolas_snek.isu_tisbi_xml.data.models

import com.squareup.moshi.Json

// Определение модели данных
data class PeopleRoleIdResponse(
    val allowChangePassword: Boolean,
    val list: List<PeopleRoleId>
)

data class PeopleRoleId(
    val ARMName: String?,
    val ARMTitleName: String?,
    val assistantName: String?,
    val departmentId: Int?,
    val departmentName: String?,
    val internetPageName: String?,
    val isAssistant: Int?,
    val moduleName: String?,
    val orgId: Int?,
    val orgName: String?,
    val peopleRoleId: Int,
    val roleTypeId: Int?,
    val roleTypeName: String?,
    val viceId: Int?,
    val webConfig: String?,
)