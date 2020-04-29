package com.meme.ui.main.feed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.meme.R
import com.meme.model.dto.MemeDto
import com.meme.ui.main.recycler.MItemDecorator
import com.meme.ui.main.recycler.MemesAdapter
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.meme_card.*


class FeedFragment : Fragment() {

    companion object {
        private const val RECYCLER_COLUMN_SPAN = 2
    }

    private val adapter = MemesAdapter {
        showMemeDetailActivity(it)
    }

    private val feedPresenter = FeedPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true

        initRecycler()
    }

    private fun initRecycler(){
        recycler_memes_view.apply {

            adapter = this@FeedFragment.adapter

            val staggeredGridLayoutManager = StaggeredGridLayoutManager(
                RECYCLER_COLUMN_SPAN,
                StaggeredGridLayoutManager.VERTICAL
            )
            staggeredGridLayoutManager.gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_NONE

            addItemDecoration(MItemDecorator(8))

            layoutManager = staggeredGridLayoutManager

            setPadding(8, 0, 8, 8)

            setHasFixedSize(true)
        }


        feed_refresher.setOnRefreshListener {
            feedPresenter.refreshMemes()
            feed_refresher.isRefreshing = false
        }
        feed_refresher.setColorSchemeResources(
            R.color.colorSurfBackgroundLighter
        )

        feedPresenter.getMemes()
    }

    fun showLoadError(error: Throwable) {
        load_memes_error_tv.visibility = View.VISIBLE
    }

    fun showProgressBar() {
        memes_progress_bar.visibility = View.VISIBLE
    }

    fun showReloadError(error: Throwable) {
        Snackbar.make(
            feed_refresher,
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

    private fun showMemeDetailActivity(meme: MemeDto) {
        val intent = Intent(context, MemeDetailActivity::class.java)
            .putExtra(MemeDetailActivity.MEME_EXTRA_NAME, meme)
        val p1 = Pair(card_meme_image as View, "meme_pic")
        val p2 = Pair(card_title as View, "meme_title")

        val options: ActivityOptionsCompat? =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                p1,
                p2
            )
        if (options != null) {
            requireActivity().startActivity(intent, options.toBundle())
        }
    }

    fun showMemes(memes: List<MemeDto>) {
        memes_progress_bar.visibility = View.INVISIBLE
        load_memes_error_tv.visibility = View.INVISIBLE

        adapter.setData(memes)
    }

}