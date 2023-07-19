package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.traning_screen

import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.domain.GetTrainingRatingUseCase
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfileWithPosition
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseViewModel

class TrainingRatingViewModel(
    private val repositoryImpl: UserRepositoryImpl
): BaseViewModel(repositoryImpl) {


    private val getTrainingRatingUseCase = GetTrainingRatingUseCase(repositoryImpl)

    suspend fun getTrainingRatingList(): List<StudentRatingProfile>{
        return getTrainingRatingUseCase.getTrainingGroupListWithPosition()
    }

}