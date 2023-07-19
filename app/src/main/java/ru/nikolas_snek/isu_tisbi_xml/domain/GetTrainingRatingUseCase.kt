package ru.nikolas_snek.isu_tisbi_xml.domain

import ru.nikolas_snek.isu_tisbi_xml.domain.repository.UserRepository
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfileWithPosition

class GetTrainingRatingUseCase(private val repository: UserRepository) {

    suspend fun getTrainingGroupListWithPosition(): List<StudentRatingProfile> {
        val groupRating: List<StudentRatingProfile> = repository.getTrainingGroupList()
        // сортируем рейтинг
        val sortedGroupRating = groupRating.sortedBy { it.moduleProcent }
        // создаем новый список StudentRatingProfileWithPosition
        // todo при создании нового студета нужно объеденять в одну группу студентов с
        //  одинаковыми баллами и давать один номер в рейтинге
        val sortedGroupRatingWithPosition =
            sortedGroupRating.mapIndexed { index, studentRatingProfile ->
                StudentRatingProfileWithPosition(
                    position = index + 1,
                    moduleProcent = studentRatingProfile.moduleProcent,
                    moduleResult = studentRatingProfile.moduleResult,
                    studId = studentRatingProfile.studId,
                    studentName = studentRatingProfile.studentName
                )
            }
        return sortedGroupRating
    }
}