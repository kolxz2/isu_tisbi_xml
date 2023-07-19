package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.traning_screen.resycler

import androidx.recyclerview.widget.DiffUtil
import ru.nikolas_snek.isu_tisbi_xml.domain.repository.models.StudentRatingProfile

class RatingRecyclerIemDiffUtilCallback: DiffUtil.ItemCallback<StudentRatingProfile>() {
    override fun areItemsTheSame(
        oldItem: StudentRatingProfile,
        newItem: StudentRatingProfile,
    ): Boolean {
        return oldItem.studentName == newItem.studentName
    }

    override fun areContentsTheSame(
        oldItem: StudentRatingProfile,
        newItem: StudentRatingProfile,
    ): Boolean {
        return oldItem.moduleProcent == newItem.moduleProcent
    }

    override fun getChangePayload(
        oldItem: StudentRatingProfile,
        newItem: StudentRatingProfile,
    ): Boolean {
        return oldItem == newItem
    }

}
