package ru.nikolas_snek.isu_tisbi_xml.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.api.RemoteDataSource
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserDataStore
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.presenter.auth.AuthActivity
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDataStore = UserDataStore(this)
        val remoteDataSource = RemoteDataSource()

         val repositoryImpl =  UserRepositoryImpl(remoteDataSource.buildTokenAPI(
            ApiAuthService::class.java),
            remoteDataSource.buildTokenAPI(ApiStudentService::class.java),
            userDataStore)

        lifecycleScope.launch {
            val activity =
                if (userDataStore.checkAllVariablesNotNull()) {
                    repositoryImpl.refreshData()
                    HomeActivity::class.java
                } else AuthActivity::class.java
            startActivity(Intent(this@MainActivity, activity))
        }


        /*        userPreferences.authToken.asLiveData(Dispatchers.IO).observe(this, Observer {
                    val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
                    startActivity(Intent(this, activity))
                })*/
    }


}