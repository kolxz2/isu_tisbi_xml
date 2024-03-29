package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.student_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentStudentProfileBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseFragment


class StudentProfileFragment : BaseFragment<StudentProfileViewModel, FragmentStudentProfileBinding, UserRepositoryImpl>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.student_profile_action_bar_title)
        return binding.root
    }
    override fun getViewModel() = StudentProfileViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentStudentProfileBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = UserRepositoryImpl(
        remoteDataSource.buildTokenAPI(
            ApiAuthService::class.java
        ),
        remoteDataSource.buildTokenAPI(
            ApiStudentService::class.java
        ),
        userDataStore
    )


    override fun onStart() {
        super.onStart()
        binding.button2.setOnClickListener {
            logout()
        }
    }
}