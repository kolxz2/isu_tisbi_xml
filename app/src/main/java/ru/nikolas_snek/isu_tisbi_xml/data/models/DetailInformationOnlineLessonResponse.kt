package ru.nikolas_snek.isu_tisbi_xml.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DetailInformationOnlineLessonResponse(
    @Json(name = "taskText") val taskText: String,
    @Json(name = "remarks") val remarks: List<Remark>
)

@JsonClass(generateAdapter = true)
data class Remark(
    @Json(name = "taskRemarkId") val taskRemarkId: Long,
    @Json(name = "postponedDate") val postponedDate: String?, // Может быть null
    @Json(name = "shortRemark") val shortRemark: String,
    @Json(name = "remark") val remark: String,
    @Json(name = "dateCreate") val dateCreate: String,
    @Json(name = "sessionId") val sessionId: Long,
    @Json(name = "sessionOwnerName") val sessionOwnerName: String,
    @Json(name = "isWrong") val isWrong: Int,
    @Json(name = "statusTypeId") val statusTypeId: Int,
    @Json(name = "isEdit") val isEdit: Int,
    @Json(name = "taskId") val taskId: Long
)

