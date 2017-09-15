package de.kleinelamas.svbrockscheid.helpers

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * @author Matthias Kutscheid
 */
class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        // Add top margin only for the first item to avoid double space between items
        val layoutPosition = parent.getChildLayoutPosition(view)
        var spanCount = 1
        (parent.layoutManager as? GridLayoutManager)?.let {
            spanCount = it.spanCount
        }

        parent.layoutManager.baseline
        val lastRowLength = parent.childCount % spanCount
        if (layoutPosition < parent.childCount - if (lastRowLength == 0) spanCount else lastRowLength) {
            outRect.bottom = space
        }

        if (layoutPosition % spanCount == 0) {
            outRect.right = space
        }
    }
}