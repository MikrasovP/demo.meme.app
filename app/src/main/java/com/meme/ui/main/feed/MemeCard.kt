package com.meme.ui.main.feed

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.meme.R
import com.meme.model.dto.MemeDto
import kotlinx.android.synthetic.main.meme_card.view.*

class MemeCard(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.style.SurfTheme_ActionBar
) : CardView(
    context,
    attributeSet, defStyleAttr
) {

    var imageLink: String? = null
        set(value) {
            Glide.with(this).load(value).into(imageView)
            field = value
        }
    lateinit var imageView: ImageView
    lateinit var title: TextView
    lateinit var favouriteBtn: ImageButton

    var memeDto: MemeDto? = null
        set(value) {
            field = value
            imageLink = value?.photoUrl
            title.text = value?.title ?: "Title"
            favouriteBtn.isSelected = value?.isFavourite ?: false
        }

    constructor(context: Context, attributeSet: AttributeSet?) :
            this(context, attributeSet, R.style.SurfTheme_ActionBar) {
        inflate(context, R.layout.meme_card, this)
        title = findViewById(R.id.card_title)
        favouriteBtn = findViewById(R.id.card_like_btn)
        imageView = findViewById(R.id.card_meme_image)
    }


}