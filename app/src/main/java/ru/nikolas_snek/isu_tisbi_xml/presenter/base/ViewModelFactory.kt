package ru.nikolas_snek.isu_tisbi_xml.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.nikolas_snek.isu_tisbi_xml.data.repository.BaseRepository
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.presenter.auth.AuthViewModel
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.HomeViewModel
import java.lang.IllegalArgumentException


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java)-> AuthViewModel(repository as UserRepositoryImpl) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java)-> HomeViewModel(repository as UserRepositoryImpl) as T
            else -> throw IllegalArgumentException("ViewModalClass Not Found")
        }
    }
}
