package com.meme.ui.main.feed

import com.meme.model.repo.MemesNetRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FeedPresenter(
    private val fragment: FeedFragment
) {

    private val memesRepo = MemesNetRepo

    fun refreshMemes() {
        memesRepo.getMemes()
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
        memesRepo.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fragment.showMemes(it)
            }, {
                fragment.showLoadError(it)
            })
    }
}