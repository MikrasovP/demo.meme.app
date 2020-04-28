package com.meme.ui.main.feed

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
    private lateinit var toolbarTitleTv: TextView
    private lateinit var toolbarCloseBtn: ImageButton
    private lateinit var titleTv: TextView
    private lateinit var memePic: ImageView
    private lateinit var creationTimeTV: TextView
    private lateinit var likeBtn: ImageButton
    private lateinit var descriptionTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_details)

        meme = intent.getSerializableExtra("meme") as MemeDto

        toolbarTitleTv = findViewById(R.id.meme_toolbar_title)
        toolbarCloseBtn = findViewById(R.id.meme_close_btn)
        titleTv = findViewById(R.id.meme_title_tv)
        memePic = findViewById(R.id.meme_pic)
        creationTimeTV = findViewById(R.id.meme_creation_time_tv)
        likeBtn = findViewById(R.id.meme_detail_like_btn)
        descriptionTV = findViewById(R.id.meme_description)

        toolbarTitleTv.text = meme.title
        toolbarCloseBtn.setOnClickListener {
            this.finish()
        }
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