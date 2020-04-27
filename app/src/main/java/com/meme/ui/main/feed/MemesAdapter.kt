package com.meme.ui.main.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meme.R
import com.meme.model.dto.MemeDto

class MemesAdapter(
    private val onItemClickListener: (MemeDto) -> Unit
) : RecyclerView.Adapter<MemesAdapter.MemesViewHolder>() {

    private var memes: List<MemeDto> = listOf()

    fun setData(data: List<MemeDto>) {
        val callback = MemesDiffUtilCallback(
            memes,
            data
        )

        val difRes = DiffUtil.calculateDiff(callback)

        memes = data
        difRes.dispatchUpdatesTo(this)

    }


    inner class MemesViewHolder(
        private val memeCard: CardView
    ) : RecyclerView.ViewHolder(memeCard) {
        private val imageView: ImageView = memeCard.findViewById(R.id.card_meme_image)
        private val titleTV: TextView = memeCard.findViewById(R.id.card_title)
        private val likeBtn: ImageButton = memeCard.findViewById(R.id.card_like_btn)

        init {
            likeBtn.setOnClickListener {
                FeedPresenter.onLikeBtnClickListener(
                    it,
                    memes[adapterPosition]
                )
            }
            memeCard.setOnClickListener { onItemClickListener(memes[adapterPosition]) }
        }

        fun bind(position: Int) {
            titleTV.text = memes[position].title
            Glide.with(memeCard).load(memes[position].photoUrl).into(imageView)
            likeBtn.isSelected = memes[position].isFavourite
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