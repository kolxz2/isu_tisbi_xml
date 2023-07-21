package ru.nikolas_snek.isu_tisbi_xml.domain.repository.models

data class Checkpoint(
    val checkPointName: String?,
    val CPFinalResultDate: String?,
    val typeName: String?,
    val finalResult: String?,
    val staffName: String?,
    val semNumber: Int?
)