package ru.nikolas_snek.isu_tisbi_xml.presenter.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import okhttp3.ResponseBody
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentLoginBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseFragment
import ru.nikolas_snek.isu_tisbi_xml.presenter.enable
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.HomeActivity
import ru.nikolas_snek.isu_tisbi_xml.presenter.startNewActivity
import ru.nikolas_snek.isu_tisbi_xml.presenter.visible


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, UserRepositoryImpl>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.pbLogin.visible(false)
        binding.btnLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.pbLogin.visible(false)
            when (it) {
                is ResultRequest.Success -> {
                    requireActivity().startNewActivity(HomeActivity::class.java)
                    Toast.makeText(requireContext(), "Login success.", Toast.LENGTH_SHORT).show()
                }
                is ResultRequest.Error -> {
                    val error: ResponseBody? = it.message
                    println("Login error: $error")
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_LONG).show()
                    login()
                }
            }
        })

        binding.etPassword.addTextChangedListener {
            val email = binding.etPassword.text.toString().trim()
            binding.btnLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }


        binding.btnLogin.setOnClickListener {
            login()

        }
    }

    private fun login() {
        val login = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        binding.pbLogin.visible(true)
        viewModel.login(login, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        UserRepositoryImpl(
            remoteDataSource.buildTokenAPI(ApiAuthService::class.java),
            remoteDataSource.buildTokenAPI(ApiStudentService::class.java),
            userDataStore
        )
}