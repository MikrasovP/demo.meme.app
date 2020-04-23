package com.meme.ui.main.feed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.meme.R
import com.meme.model.dto.MemeDto


class FeedFragment : Fragment() {

    private lateinit var recyclerMemesView: RecyclerView
    private lateinit var memesProgressBar: ProgressBar
    private lateinit var loadErrorTV: TextView
    private lateinit var feedRefresher: SwipeRefreshLayout
    private val adapter = MemesAdapter {
        showMemeFragment(it)
    }

    private val feedPresenter = FeedPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)

        recyclerMemesView =
            rootView.findViewById<RecyclerView>(R.id.recycler_memes_view).apply {

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
        memesProgressBar = rootView.findViewById(R.id.memes_progress_bar)
        loadErrorTV = rootView.findViewById(R.id.load_memes_errorTV)

        feedRefresher = rootView.findViewById(R.id.feedRefresher)
        feedRefresher.setOnRefreshListener {
            feedPresenter.refreshMemes()
            feedRefresher.isRefreshing = false
        }
        feedRefresher.setColorSchemeResources(
            R.color.colorSurfBackgroundLighter
        )

        feedPresenter.getMemes()

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun showLoadError(error: Throwable) {
        loadErrorTV.visibility = View.VISIBLE
    }

    fun showProgressBar() {
        memesProgressBar.visibility = View.VISIBLE
    }

    fun showReloadError(error: Throwable) {
        Snackbar.make(
            feedRefresher,
            R.string.meme_reload_error,
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(
            ResourcesCompat.getColor(
                resources,
                R.color.colorSurfError,
                requireContext().theme
            )
        ).show()
    }

    private fun showMemeFragment(meme: MemeDto) {
        val intent = Intent(activity, MemeDetailActivity::class.java)
            .putExtra("meme", meme)
        activity?.startActivity(intent)
    }

    fun showMemes(memes: List<MemeDto>) {
        memesProgressBar.visibility = View.INVISIBLE
        loadErrorTV.visibility = View.INVISIBLE

        adapter.setData(memes)

    }

}