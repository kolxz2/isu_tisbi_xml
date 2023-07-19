package ru.nikolas_snek.isu_tisbi_xml.presenter.home.screens.traning_screen.resycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/** отступ последнего элемента в списке */
class FooterDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
            outRect.bottom = padding
        }
    }
}