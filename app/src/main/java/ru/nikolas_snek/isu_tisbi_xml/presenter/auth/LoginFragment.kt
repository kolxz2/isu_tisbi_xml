package ru.nikolas_snek.isu_tisbi_xml.presenter.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import okhttp3.ResponseBody
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.api.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentLoginBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.BaseFragment


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, UserRepositoryImpl>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is ResultRequest.Success -> {
                    val token: String = it.data
                    println("Login success. Token: $token")
                    Toast.makeText(requireContext(), token, Toast.LENGTH_LONG).show()
                }
                is ResultRequest.Error -> {
                    val error: ResponseBody? = it.message
                    println("Login error: $error")
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.btnLogin.setOnClickListener{
            val login = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.login(login, password)

        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = UserRepositoryImpl(remoteDataSource.buildAPI(ApiService::class.java))
}