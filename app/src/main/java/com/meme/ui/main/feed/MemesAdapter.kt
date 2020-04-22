package com.meme.ui.main.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.meme.R
import com.meme.model.dto.MemeDto

class MemesAdapter() : RecyclerView.Adapter<MemesAdapter.MemesViewHolder>() {

    private var memes: List<MemeDto> = listOf()

    fun setData(data: List<MemeDto>){
        memes = data
        notifyDataSetChanged()
    }

    inner class MemesViewHolder(val memeCard: MemeCard) : RecyclerView.ViewHolder(memeCard)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesViewHolder {
        // create a new view
        val memeCard = LayoutInflater.from(parent.context)
            .inflate(R.layout.meme_card_object, parent, false) as MemeCard
        // set the view's size, margins, paddings and layout parameters

        memeCard.memeDto = memes[0]

        return MemesViewHolder(memeCard)
    }

    override fun getItemCount(): Int = memes.size

    override fun onBindViewHolder(holder: MemesViewHolder, position: Int) {
        holder.memeCard.memeDto = memes[position]
    }
}