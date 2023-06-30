package ru.nikolas_snek.isu_tisbi_xml.data.api

import okhttp3.ResponseBody

sealed class ResultRequest<out T> {
    data class Success<out T>(val data: T) : ResultRequest<T>()
    data class Error(val message: ResponseBody?) : ResultRequest<Nothing>()
}

