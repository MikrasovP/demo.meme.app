package com.meme.ui.main.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.meme.R
import com.meme.model.dto.MemeDto
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private val feedPresenter = FeedPresenter(
        this
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedPresenter.getMemes()
    }

    fun showLoadError(error: Throwable){
        loadErrorTV.visibility = View.VISIBLE
    }

    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    fun showReloadError(){

    }

    fun showMemes(memes: List<MemeDto>){
        progressBar.visibility = View.INVISIBLE
    }

}