package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentStudentProfileBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseFragment


class StudentProfileFragment : BaseFragment<StudentProfileViewModel, FragmentStudentProfileBinding, UserRepositoryImpl>() {

    override fun getViewModel() = StudentProfileViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentStudentProfileBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = UserRepositoryImpl(remoteDataSource.buildTokenAPI(
        ApiAuthService::class.java), userDataStore)


    override fun onStart() {
        super.onStart()
        binding.button2.setOnClickListener {
            logout()
        }
    }
}