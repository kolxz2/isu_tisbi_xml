package ru.nikolas_snek.isu_tisbi_xml.domain

import ru.nikolas_snek.isu_tisbi_xml.domain.repository.UserRepository
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfileWithPosition

class GetTrainingRatingUseCase(private val repository: UserRepository) {

    suspend fun getTrainingGroupListWithPosition(): List<StudentRatingProfileWithPosition> {
        val groupRating: List<StudentRatingProfile> = repository.getTrainingGroupList()
        // сортируем рейтинг
        val sortedGroupRating = groupRating.sortedBy { it.moduleProcent }
        // создаем новый список StudentRatingProfileWithPosition
        var tempModuleProcent = 101
        var tempPosition = 0
        val sortedGroupRatingWithPosition =
            sortedGroupRating.mapIndexed { _, studentRatingProfile ->
                StudentRatingProfileWithPosition(
                    position = if (tempModuleProcent > studentRatingProfile.moduleProcent) {
                        tempModuleProcent = studentRatingProfile.moduleProcent
                        ++tempPosition
                    } else {
                        tempPosition
                    },
                    moduleProcent = studentRatingProfile.moduleProcent,
                    moduleResult = studentRatingProfile.moduleResult,
                    studId = studentRatingProfile.studId,
                    studentName = studentRatingProfile.studentName
                )
            }
        return sortedGroupRatingWithPosition
    }
}