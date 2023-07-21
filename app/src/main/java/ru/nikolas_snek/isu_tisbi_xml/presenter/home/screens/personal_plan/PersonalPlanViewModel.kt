package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan

import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.domain.GetPersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.PersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Semester
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Subject
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseViewModel

class PersonalPlanViewModel (
    private val repositoryImpl: UserRepositoryImpl
) : BaseViewModel(repositoryImpl){

    private val getPersonalPlan = GetPersonalPlan(repositoryImpl)

    suspend fun getPersonalPlanList(): HashMap<Semester, MutableList<Subject>> {
        return getPersonalPlan.getPersonalPlan()
    }
}