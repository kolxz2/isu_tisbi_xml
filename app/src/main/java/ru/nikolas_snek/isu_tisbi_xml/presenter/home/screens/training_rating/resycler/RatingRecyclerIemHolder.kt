package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating.resycler

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nikolas_snek.isu_tisbi_xml.databinding.RatingRecyclerItemBinding

class RatingRecyclerIemHolder(private val binding: RatingRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val tvStudentName: TextView = binding.tvStudentName
    val tvModulePercent: TextView = binding.tvModulePercent
    val tvRatingPosition: TextView = binding.tvRatingPosition

}
