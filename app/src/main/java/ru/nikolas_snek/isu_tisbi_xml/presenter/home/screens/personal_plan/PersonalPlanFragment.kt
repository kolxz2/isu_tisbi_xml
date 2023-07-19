package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.personal_plan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentPersonalPlanBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseFragment

class PersonalPlanFragment : BaseFragment<PersonalPlanViewModel, FragmentPersonalPlanBinding, UserRepositoryImpl>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        lifecycleScope.launch {
            val temp = viewModel.getPersonalPlanList()
            Log.d("PersonalPlanFragment", temp.toString())
        }

        return binding.root
    }

    override fun getViewModel(): Class<PersonalPlanViewModel> = PersonalPlanViewModel::class.java
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentPersonalPlanBinding = FragmentPersonalPlanBinding.inflate(inflater, container, false)

    override fun getFragmentRepository()= UserRepositoryImpl(
        remoteDataSource.buildTokenAPI(
            ApiAuthService::class.java
        ),
        remoteDataSource.buildTokenAPI(
            ApiStudentService::class.java
        ),
        userDataStore
    )

}