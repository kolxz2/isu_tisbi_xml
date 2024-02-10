package ru.nikolas_snek.isu_tisbi_xml.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleLessonsResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "schedRootId") val schedRootId: Long,
    @Json(name = "threadName") val threadName: String,
    @Json(name = "threadLine") val threadLine: String,
    @Json(name = "contactTypeId") val contactTypeId: Int,
    @Json(name = "pairId") val pairId: Int,
    @Json(name = "dayId") val dayId: Int,
    @Json(name = "weekOdd") val weekOdd: Int,
    @Json(name = "weekEven") val weekEven: Int,
    @Json(name = "hoursQuant") val hoursQuant: Int,
    @Json(name = "code") val code: String,
    @Json(name = "name") val name: String,
    @Json(name = "buildingId") val buildingId: Int,
    @Json(name = "sectId") val sectId: Int,
    @Json(name = "sectName") val sectName: String,
    @Json(name = "start") val start: String,
    @Json(name = "finish") val finish: String,
    @Json(name = "shedStart") val shedStart: String,
    @Json(name = "shedFinish") val shedFinish: String,
    @Json(name = "placeTypeId") val placeTypeId: Int,
    @Json(name = "placeName") val placeName: String,
    @Json(name = "countWeek") val countWeek: Int,
    @Json(name = "currWeekOdd") val currWeekOdd: Int,
    @Json(name = "staffId") val staffId: Int,
    @Json(name = "staffName") val staffName: String,
    @Json(name = "extId") val extId: String?, // Может быть null
    @Json(name = "lessonTypeId") val lessonTypeId: String?, // Может быть null
    @Json(name = "lessonDate") val lessonDate: String?, // Может быть null
    @Json(name = "extStaffId") val extStaffId: String?, // Может быть null
    @Json(name = "extStaffName") val extStaffName: String?, // Может быть null
    @Json(name = "lessonTypeName") val lessonTypeName: String? // Может быть null
)
