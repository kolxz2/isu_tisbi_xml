package ru.nikolas_snek.isu_tisbi_xml.domain.repository.models

data class PersonalPlan(
    val sectName: String?,
    val virtSemNumb: Int?,
    val semNumber: Int?,
    val typeName: String?,
    val CPFinalResult: String?,
    val checkPoints: List<Checkpoint>? = emptyList()
)

