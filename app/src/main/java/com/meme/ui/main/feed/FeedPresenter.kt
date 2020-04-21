package com.meme.ui.main.feed

import com.meme.model.dto.MemeDto
import com.meme.model.repo.MemesRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FeedPresenter(
    private val fragment: FeedFragment
) {

    private val memesRepo = MemesRepo

    init {

    }

    fun getMemes() {
        fragment.showProgressBar()
        memesRepo.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fragment.showMemes(it)
            }, {
                fragment.showLoadError(
                    it
                )
            })
    }
}