package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating.resycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nikolas_snek.isu_tisbi_xml.databinding.RatingRecyclerItemBinding
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfileWithPosition

class TrainingRatingListAdapter :
    ListAdapter<StudentRatingProfileWithPosition, RatingRecyclerIemHolder>(RatingRecyclerIemDiffUtilCallback()) {

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
        holder.tvRatingPosition.text = student.position.toString()
        holder.tvStudentName.text = student.studentName

    }
}