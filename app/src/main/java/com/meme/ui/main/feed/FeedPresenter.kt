package com.meme.ui.main.feed

import com.meme.model.repo.MemesNetRepo
import com.meme.utils.App
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FeedPresenter(
    private val fragment: FeedFragment
) {

    @Inject
    lateinit var memesNetRepo: MemesNetRepo

    fun onActivityCreated(){
        val app: App = fragment.requireActivity().application as App
        app.appComponent.inject(this)
    }

    fun refreshMemes() {
        memesNetRepo.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fragment.showMemes(it)
            }, {
                fragment.showReloadError(it)
            })
    }

    fun getMemes() {
        fragment.showProgressBar()
        memesNetRepo.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fragment.showMemes(it)
            }, {
                fragment.showLoadError(it)
            })
    }
}