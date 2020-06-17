package com.meme.ui.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.meme.model.dto.MemeDto

class MemesDiffUtilCallback(
    private val oldList: List<MemeDto>,
    private val newList: List<MemeDto>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].id == oldList[oldItemPosition].id

    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]
}