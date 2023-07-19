package ru.nikolas_snek.isu_tisbi_xml.domain.repository

import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile

interface UserRepository {

    suspend fun getTrainingGroupList(): List<StudentRatingProfile>
}