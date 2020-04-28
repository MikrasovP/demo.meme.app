package com.meme.ui.main.profile

import android.util.Log
import com.meme.model.repo.DatabaseRepo
import com.meme.model.repo.MemesNetRepo
import com.meme.utils.PrefsEditor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfilePresenter(
    private val fragment: ProfileFragment
) {

    fun onActivityCreated() {
        fragment.setUserData(PrefsEditor.getUser())
    }

    private fun loadMemes() {
        DatabaseRepo.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fragment.hideProgressBar()
                fragment.showMemes(it)
                Log.d("ProfilePresenter", it.toString())
            }, {
                throw it
            })
    }

    fun refreshMemes() = loadMemes()

    fun getMemes() {
        fragment.showProgressBar()

        loadMemes()
    }

    fun onLogoutClick() {
        fragment.showAlertDialog()
    }

    fun logout() {
        MemesNetRepo.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                PrefsEditor.removeUser()
                fragment.activity?.finish()
                fragment.moveToLoginActivity()
            }, {
                throw it
            })

    }

}