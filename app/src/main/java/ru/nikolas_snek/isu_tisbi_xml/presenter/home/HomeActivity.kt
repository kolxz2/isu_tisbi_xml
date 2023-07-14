package ru.nikolas_snek.isu_tisbi_xml.presenter.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.RemoteDataSource
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserPreferences
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val remoteDataSource = RemoteDataSource()
        val userRepositoryImpl = UserRepositoryImpl(remoteDataSource.buildTokenAPI(ApiAuthService::class.java), UserPreferences(this) )
        lifecycleScope.launch{
            val personalToken  = userRepositoryImpl.refreshData()
            //Log.d("Login",personalToken )
        }

    }
}