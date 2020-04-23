package com.meme.ui.main.feed

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.meme.R
import com.meme.model.dto.MemeDto
import java.util.*


class MemeDetailActivity : AppCompatActivity() {

    private lateinit var meme: MemeDto
    private lateinit var toolbar: Toolbar
    private lateinit var titleTv: TextView
    private lateinit var memePic: ImageView
    private lateinit var creationTimeTV: TextView
    private lateinit var likeBtn: ImageButton
    private lateinit var descriptionTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_meme_details)

        meme = intent.getSerializableExtra("meme") as MemeDto

        toolbar = findViewById(R.id.meme_toolbar)
        titleTv = findViewById(R.id.meme_title_tv)
        memePic = findViewById(R.id.meme_pic)
        creationTimeTV = findViewById(R.id.meme_creation_time_tv)
        likeBtn = findViewById(R.id.meme_detail_like_btn)
        descriptionTV = findViewById(R.id.meme_description)

        toolbar.title = meme.title
        titleTv.text = meme.title
        Glide.with(this).load(meme.photoUrl).into(memePic)
        creationTimeTV.text = Date(meme.createdDate).toString()
        likeBtn.isSelected = meme.isFavourite
        likeBtn.setOnClickListener {
            FeedPresenter.onLikeBtnClickListener(it, meme)
        }
        descriptionTV.text = meme.description
    }
}