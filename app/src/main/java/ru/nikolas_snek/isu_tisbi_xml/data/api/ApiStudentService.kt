package ru.nikolas_snek.isu_tisbi_xml.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.nikolas_snek.isu_tisbi_xml.data.models.PeopleRoleIdResponse
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.PersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile

/**
 * Интерфейс для запросов:
 *  - список группы
 *  - расписание занятий
 *  - список онлайн занятий
 *  - информация об онлайн занятии
 *
 * */


interface ApiStudentService {

    /** запрос на получение рейтинга группы*/
    @GET("module/edu-rating/{studentHash}/stud")
    fun trainingGroupListGet(
        @Path("studentHash") studentHash: String,
        @Query("token") token: String,
    ): Call<List<StudentRatingProfile>>

    /** запрос на получение персонального плана обучения*/
    @GET("module/personal-plan/{studentHash}")
    fun personalPlantGet(
        @Path("studentHash") studentHash: String,
        @Query("token") token: String,
    ): Call<List<PersonalPlan>>

}