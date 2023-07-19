package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.training_rating.resycler

import androidx.recyclerview.widget.DiffUtil
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfileWithPosition

class RatingRecyclerIemDiffUtilCallback: DiffUtil.ItemCallback<StudentRatingProfileWithPosition>() {
    override fun areItemsTheSame(
        oldItem: StudentRatingProfileWithPosition,
        newItem: StudentRatingProfileWithPosition,
    ): Boolean {
        return oldItem.studentName == newItem.studentName
    }

    override fun areContentsTheSame(
        oldItem: StudentRatingProfileWithPosition,
        newItem: StudentRatingProfileWithPosition,
    ): Boolean {
        return oldItem.moduleProcent == newItem.moduleProcent
    }

    override fun getChangePayload(
        oldItem: StudentRatingProfileWithPosition,
        newItem: StudentRatingProfileWithPosition,
    ): Boolean {
        return oldItem == newItem
    }

}
