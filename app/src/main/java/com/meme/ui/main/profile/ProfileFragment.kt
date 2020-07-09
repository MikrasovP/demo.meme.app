package com.meme.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.meme.R
import com.meme.model.UserInfo
import com.meme.model.dto.MemeDto
import com.meme.ui.login.LoginActivity
import com.meme.ui.main.feed.MemeDetailActivity
import com.meme.ui.main.recycler.MItemDecorator
import com.meme.ui.main.recycler.MemesAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.meme_card.*

class ProfileFragment : Fragment() {

    companion object {
        private const val RECYCLER_COLUMN_SPAN = 2
    }

    private val adapter = MemesAdapter {
        showMemeDetailActivity(it)
    }

    private val presenter: ProfilePresenter = ProfilePresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
        presenter.onActivityCreated()

        initRecycler()

        profile_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.profile_menu_exit -> {
                    presenter.onLogoutClick()
                    true
                }
                R.id.profile_menu_about -> {
                    showAbout()
                    true
                }
                else ->
                    super.onOptionsItemSelected(it)
            }
        }
    }

    private fun initRecycler() {
        profile_memes_rv.apply {

            adapter = this@ProfileFragment.adapter

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


        profile_memes_refresher.setOnRefreshListener {
            presenter.refreshMemes()
            profile_memes_refresher.isRefreshing = false
        }

        profile_memes_refresher.setColorSchemeResources(
            R.color.colorSurfBackgroundLighter
        )

        presenter.getMemes()
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

    fun setUserData(userInfo: UserInfo) {
        profile_username_tv.text = userInfo.username
        profile_user_description_tv.text = userInfo.userDescription
    }

    fun showProgressBar() {
        profile_progress_bar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        profile_progress_bar.visibility = View.INVISIBLE
    }

    fun showMemes(memes: List<MemeDto>) {
        adapter.setData(memes)
    }

    fun showAlertDialog() {
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_cancel) { dialog, _ ->
                    dialog.cancel()
                }
                .setNegativeButton(R.string.dialog_quit) { _, _ ->
                    presenter.logout()
                }
            builder.create().show()
        }
    }

    fun moveToLoginActivity() {
        val intent = Intent(activity?.applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showAbout() {
        Toast.makeText(context, "About", Toast.LENGTH_SHORT).show()
    }
}