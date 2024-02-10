package ru.nikolas_snek.isu_tisbi_xml.data.api

import OnlineLessonsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.nikolas_snek.isu_tisbi_xml.data.models.DetailInformationOnlineLessonResponse
import ru.nikolas_snek.isu_tisbi_xml.data.models.ScheduleLessonsResponse
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

    /** запрос на список всех онлайн занятий и их taskId (нужны для следующих запросов)*/
    @GET("module/task/")
    fun onlineLessonsGet(
        @Query("token") token: String,
    ): Call<OnlineLessonsResponse>

    /** запрос на получение детальной информации по значению taskId
     *
     * @param taskId id отдельного онлайн зантия
     *
     * */
    @GET("module/personal-plan/{taskId}")
    fun detailInformationOnlineLessonGet(
        @Path("taskId") taskId: String,
        @Query("token") token: String,
    ): Call<DetailInformationOnlineLessonResponse>

    /**
     * запрос на получение расписания занятий студента в указнные диапазоны дат
     * */

    @GET("module/edu-schedule/{studentHash}/stud")
    fun scheduleLessonsGet(
        @Path("studentHash") studentHash: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("token") token: String,
    ): Call<List<ScheduleLessonsResponse>>

}