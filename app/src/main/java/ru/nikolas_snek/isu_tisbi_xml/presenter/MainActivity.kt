package ru.nikolas_snek.isu_tisbi_xml.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserPreferences
import ru.nikolas_snek.isu_tisbi_xml.presenter.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData(Dispatchers.IO).observe(this, Observer {
            Toast.makeText(this, it ?: "Login error", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AuthActivity::class.java))
        })
    }
}