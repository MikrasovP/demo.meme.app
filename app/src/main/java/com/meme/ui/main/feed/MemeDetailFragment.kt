package com.meme.ui.main.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.bumptech.glide.Glide
import com.meme.R
import com.meme.model.dto.MemeDto
import java.util.*


class MemeDetailFragment(var meme: MemeDto) : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var titleTv: TextView
    private lateinit var memePic: ImageView
    private lateinit var creationTimeTV: TextView
    private lateinit var likeBtn: ImageButton
    private lateinit var descriptionTV: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(
            R.layout.fragment_meme_details,
            container,
            false
        )

        toolbar = rootView.findViewById(R.id.meme_toolbar)
        titleTv = rootView.findViewById(R.id.meme_title_tv)
        memePic = rootView.findViewById(R.id.meme_pic)
        creationTimeTV = rootView.findViewById(R.id.meme_creation_time_tv)
        likeBtn = rootView.findViewById(R.id.meme_detail_like_btn)
        descriptionTV = rootView.findViewById(R.id.meme_description)

        toolbar.title = meme.title
        titleTv.text = meme.title
        Glide.with(this).load(meme.photoUrl).into(memePic)
        creationTimeTV.text = Date(meme.createdDate).toString()
        likeBtn.isSelected = meme.isFavourite
        likeBtn.setOnClickListener {
            FeedPresenter.onLikeBtnClickListener(it, meme)
        }
        descriptionTV.text = meme.description

        return rootView
    }


    companion object {
        fun newInstance(meme: MemeDto) =
            MemeDetailFragment(meme)
    }
}