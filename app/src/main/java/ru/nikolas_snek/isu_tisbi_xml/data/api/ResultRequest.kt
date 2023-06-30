package ru.nikolas_snek.isu_tisbi_xml.data.api

sealed class ResultRequest<out T> {
    data class Success<out T>(val data: T) : ResultRequest<T>()
    data class Error(val message: String) : ResultRequest<Nothing>()
}

