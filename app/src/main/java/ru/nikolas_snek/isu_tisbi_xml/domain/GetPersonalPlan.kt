package ru.nikolas_snek.isu_tisbi_xml.domain

import ru.nikolas_snek.isu_tisbi_xml.domain.repository.UserRepository
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.PersonalPlan
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Semester
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.Subject

class GetPersonalPlan(private val repository: UserRepository) {

    suspend fun getPersonalPlan(): HashMap<Semester, MutableList<Subject>> {
        return sortPersonalPlans(repository.getPersonalPlanList())
    }

    private fun sortPersonalPlans(personalPlans: List<PersonalPlan>): HashMap<Semester, MutableList<Subject>> {
        val learnPlan: HashMap<Semester, MutableList<Subject>> = hashMapOf()
        for (item in personalPlans) {
            // ветка куда попадут item с названием модулей
            if (item.semNumber == 0 && item.virtSemNumb != 0) {
                val semester =
                    Semester(name = item.sectName!!, number = item.virtSemNumb!!, emptyList())
                learnPlan[semester] = mutableListOf()
            } else if (item.semNumber != null) {
                addLessons(learnPlan, item)
            }
        }
        return learnPlan
    }

    private fun addLessons(
        learnPlan: HashMap<Semester, MutableList<Subject>>,
        item: PersonalPlan,
    ) {
        val semesterForAdd = findSemesterByNumber(learnPlan, item.semNumber!!)
        if (semesterForAdd != null) {
            val subject = Subject(
                name = item.sectName!!,
                status = item.typeName!!,
                score = item.CPFinalResult!!,
                // todo заполнить модули
                checkpoints = item.checkPoints!!
            )
            learnPlan[semesterForAdd]!!.add(subject)
        }
    }

    private fun findSemesterByNumber(
        semesters: HashMap<Semester, MutableList<Subject>>,
        number: Int,
    ): Semester? {
        for (semester in semesters.keys) {
            if (semester.number == number) {
                return semester
            }
        }
        return null
    }
}