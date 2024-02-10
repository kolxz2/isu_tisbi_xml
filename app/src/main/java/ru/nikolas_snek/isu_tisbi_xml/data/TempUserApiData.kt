package ru.nikolas_snek.isu_tisbi_xml.data

import android.app.Application

/**
 *  Синглтон хронящий значения токенов для авторизации приложения
 *
 * */
class TempUserApiData : Application() {
    companion object {
        var personalAuthToken: String? = null
        var firstAuthToken: String? = null
    }
}