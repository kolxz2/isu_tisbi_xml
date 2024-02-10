package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.schedule_lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentScheduleLessonsBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseFragment
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.student_profile.StudentProfileViewModel


class ScheduleLessonsFragment :
    BaseFragment<StudentProfileViewModel, FragmentScheduleLessonsBinding, UserRepositoryImpl>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.schedule_lessons_action_bar_title)
        return binding.root
    }

    override fun getViewModel() = StudentProfileViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentScheduleLessonsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = UserRepositoryImpl(
        remoteDataSource.buildTokenAPI(
            ApiAuthService::class.java
        ),
        remoteDataSource.buildTokenAPI(
            ApiStudentService::class.java
        ),
        userDataStore
    )


}