package com.meme.ui.main.feed

import android.view.View
import com.meme.model.dto.MemeDto
import com.meme.model.repo.MemesNetRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FeedPresenter(
    private val fragment: FeedFragment
) {

    private val memesRepo = MemesNetRepo

    companion object{
        fun onLikeBtnClickListener(view: View, meme: MemeDto){
            meme.isFavourite = !meme.isFavourite
            view.isSelected = !view.isSelected
        }
    }

    fun onMemeClickListener(){

    }

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