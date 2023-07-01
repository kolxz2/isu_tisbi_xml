package ru.nikolas_snek.isu_tisbi_xml.presenter.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.data.models.LoginResponse

class AuthViewModel(private val repositoryImpl : UserRepositoryImpl) : ViewModel(){

    private val _loginResponse : MutableLiveData<ResultRequest<String>> = MutableLiveData()
    val loginResponse: MutableLiveData<ResultRequest<String>>
        get() = _loginResponse

    fun login(login: String, password: String) = viewModelScope.launch {
        _loginResponse.value =  repositoryImpl.login(login, password)
    }
}
