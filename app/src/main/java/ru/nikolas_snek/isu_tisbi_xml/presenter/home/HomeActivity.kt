package ru.nikolas_snek.isu_tisbi_xml.presenter.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dependencyinjectionstart.CurvedBottomNavigation
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.api.RemoteDataSource
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserDataStore
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    companion object {
        val PERSONAL_PLAN = R.id.personalPlanFragment
        val SCHEDULE_LESSONS = R.id.scheduleLessonsFragment
        val TRAINING_RATING = R.id.trainingRatingFragment
        val STUDENT_PROFILE = R.id.studentProfile

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            initNavHost()
            setUpBottomNavigation()
        }
        val remoteDataSource = RemoteDataSource()
        val userRepositoryImpl = UserRepositoryImpl(
            remoteDataSource.buildTokenAPI(ApiAuthService::class.java),
            remoteDataSource.buildTokenAPI(ApiStudentService::class.java),
            UserDataStore(this)
        )
    }
    private fun ActivityHomeBinding.setUpBottomNavigation() {
        val bottomNavigationItems = mutableListOf(
            CurvedBottomNavigation.Model(
                SCHEDULE_LESSONS,
                getString(R.string.calendar),
                R.drawable.calendar
            ),
            CurvedBottomNavigation.Model(
                PERSONAL_PLAN,
                getString(R.string.education),
                R.drawable.education
            ),
            CurvedBottomNavigation.Model(
                TRAINING_RATING,
                getString(R.string.rating),
                R.drawable.rating
            ),
            CurvedBottomNavigation.Model(
                STUDENT_PROFILE,
                getString(R.string.person),
                R.drawable.person
            ),
        )
        bottomNavigation.apply {
            bottomNavigationItems.forEach { add(it) }
            setOnClickMenuListener {
                navController.navigate(it.id)
            }
            // optional
            setupNavController(navController)
        }
    }

    private fun initNavHost() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }


    // if you need your backstack of your items always back to home please override this method
    override fun onBackPressed() {
        if (navController.currentDestination!!.id == PERSONAL_PLAN)
            super.onBackPressed()
        else {
            when (navController.currentDestination!!.id) {
                SCHEDULE_LESSONS -> {
                    navController.popBackStack(R.id.personalPlanFragment, false)
                }

                STUDENT_PROFILE -> {
                    navController.popBackStack(R.id.personalPlanFragment, false)
                }
                TRAINING_RATING -> {
                    navController.popBackStack(R.id.personalPlanFragment, false)
                }

                else -> {
                    navController.navigateUp()
                }
            }
        }
    }
}