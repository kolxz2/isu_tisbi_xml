package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiAuthService
import ru.nikolas_snek.isu_tisbi_xml.data.api.ApiStudentService
import ru.nikolas_snek.isu_tisbi_xml.data.repository.UserRepositoryImpl
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentTrainingRatingBinding
import ru.nikolas_snek.isu_tisbi_xml.presenter.base.BaseFragment
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating.resycler.FooterDecoration
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating.resycler.SpaceItemDecoration
import ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating.resycler.TrainingRatingListAdapter


class TrainingRatingFragment :
    BaseFragment<TrainingRatingViewModel, FragmentTrainingRatingBinding, UserRepositoryImpl>() {

    private lateinit var shopListAdapter: TrainingRatingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setupRecyclerView()
        lifecycleScope.launch{
            val rating = viewModel.getTrainingRatingList()
            Log.d("Home", rating.toString())
            shopListAdapter.submitList(rating)
        }
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.training_rating_action_bar_title)
        return binding.root
    }

    private fun setupRecyclerView() {
        val rvShopList = binding.rvRatingList
        with(rvShopList) {
            shopListAdapter = TrainingRatingListAdapter()
            adapter = shopListAdapter

            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_between_items) // Define the spacing value in pixels
            addItemDecoration(SpaceItemDecoration(spacingInPixels))
            val footerDecoration = FooterDecoration(250)
            // Set the FooterDecoration on the RecyclerView
            addItemDecoration(footerDecoration)
        }
    }

    override fun getViewModel(): Class<TrainingRatingViewModel> =
        TrainingRatingViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentTrainingRatingBinding.inflate(inflater, container, false)

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