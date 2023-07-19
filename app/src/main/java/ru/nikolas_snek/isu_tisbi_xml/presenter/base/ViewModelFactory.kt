package ru.nikolas_snek.isu_tisbi_xml.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.nikolas_snek.isu_tisbi_xml.data.repository.BaseRepository
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.presenter.auth.AuthViewModel
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.HomeViewModel
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.schedule_lessons.ScheduleLessonsViewModel
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan.PersonalPlanViewModel
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.student_profile.StudentProfileViewModel
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating.TrainingRatingViewModel
import java.lang.IllegalArgumentException


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java)-> AuthViewModel(repository as UserRepositoryImpl) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java)-> HomeViewModel(repository as UserRepositoryImpl) as T
            modelClass.isAssignableFrom(StudentProfileViewModel::class.java)-> StudentProfileViewModel(repository as UserRepositoryImpl) as T
            modelClass.isAssignableFrom(PersonalPlanViewModel::class.java)-> PersonalPlanViewModel(repository as UserRepositoryImpl) as T
            modelClass.isAssignableFrom(TrainingRatingViewModel::class.java)-> TrainingRatingViewModel(repository as UserRepositoryImpl) as T
            modelClass.isAssignableFrom(ScheduleLessonsViewModel::class.java)-> ScheduleLessonsViewModel(repository as UserRepositoryImpl) as T
            else -> throw IllegalArgumentException("ViewModalClass Not Found")
        }
    }
}
