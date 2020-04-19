package com.meme.ui.main.feed

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.meme.R
import com.meme.model.dto.MemeDto
import kotlinx.android.synthetic.main.meme_card.view.*

class MemeCard(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(
    context,
    attributeSet, defStyleAttr
) {

    var memeDto: MemeDto = MemeDto(0, "Test title", "Description test",
        true, 1111,
        "https://media.sproutsocial.com/uploads/meme-example.jpg")

    init {
        View.inflate(context, R.layout.meme_card, this)
        Glide.with(this).load(memeDto.photoUrl).into(card_meme_image)
        card_title.text = memeDto.title
    }

}