package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentPersonalPlanBinding

class PersonalPlanFragment : Fragment() {

    lateinit var binding: FragmentPersonalPlanBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersonalPlanBinding.inflate(layoutInflater)

        return binding.root

        // return inflater.inflate(R.layout.fragment_personal_plan, container, false)
    }

}