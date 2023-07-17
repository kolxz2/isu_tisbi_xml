package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nikolas_snek.isu_tisbi_xml.databinding.FragmentPersonalPlanBinding
import ru.nikolas_snek.isu_tisbi_xml.R

class PersonalPlanFragment : Fragment() {

    lateinit var binding: FragmentPersonalPlanBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*binding  = FragmentHomeBinding.inflate(layoutInflater)
        with(binding){
            val name = arguments?.getString("name","")
            nameItem = name
            return root
        }*/
         return inflater.inflate(R.layout.fragment_personal_plan, container, false)
    }

}