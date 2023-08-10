package ru.nikolas_snek.isu_tisbi_xml.domain.repository.models

data class Semester(
    val name: String,
    val number: Int,
    val current: Boolean,
)

data class Subject(
    val name: String,
    val status: String?,
    val score: String?,
    val checkpoints: List<Checkpoint>,
    val isSelected: Boolean = false
)

data class SubjectCheckpoint(
    val name: String,
    val status: String,
    val score: String,
    val staffName: String?,
    val passingDate: String?
)