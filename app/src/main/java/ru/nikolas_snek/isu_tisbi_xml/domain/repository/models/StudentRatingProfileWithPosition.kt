package ru.nikolas_snek.isu_tisbi_xml.domain.repository.models

data class StudentRatingProfileWithPosition(
    val position: Int,
    val moduleProcent: Int,
    val moduleResult: Int,
    val studId: Int,
    val studentName: String
)