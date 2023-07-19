package ru.nikolas_snek.isu_tisbi_xml.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.data.api.RemoteDataSource
import ru.nikolas_snek.isu_tisbi_xml.data.data_store.UserDataStore
import ru.nikolas_snek.isu_tisbi_xml.data.repository.BaseRepository
import ru.nikolas_snek.isu_tisbi_xml.presenter.auth.AuthActivity
import ru.nikolas_snek.isu_tisbi_xml.presenter.startNewActivity

abstract class BaseFragment<VM: BaseViewModel, B: ViewBinding, R: BaseRepository>: Fragment(){


    protected lateinit var userDataStore: UserDataStore
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userDataStore = UserDataStore(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory)[getViewModel()]
        return binding.root
    }

    fun logout() = lifecycleScope.launch {
        userDataStore.clearUserData()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository(): R
}