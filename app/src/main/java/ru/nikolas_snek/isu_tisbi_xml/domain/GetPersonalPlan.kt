package ru.nikolas_snek.isu_tisbi_xml.domain

import ru.nikolas_snek.isu_tisbi_xml.domain.repository.UserRepository
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.PersonalPlan

class GetPersonalPlan(private val repository: UserRepository) {

    suspend fun getPersonalPlan(): List<PersonalPlan> {
        return repository.getPersonalPlanList()
    }
}