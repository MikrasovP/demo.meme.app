package com.meme.ui.main.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.meme.R
import com.meme.model.dto.MemeDto
import ru.surfstudio.android.easyadapter.EasyAdapter

class FeedFragment : Fragment() {

    private lateinit var recyclerMemesView: RecyclerView
    private lateinit var memesProgressBar: ProgressBar
    private lateinit var loadErrorTV: TextView
    private val adapter = MemesAdapter()

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

        recyclerMemesView = view.findViewById<RecyclerView>(R.id.recyclerMemesView).apply {

            adapter = this@FeedFragment.adapter

            val staggeredGridLayoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            staggeredGridLayoutManager.gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_NONE

            addItemDecoration(MItemDecorator(8))

            layoutManager = staggeredGridLayoutManager

            setPadding(8, 0, 8, 8)

            setHasFixedSize(true)
        }
        memesProgressBar = view.findViewById(R.id.memesProgressBar)
        loadErrorTV = view.findViewById(R.id.loadMemesErrorTV)


        feedPresenter.getMemes()
    }

    fun showLoadError(error: Throwable) {
        loadErrorTV.visibility = View.VISIBLE
    }

    fun showProgressBar() {
        memesProgressBar.visibility = View.VISIBLE
    }

    fun showReloadError() {

    }

    fun showMemes(memes: List<MemeDto>) {
        memesProgressBar.visibility = View.INVISIBLE

        adapter.setData(memes)

    }

}