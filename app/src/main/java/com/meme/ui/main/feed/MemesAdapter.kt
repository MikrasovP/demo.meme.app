package com.meme.ui.main.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meme.R
import com.meme.model.dto.MemeDto

class MemesAdapter : RecyclerView.Adapter<MemesAdapter.MemesViewHolder>() {

    private var memes: List<MemeDto> = listOf()

    fun setData(data: List<MemeDto>) {
        memes = data
        notifyDataSetChanged()
    }

    inner class MemesViewHolder(
        private val memeCard: CardView
    ) : RecyclerView.ViewHolder(memeCard) {
        private var imageView: ImageView = memeCard.findViewById(R.id.card_meme_image)
        private var titleTV: TextView = memeCard.findViewById(R.id.card_title)
        private var likeBtn: ImageButton = memeCard.findViewById(R.id.card_like_btn)

        fun bind( position: Int){
            this.titleTV.text = memes[position].title
            Glide.with(this.memeCard).load(memes[position].photoUrl).into(this.imageView)
            this.likeBtn.isSelected = memes[position].isFavourite
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesViewHolder {

        val memeCard = LayoutInflater.from(parent.context)
            .inflate(R.layout.meme_card, parent, false) as CardView




        return MemesViewHolder(memeCard)
    }

    override fun getItemCount(): Int = memes.size


    override fun onBindViewHolder(holder: MemesViewHolder, position: Int) {
        holder.bind(position)
    }
}