package ru.nikolas_snek.isu_tisbi_xml.presenter.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentHomeBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.auth.AuthViewModel
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseFragment


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepositoryImpl>() {

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = UserRepositoryImpl(remoteDataSource.buildTokenAPI(
        ApiAuthService::class.java), userPreferences)


    override fun onStart() {
        super.onStart()
        binding.button2.setOnClickListener {
            logout()
        }
    }
}