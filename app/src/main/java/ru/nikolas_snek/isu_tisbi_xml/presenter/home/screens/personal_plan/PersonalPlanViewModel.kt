package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan

import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.domain.GetPersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.PersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseViewModel

class PersonalPlanViewModel (
    private val repositoryImpl: UserRepositoryImpl
) : BaseViewModel(repositoryImpl){

    private val getPersonalPlan = GetPersonalPlan(repositoryImpl)

    suspend fun getPersonalPlanList(): List<PersonalPlan>{
        return repositoryImpl.getPersonalPlanList()
    }
}