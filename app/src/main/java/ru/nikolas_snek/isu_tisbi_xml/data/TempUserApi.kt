package ru.nikolas_snek.isu_tisbi_xml.data

import android.app.Application

class TempUserApi:Application() {
    companion object {
        var personalAuthToken: String? = null
        var firstAuthToken: String? = null
    }
}