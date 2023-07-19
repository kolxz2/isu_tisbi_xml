package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.traning_screen.resycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nikolas_snek.isu_tisbi_xml.R
import ru.nikolas_snek.isu_tisbi_xml.databinding.RatingRecyclerItemBinding
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile

class TrainingRatingListAdapter :
    ListAdapter<StudentRatingProfile, RatingRecyclerIemHolder>(RatingRecyclerIemDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingRecyclerIemHolder {

        val binding = RatingRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RatingRecyclerIemHolder(binding)
    }

    override fun onBindViewHolder(holder: RatingRecyclerIemHolder, position: Int) {
        val student = getItem(position)
        holder.tvModulePercent.text = student.moduleProcent.toString()
        holder.tvRatingPosition.text = position.toString()
        holder.tvStudentName.text = student.studentName

    }
}