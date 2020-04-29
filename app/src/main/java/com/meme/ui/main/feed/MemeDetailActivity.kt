package com.meme.ui.main.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.meme.R
import com.meme.model.dto.MemeDto
import kotlinx.android.synthetic.main.activity_meme_details.*
import java.util.*


class MemeDetailActivity : AppCompatActivity() {

    companion object {
        const val MEME_EXTRA_NAME = "meme"

        fun onLikeClick(meme: MemeDto, view: View) {
            meme.isFavourite = !meme.isFavourite
            view.isSelected = !view.isSelected
        }

        fun onShareClick(meme: MemeDto, context: Context) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${meme.title}\n${meme.description}\n${meme.photoUrl}"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(context, shareIntent, null)
        }
    }

    private lateinit var meme: MemeDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_details)

        meme = intent.getSerializableExtra(MEME_EXTRA_NAME) as MemeDto

        fillData()
    }

    private fun fillData() {
        meme_toolbar_title.text = meme.title
        meme_close_btn.setOnClickListener {
            this.finish()
        }
        meme_title_tv.text = meme.title

        Glide.with(this).load(meme.photoUrl).into(meme_pic)
        meme_creation_time_tv.text = Date(meme.createdDate).toString()

        meme_detail_like_btn.isSelected = meme.isFavourite
        meme_detail_like_btn.setOnClickListener {
            onLikeClick(meme, it)
        }

        meme_share_btn.setOnClickListener {
            onShareClick(meme, meme_share_btn.context)
        }

        meme_description.text = meme.description
    }
}